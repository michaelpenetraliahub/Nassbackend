package com.jtb.taxpayerws.service;

import com.jtb.taxpayerws.dto.IndividualDto;
import com.jtb.taxpayerws.dto.NonIndividualDto;

import java.util.List;

public interface ReportService {
    List<IndividualDto> getIndividualReports(String keyword, String from, String to);

    List<NonIndividualDto> getNonIndividualReports(String keyword, String from, String to);
}
