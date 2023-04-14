package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.dto.IndividualTaxDetailDto;
import com.jtb.taxpayerws.dto.TaxDetailDto;
import com.jtb.taxpayerws.entity.IndividualEntity;
import com.jtb.taxpayerws.entity.IndividualTaxDetailEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.repository.IndividualRepository;
import com.jtb.taxpayerws.repository.IndividualTaxDetailRepository;
import com.jtb.taxpayerws.service.IndividualTaxDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IndividualTaxDetailServiceImpl implements IndividualTaxDetailService {

    private final IndividualTaxDetailRepository individualTaxDetailRepository;
    private final IndividualRepository individualRepository;
    private final ModelMapper modelMapper;

    @Value("${security.config.secret}")
    private String secret;

    public IndividualTaxDetailServiceImpl(IndividualTaxDetailRepository individualTaxDetailRepository, IndividualRepository individualRepository, ModelMapper modelMapper) {
        this.individualTaxDetailRepository = individualTaxDetailRepository;
        this.individualRepository = individualRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public TaxDetailDto addTaxDetail(TaxDetailDto taxDetailDto, String token) {
        if (!token.equals(secret)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorInfo.UNAUTHORIZED_RESOURCE.getErrorMessage());
        }
        String tin = taxDetailDto.getJtbTin();

        if (Optional.ofNullable(individualTaxDetailRepository.findByJtbTin(tin)).isPresent()) {
            individualTaxDetailRepository.deleteByJtbTin(tin);
        }

        IndividualEntity individual = abortIfIndividualDoesNotExist(tin);
        IndividualTaxDetailEntity individualTaxDetail = modelMapper.map(taxDetailDto, IndividualTaxDetailEntity.class);
        individualTaxDetail.setIndividual(individual);

        return modelMapper.map(individualTaxDetailRepository.save(individualTaxDetail), IndividualTaxDetailDto.class);
    }

    @Override
    public TaxDetailDto getTaxDetail(String tin) {
        return modelMapper.map(abortIfTaxDoesNotExist(tin), IndividualTaxDetailDto.class);
    }

    private IndividualEntity abortIfIndividualDoesNotExist(String tin) {
        Optional<IndividualEntity> optionalIndividual = Optional.ofNullable(individualRepository.findByJtbTin(tin));

        return optionalIndividual.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
    }

    private IndividualTaxDetailEntity abortIfTaxDoesNotExist(String tin) {
        Optional<IndividualTaxDetailEntity> optionalTax = Optional.ofNullable(individualTaxDetailRepository.findByJtbTin(tin));

        return optionalTax.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
    }
}
