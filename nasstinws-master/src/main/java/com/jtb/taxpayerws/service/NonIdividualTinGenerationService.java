package com.jtb.taxpayerws.service;

import com.jtb.taxpayerws.dto.NonIndividualTinGenerationDto;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.response.NonIndividualTinGenerationResponse;

public interface NonIndividualTinGenerationService{
    NonIndividualTinGenerationDto createNonIndividualTinGeneration(NonIndividualTinGenerationDto nonIndividualTinGenerationDto);

    NonIndividualTinGenerationDto updateNonIndividualTinGeneration(NonIndividualTinGenerationDto nonIndividualTinGenerationDto);

    NonIndividualTinGenerationDto getNonIndividualTinGenerationDetails(Long id);

    PageDetail<NonIndividualResponse> getAllNonIndividualTinGenerations(int page, int limit);

    NonIndividualTinGenerationDto findNonIndividualTinGenerationByKeyword(String search);
}
