package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.dto.AssetDetailDto;
import com.jtb.taxpayerws.dto.IndividualAssetDetailDto;
import com.jtb.taxpayerws.entity.IndividualAssetDetailEntity;
import com.jtb.taxpayerws.entity.IndividualEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.repository.IndividualAssetDetailRepository;
import com.jtb.taxpayerws.repository.IndividualRepository;
import com.jtb.taxpayerws.service.IndividualAssetDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IndividualAssetDetailServiceImpl implements IndividualAssetDetailService {

    private final IndividualAssetDetailRepository individualAssetDetailRepository;
    private final IndividualRepository individualRepository;
    private final ModelMapper modelMapper;

    @Value("${security.config.secret}")
    private String secret;

    public IndividualAssetDetailServiceImpl(IndividualAssetDetailRepository individualAssetDetailRepository, IndividualRepository individualRepository, ModelMapper modelMapper) {
        this.individualAssetDetailRepository = individualAssetDetailRepository;
        this.individualRepository = individualRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public AssetDetailDto addAssetDetail(AssetDetailDto assetDetailDto, String token) {
        if (!token.equals(secret)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, ErrorInfo.UNAUTHORIZED_RESOURCE.getErrorMessage());
        }
        String tin = assetDetailDto.getTin();

        if (Optional.ofNullable(individualAssetDetailRepository.findByTin(tin)).isPresent()) {
            individualAssetDetailRepository.deleteByTin(tin);
        }

        IndividualEntity individual = abortIfIndividualDoesNotExist(tin);
        IndividualAssetDetailEntity individualAssetDetail = modelMapper.map(assetDetailDto, IndividualAssetDetailEntity.class);
        individualAssetDetail.setIndividual(individual);

        return modelMapper.map(individualAssetDetailRepository.save(individualAssetDetail), IndividualAssetDetailDto.class);
    }

    @Override
    public AssetDetailDto getAssetDetail(String tin) {
        return modelMapper.map(abortIfAssetDoesNotExist(tin), IndividualAssetDetailDto.class);
    }

    private IndividualEntity abortIfIndividualDoesNotExist(String tin) {
        Optional<IndividualEntity> optionalIndividual = Optional.ofNullable(individualRepository.findByJtbTin(tin));

        return optionalIndividual.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
    }

    private IndividualAssetDetailEntity abortIfAssetDoesNotExist(String tin) {
        Optional<IndividualAssetDetailEntity> optionalAsset = Optional.ofNullable(individualAssetDetailRepository.findByTin(tin));

        return optionalAsset.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorInfo.RECORD_DOES_NOT_EXIST.getErrorMessage()));
    }
}
