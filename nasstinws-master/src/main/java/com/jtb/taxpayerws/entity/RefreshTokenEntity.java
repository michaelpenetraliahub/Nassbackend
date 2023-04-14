package com.jtb.taxpayerws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name = "refresh_token")
public class RefreshTokenEntity {
    @Id
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    @OneToOne(mappedBy = "refreshToken")
    private AccessTokenEntity accessToken;

    public RefreshTokenEntity(String token, LocalDateTime createdAt, LocalDateTime expiryDate) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiryDate = expiryDate;
    }

    public RefreshTokenEntity() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public AccessTokenEntity getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessTokenEntity accessToken) {
        this.accessToken = accessToken;
    }
}
