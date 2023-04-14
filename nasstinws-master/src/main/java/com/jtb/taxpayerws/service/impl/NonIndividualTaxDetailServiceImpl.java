package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.dto.IndividualTaxDetailDto;
import com.jtb.taxpayerws.dto.NonIndividualTaxDetailDto;
import com.jtb.taxpayerws.dto.TaxDetailDto;
import com.jtb.taxpayerws.entity.NonIndividualEntity;
import com.jtb.taxpayerws.entity.NonIndividualTaxDetailEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.repository.NonIndividualRepository;
import com.jtb.taxpayerws.repository.NonIndividualTaxDetailRepository;
import com.jtb.taxpayerws.service.NonIndividualTaxDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NonIndividualTaxDetailServiceImpl implements NonIndividualTaxDetailService {

    private final NonIndividualTaxDetailRepository nonIndividualTaxDetailRepository;
    private final NonIndividualRepository nonIndividualRepository;
    private final ModelMapper modelMapper;

    @Value("${security.config.secret}")
    private String secret;

    public NonIndividualTaxDetailServiceImpl(NonIndividualTaxDetailRepository nonIndividualTaxDetailRepository, NonIndividualRepository nonIndividualRepository, ModelMapper modelMapper) {
        this.nonIndividualTaxDetailRepository = nonIndividualTaxDetailRepository;
        this.nonIndividualRepository = nonIndividualRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public TaxDetailDto addTaxDetail(TaxDetailDto taxDetailDto, String token) {
        if (!token.equals(secret)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorInfo.UNAUTHORIZED_RESOURCE.getErrorMessage());
        }
        String tin = taxDetailDto.getJtbTin();

        if (Optional.ofNullable(nonIndividualTaxDetailRepository.findByJtbTin(tin)).isPresent()) {
            nonIndividualTaxDetailRepository.deleteByJtbTin(tin);
        }

        NonIndividualEntity nonIndividual = abortIfNonIndividualDoesNotExist(tin);
        NonIndividualTaxDetailEntity nonIndividualTaxDetail = modelMapper.map(taxDetailDto, NonIndividualTaxDetailEntity.class);
        nonIndividualTaxDetail.setNonIndividual(nonIndividual);

        return modelMapper.map(nonIndividualTaxDetailRepository.save(nonIndividualTaxDetail), NonIndividualTaxDetailDto.class);
    }

    @Override
    public TaxDetailDto getTaxDetail(String tin) {
        return modelMapper.map(abortIfTaxDoesNotExist(tin), IndividualTaxDetailDto.class);
    }

    private NonIndividualEntity abortIfNonIndividualDoesNotExist(String tin) {
        Optional<NonIndividualEntity> optionalNonIndividual = Optional.ofNullable(nonIndividualRepository.findByCurrentTin(tin));

        return optionalNonIndividual.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
    }

    private NonIndividualTaxDetailEntity abortIfTaxDoesNotExist(String tin) {
        Optional<NonIndividualTaxDetailEntity> optionalTax = Optional.ofNullable(nonIndividualTaxDetailRepository.findByJtbTin(tin));

        return optionalTax.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
    }
}
