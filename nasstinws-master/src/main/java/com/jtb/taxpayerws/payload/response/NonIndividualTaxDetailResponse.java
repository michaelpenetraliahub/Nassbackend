package com.jtb.taxpayerws.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class NonIndividualTaxDetailResponse {
    private Long id;

    @JsonProperty("jtb_tin")
    private String jtbTin;

    @JsonProperty("old_tin")
    private String oldTin;

    @JsonProperty("tax_number")
    private String taxNumber;

    @JsonProperty("tax_period")
    private String taxPeriod;

    @JsonProperty("assessable_profit")
    private String assessableProfit;

    @JsonProperty("total_profit")
    private String totalProfit;

    @JsonProperty("tax_payable")
    private String taxPayable;

    @JsonProperty("tax_paid")
    private String taxPaid;

    @JsonProperty("payment_date")
    private LocalDate paymentDate;

    @JsonProperty("tax_type")
    private String taxType;

    @JsonProperty("tax_office")
    private String taxOffice;

    @JsonProperty("tax_authority")
    private String taxAuthority;

    @JsonProperty("non_individual_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nonIndividualId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJtbTin() {
        return jtbTin;
    }

    public void setJtbTin(String jtbTin) {
        this.jtbTin = jtbTin;
    }

    public String getOldTin() {
        return oldTin;
    }

    public void setOldTin(String oldTin) {
        this.oldTin = oldTin;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getTaxPeriod() {
        return taxPeriod;
    }

    public void setTaxPeriod(String taxPeriod) {
        this.taxPeriod = taxPeriod;
    }

    public String getAssessableProfit() {
        return assessableProfit;
    }

    public void setAssessableProfit(String assessableProfit) {
        this.assessableProfit = assessableProfit;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getTaxPayable() {
        return taxPayable;
    }

    public void setTaxPayable(String taxPayable) {
        this.taxPayable = taxPayable;
    }

    public String getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(String taxPaid) {
        this.taxPaid = taxPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public String getNonIndividualId() {
        return nonIndividualId;
    }

    public void setNonIndividualId(String nonIndividualId) {
        this.nonIndividualId = nonIndividualId;
    }
}
