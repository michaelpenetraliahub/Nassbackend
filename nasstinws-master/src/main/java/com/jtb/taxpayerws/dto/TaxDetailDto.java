package com.jtb.taxpayerws.dto;

import java.time.LocalDate;

public class TaxDetailDto {
    private Long id;
    private String jtbTin;
    private String oldTin;
    private String taxNumber;
    private String taxPeriod;
    private String assessableProfit;
    private String totalProfit;
    private String taxPayable;
    private String taxPaid;
    private LocalDate paymentDate;
    private String taxType;
    private String taxOffice;
    private String taxAuthority;

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
}
