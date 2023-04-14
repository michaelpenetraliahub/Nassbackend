package com.jtb.taxpayerws.service;

import com.jtb.taxpayerws.dto.AssetDetailDto;

public interface NonIndividualAssetDetailService {
    AssetDetailDto addAssetDetail(AssetDetailDto assetDetailDto, String token);

    AssetDetailDto getAssetDetail(String tin);
}
