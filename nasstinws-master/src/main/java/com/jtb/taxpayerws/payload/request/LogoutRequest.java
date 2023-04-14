package com.jtb.taxpayerws.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LogoutRequest {
    @JsonProperty("refresh_token")
    @NotBlank(message = "{required.field}")
    @NotNull(message = "{notnull.field}")
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
