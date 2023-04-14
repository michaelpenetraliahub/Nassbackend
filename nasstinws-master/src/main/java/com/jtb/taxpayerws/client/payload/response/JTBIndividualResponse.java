package com.jtb.taxpayerws.client.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class JTBIndividualResponse implements Serializable {

    @JsonProperty(value = "ResponseCode")
    private String responseCode;

    @JsonProperty(value = "ResponseDescription")
    private String responseDescription;

    @JsonProperty(value = "Taxpayer")
    private IndividualTaxpayer taxpayer;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public IndividualTaxpayer getTaxpayer() {
        return taxpayer;
    }

    public void setTaxpayer(IndividualTaxpayer taxpayer) {
        this.taxpayer = taxpayer;
    }
}