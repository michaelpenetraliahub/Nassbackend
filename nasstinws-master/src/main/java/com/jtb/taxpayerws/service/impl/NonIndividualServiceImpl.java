package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.client.payload.request.JTBTaxpayerRequest;
import com.jtb.taxpayerws.client.payload.response.JTBNonIndividualResponse;
import com.jtb.taxpayerws.client.payload.response.NonIndividualTaxpayer;
import com.jtb.taxpayerws.client.proxy.JTBClientProxy;
import com.jtb.taxpayerws.dto.NonIndividualDto;
import com.jtb.taxpayerws.entity.NonIndividualEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.Paging;
import com.jtb.taxpayerws.payload.response.NonIndividualResponse;
import com.jtb.taxpayerws.repository.NonIndividualRepository;
import com.jtb.taxpayerws.service.AbstractJTBService;
import com.jtb.taxpayerws.service.NonIndividualService;
import com.jtb.taxpayerws.utils.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NonIndividualServiceImpl extends AbstractJTBService implements NonIndividualService {
    private final NonIndividualRepository nonIndividualRepository;
    private final ModelMapper modelMapper;

    public NonIndividualServiceImpl(JTBClientProxy jtbClientProxy, NonIndividualRepository nonIndividualRepository, ModelMapper modelMapper) {
        super(jtbClientProxy);
        this.nonIndividualRepository = nonIndividualRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NonIndividualDto createNonIndividual(NonIndividualDto individualDto) {
        NonIndividualEntity nonIndividual = modelMapper.map(individualDto, NonIndividualEntity.class);

        return modelMapper.map(nonIndividualRepository.save(nonIndividual), NonIndividualDto.class);
    }

    @Override
    public NonIndividualDto updateNonIndividual(NonIndividualDto nonIndividualDto) {
        NonIndividualDto oldNonIndividualDto = abortIfIndividualDoesNotExist(nonIndividualDto.getId());
        modelMapper.map(nonIndividualDto, oldNonIndividualDto);

        NonIndividualEntity individual = modelMapper.map(oldNonIndividualDto, NonIndividualEntity.class);

        return modelMapper.map(nonIndividualRepository.save(individual), NonIndividualDto.class);
    }

    @Override
    public NonIndividualDto getNonIndividualDetails(Long id) {
        return abortIfIndividualDoesNotExist(id);
    }

    @Override
    public PageDetail<NonIndividualResponse> getAllNonIndividuals(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("dateOfRegistration"));
        Page<NonIndividualEntity> pages = nonIndividualRepository.findAll(pageable);
        Paging paging = new Paging();
        paging.setPage(pages.getNumber() + 1);
        paging.setPageSize(pages.getSize());
        paging.setTotalPages(pages.getTotalPages());
        paging.setTotalRecord(pages.getTotalElements());

        PageDetail<NonIndividualResponse> pageDetail = new PageDetail<>();
        pageDetail.setPaging(paging);
        pageDetail.setRequests(pages.getContent().stream().map(x -> modelMapper.map(x, NonIndividualResponse.class)).collect(Collectors.toList()));

        return pageDetail;
    }

    @Override
    public NonIndividualDto findNonIndividualByKeyword(String search) {
        Optional<NonIndividualEntity> optionalNonIndividual = Optional.ofNullable(nonIndividualRepository.findNonIndividualByKeyword(search));

        if (!optionalNonIndividual.isPresent() && search.length() == 10 && search.chars().allMatch(Character::isDigit)) {
            JTBTaxpayerRequest jtbTaxpayerRequest = getJtbTaxpayerRequest(search.trim());
            JTBNonIndividualResponse nonIndividualResponse = jtbClientProxy.getNonIndividual(jtbTaxpayerRequest, getLoginResponseEntity().getTokenId());

            if(nonIndividualResponse.getResponseCode().equals("001")) {
                NonIndividualTaxpayer taxpayer = nonIndividualResponse.getTaxpayer();
                NonIndividualEntity nonIndividualEntity = modelMapper.map(taxpayer, NonIndividualEntity.class);
                nonIndividualEntity.setCurrentTin(taxpayer.getTin());

                if(!taxpayer.getDateOfInc().trim().isEmpty() && Objects.nonNull(taxpayer.getDateOfInc())) nonIndividualEntity.setDateOfIncorporation(DateUtil.parseStringToDate(taxpayer.getDateOfInc()));
                if(!taxpayer.getDateOfReg().trim().isEmpty() && Objects.nonNull(taxpayer.getDateOfReg())) nonIndividualEntity.setDateOfRegistration(DateUtil.parseStringToDate(taxpayer.getDateOfReg()));

                optionalNonIndividual = Optional.of(nonIndividualRepository.save(nonIndividualEntity));
            }
        }
        NonIndividualEntity nonIndividual = optionalNonIndividual.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));

        return modelMapper.map(nonIndividual, NonIndividualDto.class);
    }

    private NonIndividualDto abortIfIndividualDoesNotExist(Long id) {
        Optional<NonIndividualEntity> optionalNonIndividual = nonIndividualRepository.findById(id);

        NonIndividualEntity nonIndividual = optionalNonIndividual.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));

        return modelMapper.map(nonIndividual, NonIndividualDto.class);
    }
}
