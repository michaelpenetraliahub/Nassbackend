package com.jtb.taxpayerws.service;


import com.jtb.taxpayerws.dto.IndividualTinGenerationDto;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.response.IndividualTinGenerationResponse;


public interface IndividualTinGenerationService {

    IndividualTinGenerationDto createIndividualTinGeneration(IndividualTinGenerationDto individualTinGenerationDto);

    IndividualTinGenerationDto updateIndividualTinGeneration(IndividualTinGenerationDto individualTinGenerationDto);

    IndividualTinGenerationDto getIndividualTinGenerationDetails(Long id);

    PageDetail<IndividualTinGenerationResponse> getAllIndividualTinGenerations(int page, int limit);

    IndividualTinGenerationDto findIndividualTinGenerationByKeyword(String search);


}
