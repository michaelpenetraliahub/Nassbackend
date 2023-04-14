package com.jtb.taxpayerws.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DashboardResponse {
    @JsonProperty("individual_chart")
    private List<Long> individualChart;

    @JsonProperty("non_individual_chart")
    private List<Long> nonIndividualChart;

    @JsonProperty("total_individuals")
    private Long totalIndividuals;

    @JsonProperty("total_non_individuals")
    private Long totalNonIndividuals;

    @JsonProperty("total_individuals_today")
    private Long totalIndividualsToday;

    @JsonProperty("total_non_individuals_today")
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
