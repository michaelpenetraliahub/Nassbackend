package com.jtb.taxpayerws.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NonIndividualTinGenerationResponse{

    private Long id;


    @JsonProperty("registration_number")
    private String RegNumber;

    @JsonProperty("tin")
    private Int tin;

    @JsonProperty("name")
    private String name;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegNumber() {
        return RegNumber;
    }

    public void setRegNumber(String regNumber) {
        this.RegNumber = regNumber;
    }

    public Int getTin() {
        return tin;
    }

    public void setTin(Int tin) {
        this.tin = tin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
