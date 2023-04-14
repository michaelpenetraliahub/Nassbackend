package com.jtb.taxpayerws.client.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class JTBNonIndividualResponse implements Serializable {

    @JsonProperty(value = "ResponseCode")
    private String responseCode;

    @JsonProperty(value = "ResponseDescription")
    private String responseDescription;

    @JsonProperty(value = "Taxpayer")
    private NonIndividualTaxpayer taxpayer;

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

    public NonIndividualTaxpayer getTaxpayer() {
        return taxpayer;
    }

    public void setTaxpayer(NonIndividualTaxpayer taxpayer) {
        this.taxpayer = taxpayer;
    }
}
