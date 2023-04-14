package com.jtb.taxpayerws.client.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndividualTaxpayer extends Taxpayer {
    @JsonProperty(value = "first_name")
    private String firstname;

    @JsonProperty(value = "last_name")
    private String lastname;

    @JsonProperty(value = "middle_name")
    private String middlename;

    @JsonProperty(value = "date_of_birth")
    private String dob;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
