package com.jtb.taxpayerws.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EditNonIndividualRequest {
    private Long id;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @Email
    @JsonProperty("email_address")
    private String emailAddress;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String city;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String lga;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String street;

    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    @JsonProperty("house_no")
    private String houseNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }
}
