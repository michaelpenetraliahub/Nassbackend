package com.jtb.taxpayerws.client.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Taxpayer {
    private String tin;

    @JsonProperty(value = "phone_no")
    private String phoneNo;

    private String email;

    @JsonProperty(value = "date_of_registration")
    private String dateOfReg;

    @JsonProperty(value = "tax_authority")
    private String taxAuthorityName;

    @JsonProperty(value = "tax_office")
    private String taxOffice;

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfReg() {
        return dateOfReg;
    }

    public void setDateOfReg(String dateOfReg) {
        this.dateOfReg = dateOfReg;
    }

    public String getTaxAuthorityName() {
        return taxAuthorityName;
    }

    public void setTaxAuthorityName(String taxAuthorityName) {
        this.taxAuthorityName = taxAuthorityName;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }
}
