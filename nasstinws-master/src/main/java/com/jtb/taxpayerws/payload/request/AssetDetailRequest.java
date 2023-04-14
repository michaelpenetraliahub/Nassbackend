package com.jtb.taxpayerws.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssetDetailRequest {
    private String tin;
    private String location;

    @JsonProperty("asset_type")
    private String assetType;

    @JsonProperty("asset_value")
    private String assetValue;

    @JsonProperty("asset_acquired")
    private String dateAcquiredStr;
    private String description;

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

    public String getDateAcquiredStr() {
        return dateAcquiredStr;
    }

    public void setDateAcquiredStr(String dateAcquiredStr) {
        this.dateAcquiredStr = dateAcquiredStr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
