package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.dto.AssetDetailDto;
import com.jtb.taxpayerws.dto.IndividualAssetDetailDto;
import com.jtb.taxpayerws.dto.NonIndividualAssetDetailDto;
import com.jtb.taxpayerws.entity.NonIndividualAssetDetailEntity;
import com.jtb.taxpayerws.entity.NonIndividualEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.repository.NonIndividualAssetDetailRepository;
import com.jtb.taxpayerws.repository.NonIndividualRepository;
import com.jtb.taxpayerws.service.NonIndividualAssetDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NonIndividualAssetDetailServiceImpl implements NonIndividualAssetDetailService {

    private final NonIndividualAssetDetailRepository nonIndividualAssetDetailRepository;
    private final NonIndividualRepository nonIndividualRepository;
    private final ModelMapper modelMapper;

    @Value("${security.config.secret}")
    private String secret;

    public NonIndividualAssetDetailServiceImpl(NonIndividualAssetDetailRepository nonIndividualAssetDetailRepository, NonIndividualRepository nonIndividualRepository, ModelMapper modelMapper) {
        this.nonIndividualAssetDetailRepository = nonIndividualAssetDetailRepository;
        this.nonIndividualRepository = nonIndividualRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public AssetDetailDto addAssetDetail(AssetDetailDto assetDetailDto, String token) {
        if (!token.equals(secret)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorInfo.UNAUTHORIZED_RESOURCE.getErrorMessage());
        }
        String tin = assetDetailDto.getTin();

        if (Optional.ofNullable(nonIndividualAssetDetailRepository.findByTin(tin)).isPresent()) {
            nonIndividualAssetDetailRepository.deleteByTin(tin);
        }

        NonIndividualEntity nonIndividual = abortIfNonIndividualDoesNotExist(tin);
        NonIndividualAssetDetailEntity nonIndividualAssetDetail = modelMapper.map(assetDetailDto, NonIndividualAssetDetailEntity.class);
        nonIndividualAssetDetail.setNonIndividual(nonIndividual);

        return modelMapper.map(nonIndividualAssetDetailRepository.save(nonIndividualAssetDetail), NonIndividualAssetDetailDto.class);
    }

    @Override
    public AssetDetailDto getAssetDetail(String tin) {
        return modelMapper.map(abortIfAssetDoesNotExist(tin), NonIndividualAssetDetailDto.class);
    }

    private NonIndividualEntity abortIfNonIndividualDoesNotExist(String tin) {
        Optional<NonIndividualEntity> optionalNonIndividual = Optional.ofNullable(nonIndividualRepository.findByCurrentTin(tin));

        return optionalNonIndividual.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
    }

    private NonIndividualAssetDetailEntity abortIfAssetDoesNotExist(String tin) {
        Optional<NonIndividualAssetDetailEntity> optionalAsset = Optional.ofNullable(nonIndividualAssetDetailRepository.findByTin(tin));

        return optionalAsset.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
    }
}
