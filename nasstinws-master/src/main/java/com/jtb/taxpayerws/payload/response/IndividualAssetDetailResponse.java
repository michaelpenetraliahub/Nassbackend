package com.jtb.taxpayerws.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class IndividualAssetDetailResponse {
    private Long id;
    private String tin;
    private String location;

    @JsonProperty("asset_type")
    private String assetType;

    @JsonProperty("asset_value")
    private String assetValue;

    @JsonProperty("date_acquired")
    private LocalDate dateAcquired;
    private String description;

    @JsonProperty("individual_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String individualId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(String assetValue) {
        this.assetValue = assetValue;
    }

    public LocalDate getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(LocalDate dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }
}
