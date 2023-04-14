package com.jtb.taxpayerws.service;

import com.jtb.taxpayerws.dto.NonIndividualDto;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.response.NonIndividualResponse;

public interface NonIndividualService {
    NonIndividualDto createNonIndividual(NonIndividualDto nonIndividualDto);

    NonIndividualDto updateNonIndividual(NonIndividualDto individualDto);

    NonIndividualDto getNonIndividualDetails(Long id);

    PageDetail<NonIndividualResponse> getAllNonIndividuals(int page, int limit);

    NonIndividualDto findNonIndividualByKeyword(String search);
}
