package com.jtb.taxpayerws.service;


import com.jtb.taxpayerws.dto.IndividualDto;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.response.IndividualResponse;


public interface IndividualService {

    IndividualDto createIndividual(IndividualDto individualDto);

    IndividualDto updateIndividual(IndividualDto individualDto);

    IndividualDto getIndividualDetails(Long id);

    PageDetail<IndividualResponse> getAllIndividuals(int page, int limit);

    IndividualDto findIndividualByKeyword(String search);


}
