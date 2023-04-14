package com.jtb.taxpayerws.dto;

import java.util.List;

public class DashboardDto {
    private List<Long> individualChart;
    private List<Long> nonIndividualChart;
    private Long totalIndividuals;
    private Long totalNonIndividuals;
    private Long totalIndividualsToday;
    private Long totalNonIndividualsToday;

    public List<Long> getIndividualChart() {
        return individualChart;
    }

    public void setIndividualChart(List<Long> individualChart) {
        this.individualChart = individualChart;
    }

    public List<Long> getNonIndividualChart() {
        return nonIndividualChart;
    }

    public void setNonIndividualChart(List<Long> nonIndividualChart) {
        this.nonIndividualChart = nonIndividualChart;
    }

    public Long getTotalIndividuals() {
        return totalIndividuals;
    }

    public void setTotalIndividuals(Long totalIndividuals) {
        this.totalIndividuals = totalIndividuals;
    }

    public Long getTotalNonIndividuals() {
        return totalNonIndividuals;
    }

    public void setTotalNonIndividuals(Long totalNonIndividuals) {
        this.totalNonIndividuals = totalNonIndividuals;
    }

    public Long getTotalIndividualsToday() {
        return totalIndividualsToday;
    }

    public void setTotalIndividualsToday(Long totalIndividualsToday) {
        this.totalIndividualsToday = totalIndividualsToday;
    }

    public Long getTotalNonIndividualsToday() {
        return totalNonIndividualsToday;
    }

    public void setTotalNonIndividualsToday(Long totalNonIndividualsToday) {
        this.totalNonIndividualsToday = totalNonIndividualsToday;
    }
}
