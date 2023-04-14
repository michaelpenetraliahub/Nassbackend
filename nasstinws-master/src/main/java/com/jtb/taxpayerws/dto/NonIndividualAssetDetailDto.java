package com.jtb.taxpayerws.dto;

public class NonIndividualAssetDetailDto extends AssetDetailDto {
    private String nonIndividualId;

    public String getNonIndividualId() {
        return nonIndividualId;
    }

    public void setNonIndividualId(String nonIndividualId) {
        this.nonIndividualId = nonIndividualId;
    }
}
