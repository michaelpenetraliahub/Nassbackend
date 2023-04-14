package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.client.payload.request.JTBTaxpayerRequest;
import com.jtb.taxpayerws.client.payload.response.IndividualTaxpayer;
import com.jtb.taxpayerws.client.payload.response.JTBIndividualResponse;
import com.jtb.taxpayerws.client.proxy.JTBClientProxy;
import com.jtb.taxpayerws.dto.IndividualTinGenerationDto;
import com.jtb.taxpayerws.entity.IndividualTinGenerationEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.Paging;
import com.jtb.taxpayerws.payload.response.IndividualTinGenerationResponse;
import com.jtb.taxpayerws.repository.IndividualTinGenerationRepository;
import com.jtb.taxpayerws.service.AbstractJTBService;
import com.jtb.taxpayerws.service.IndividualTinGenerationService;
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
public class IndividualTinGenerationServiceImpl extends AbstractJTBService implements IndividualTinGenerationService {
    private final IndividualTinGenerationRepository individualTinGenerationRepository;
    private final ModelMapper modelMapper;

    public IndividualTinGenerationServiceImpl(JTBClientProxy jtbClientProxy, IndividualTinGenerationRepository individualTinGenerationRepository, ModelMapper modelMapper) {
        super(jtbClientProxy);
        this.individualTinGenerationRepository = individualTinGenerationRepository;
        this.modelMapper = modelMapper;
    }




    @Override
    public IndividualTinGenerationDto createIndividualTinGeneration(IndividualTinGenerationDto individualTinGenerationDto) {
        IndividualTinGenerationEntity individualTinGenerationEntity = modelMapper.map(individualTinGenerationDto, IndividualTinGenerationEntity.class);

        JTBIndividualTinGenerationResponse individualTinGenerationResponse = jtbClientProxy.getIndividualTinGeneration(jtbTaxpayerRequest, getLoginResponseEntity().getTokenId());
        IndividualTinGenerationEntity individualTinGenerationEntity = modelMapper.map(individualTinGenerationRespons, IndividualTinGenerationEntity.class);


        return modelMapper.map(individualTinGenerationRepository.save(individualTinGenerationEntity), IndividualTinGemerationDto.class);
    }




    @Override
    public IndividualTinGenerationDto updateIndividualTinGeneration(IndividualTinGenerationDto individualTinGenerationDto) {
        IndividualTinGenerationDto oldIndividualTinGenerationDto = abortIfIndividualDoesNotExist(individualTinGenerationDto.getId());
        modelMapper.map(individualTinGenerationDto, oldIndividualTinGenerationDto);

        IndividualTinGenerationEntity individualTinGenerationEntity = modelMapper.map(oldIndividualTinGenerationDto, IndividualTinGenerationEntity.class);

        return modelMapper.map(individualRepository.save(individualTinGenerationEntity), IndividualTinGenerationDto.class);
    }



    @Override
    public IndividualTinGenerationDto getIndividualTinGenerationDetails(Long id) {
        return abortIfIndividualDoesNotExist(id);
    }




    @Override
    @Transactional
    public PageDetail<IndividualTinGenerationResponse> getAllIndividualTinGenerations(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").descending());
        Page<IndividualTinGenerationEntity> pages = individualTinGenerationRepository.findAll(pageable);
        Paging paging = new Paging();
        paging.setPage(pages.getNumber() + 1);
        paging.setPageSize(pages.getSize());
        paging.setTotalPages(pages.getTotalPages());
        paging.setTotalRecord(pages.getTotalElements());

        PageDetail<IndividualTinGenerationResponse> pageDetail = new PageDetail<>();
        pageDetail.setPaging(paging);
        pageDetail.setRequests(pages.getContent().stream().map(x -> modelMapper.map(x, IndividualTinGenerationResponse.class)).collect(Collectors.toList()));

        return pageDetail;
    }



    @Override
    @Transactional
    public IndividualTinGenerationDto findIndividualTinGenerationByKeyword(String search) {
        Optional<IndividualTinGeneratinEntity> optionalIndividualTinGeneration = Optional.ofNullable(individualTinGenerationRepository.findIndividualTinGenerationByKeyword(search));

        if (!optionalIndividualTinGeneration.isPresent() && search.length() == 10 && search.chars().allMatch(Character::isDigit)) {
            JTBTaxpayerRequest jtbTaxpayerRequest = getJtbTaxpayerRequest(search.trim());
            JTBIndividualTinGenerationResponse individualTinGenerationResponse = jtbClientProxy.getIndividualTinGeneration(jtbTaxpayerRequest, getLoginResponseEntity().getTokenId());

            if(individualTinGenerationResponse.getResponseCode().equals("001")) {
                IndividualTaxpayer taxpayer = individualTinGenerationResponse.getTaxpayer();
                IndividualTinGenerationEntity individualTinGenerationEntity = modelMapper.map(taxpayer, IndividualTinGenerationEntity.class);
                individualTinGenerationEntity.setJtbTin(taxpayer.getTin());

                optionalIndividualTinGeneration = Optional.of(individualTinGenerationRepository.save(individualTinGenerationEntity));
            }
        }

        IndividualTinGenerationEntity individualTinGenerationEntity = optionalIndividual.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));

        return modelMapper.map(individualTinGenerationEntity, IndividualTinGenerationDto.class)
    }



    private IndividualTinGenerationDto abortIfIndividualDoesNotExist(Long id) {
        Optional<IndividualTinGenerationEntity> optionalIndividualTinGeneration = individualTinGenerationRepository.findById(id);

        IndividualTinGenerationEntity individualTinGeneration = optionalIndividualTinGeneration.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
        return modelMapper.map(individualTinGeneration, IndividualTinGenerationDto.class);
    }


}
