package com.jtb.taxpayerws.client.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NonIndividualTaxpayer extends Taxpayer {
    @JsonProperty(value = "registered_name")
    private String registeredName;

    @JsonProperty(value = "registration_number")
    private String registrationNumber;

    @JsonProperty(value = "date_of_incorporation")
    private String dateOfInc;

    public String getRegisteredName() {
        return registeredName;
    }

    public void setRegisteredName(String registeredName) {
        this.registeredName = registeredName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDateOfInc() {
        return dateOfInc;
    }

    public void setDateOfInc(String dateOfInc) {
        this.dateOfInc = dateOfInc;
    }
}
