package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.client.payload.request.JTBTaxpayerRequest;
import com.jtb.taxpayerws.client.payload.response.IndividualTaxpayer;
import com.jtb.taxpayerws.client.payload.response.JTBIndividualResponse;
import com.jtb.taxpayerws.client.proxy.JTBClientProxy;
import com.jtb.taxpayerws.dto.IndividualDto;
import com.jtb.taxpayerws.entity.IndividualEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.Paging;
import com.jtb.taxpayerws.payload.response.IndividualResponse;
import com.jtb.taxpayerws.repository.IndividualRepository;
import com.jtb.taxpayerws.service.AbstractJTBService;
import com.jtb.taxpayerws.service.IndividualService;
import com.jtb.taxpayerws.utils.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IndividualServiceImpl extends AbstractJTBService implements IndividualService {
    private final IndividualRepository individualRepository;
    private final ModelMapper modelMapper;

    public IndividualServiceImpl(JTBClientProxy jtbClientProxy, IndividualRepository individualRepository, ModelMapper modelMapper) {
        super(jtbClientProxy);
        this.individualRepository = individualRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IndividualDto createIndividual(IndividualDto individualDto) {
        IndividualEntity individual = modelMapper.map(individualDto, IndividualEntity.class);

        return modelMapper.map(individualRepository.save(individual), IndividualDto.class);
    }

    @Override
    public IndividualDto updateIndividual(IndividualDto individualDto) {
        IndividualDto oldIndividualDto = abortIfIndividualDoesNotExist(individualDto.getId());
        modelMapper.map(individualDto, oldIndividualDto);

        IndividualEntity individual = modelMapper.map(oldIndividualDto, IndividualEntity.class);

        return modelMapper.map(individualRepository.save(individual), IndividualDto.class);
    }

    @Override
    public IndividualDto getIndividualDetails(Long id) {
        return abortIfIndividualDoesNotExist(id);
    }

    @Override
    @Transactional
    public PageDetail<IndividualResponse> getAllIndividuals(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("dateOfRegistration").descending());
        Page<IndividualEntity> pages = individualRepository.findAll(pageable);
        Paging paging = new Paging();
        paging.setPage(pages.getNumber() + 1);
        paging.setPageSize(pages.getSize());
        paging.setTotalPages(pages.getTotalPages());
        paging.setTotalRecord(pages.getTotalElements());

        PageDetail<IndividualResponse> pageDetail = new PageDetail<>();
        pageDetail.setPaging(paging);
        pageDetail.setRequests(pages.getContent().stream().map(x -> modelMapper.map(x, IndividualResponse.class)).collect(Collectors.toList()));

        return pageDetail;
    }

    @Override
    @Transactional
    public IndividualDto findIndividualByKeyword(String search) {
        Optional<IndividualEntity> optionalIndividual = Optional.ofNullable(individualRepository.findIndividualByKeyword(search));

        if (!optionalIndividual.isPresent() && search.length() == 10 && search.chars().allMatch(Character::isDigit)) {
            JTBTaxpayerRequest jtbTaxpayerRequest = getJtbTaxpayerRequest(search.trim());
            JTBIndividualResponse individualResponse = jtbClientProxy.getIndividual(jtbTaxpayerRequest, getLoginResponseEntity().getTokenId());

            if(individualResponse.getResponseCode().equals("001")) {
                IndividualTaxpayer taxpayer = individualResponse.getTaxpayer();
                IndividualEntity individualEntity = modelMapper.map(taxpayer, IndividualEntity.class);
                individualEntity.setJtbTin(taxpayer.getTin());

                if(!taxpayer.getDob().trim().isEmpty() && Objects.nonNull(taxpayer.getDob())) individualEntity.setDateOfBirth(DateUtil.parseStringToDate(taxpayer.getDob()));
                if(!taxpayer.getDateOfReg().trim().isEmpty() && Objects.nonNull(taxpayer.getDateOfReg())) individualEntity.setDateOfRegistration(DateUtil.parseStringToDate(taxpayer.getDateOfReg()));

                optionalIndividual = Optional.of(individualRepository.save(individualEntity));
            }
        }

        IndividualEntity individual = optionalIndividual.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));

        return modelMapper.map(individual, IndividualDto.class);
    }

    private IndividualDto abortIfIndividualDoesNotExist(Long id) {
        Optional<IndividualEntity> optionalIndividual = individualRepository.findById(id);

        IndividualEntity individual = optionalIndividual.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
        return modelMapper.map(individual, IndividualDto.class);
    }
}
