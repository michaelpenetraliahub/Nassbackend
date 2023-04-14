package com.jtb.taxpayerws.dto;

public class NonIndividualTaxDetailDto extends TaxDetailDto {
    private String nonIndividualId;

    public String getNonIndividualId() {
        return nonIndividualId;
    }

    public void setNonIndividualId(String nonIndividualId) {
        this.nonIndividualId = nonIndividualId;
    }
}
