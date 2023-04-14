package com.jtb.taxpayerws.dto;

public class IndividualTaxDetailDto extends TaxDetailDto {
    private String individualId;

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }
}
