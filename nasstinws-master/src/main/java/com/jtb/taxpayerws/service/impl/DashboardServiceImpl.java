package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.dto.DashboardDto;
import com.jtb.taxpayerws.repository.IndividualRepository;
import com.jtb.taxpayerws.repository.NonIndividualRepository;
import com.jtb.taxpayerws.service.DashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final IndividualRepository individualRepository;
    private final NonIndividualRepository nonIndividualRepository;

    public DashboardServiceImpl(IndividualRepository individualRepository, NonIndividualRepository nonIndividualRepository) {
        this.individualRepository = individualRepository;
        this.nonIndividualRepository = nonIndividualRepository;
    }

    @Override
    public DashboardDto getAllDashboardInfo() {
        DashboardDto dashboardDto = new DashboardDto();
        LocalDate yesterday = LocalDate.now().minusDays(1);

        dashboardDto.setIndividualChart(getIndividualChart());
        dashboardDto.setNonIndividualChart(getNonIndividualChart());
        dashboardDto.setTotalIndividuals(individualRepository.count());
        dashboardDto.setTotalNonIndividuals(nonIndividualRepository.count());
        dashboardDto.setTotalIndividualsToday(individualRepository.countByDateOfRegistration(yesterday));
        dashboardDto.setTotalNonIndividualsToday(nonIndividualRepository.countByDateOfRegistration(yesterday));

        return dashboardDto;
    }

    private List<Long> getIndividualChart() {
        Long[] recordCount = new Long[12];

        for (int i = 0; i < recordCount.length ; i++) {
            recordCount[i] = individualRepository.countRecordsByMonth(i + 1);
        }

      return Arrays.asList(recordCount);
    }

    private List<Long> getNonIndividualChart() {
        Long[] recordCount = new Long[12];

        for (int i = 0; i < recordCount.length ; i++) {
            recordCount[i] = nonIndividualRepository.countRecordsByMonth(i + 1);
        }

        return Arrays.asList(recordCount);
    }

}
