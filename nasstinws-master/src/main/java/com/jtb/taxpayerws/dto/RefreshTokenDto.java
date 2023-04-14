package com.jtb.taxpayerws.dto;

import java.util.Set;

public class RefreshTokenDto {
    private String refreshToken;
    private String accessToken;
    private String email;
    private Long userId;
    private Set<String> roles;

    public RefreshTokenDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshTokenDto() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
