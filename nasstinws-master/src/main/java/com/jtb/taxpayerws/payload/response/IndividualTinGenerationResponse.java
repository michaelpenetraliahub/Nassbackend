package com.jtb.taxpayerws.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class IndividualTinGenerationResponse{

    private Long id;

    @JsonProperty("bvn")
    private Int bvn;

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

    public Int getBvn() {
        return bvn;
    }

    public void setBvn(Int bvn) {
        this.bvn = bvn;
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
