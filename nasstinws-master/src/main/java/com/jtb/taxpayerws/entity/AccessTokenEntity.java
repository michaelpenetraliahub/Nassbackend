package com.jtb.taxpayerws.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name = "access_token")
public class AccessTokenEntity {
    @Id
    @Column(name = "token", nullable = false)
    private String token;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "refresh_token", referencedColumnName = "token", nullable = false)
    private RefreshTokenEntity refreshToken;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    public AccessTokenEntity(String token, RefreshTokenEntity refreshToken, UserEntity user, LocalDateTime createdAt, LocalDateTime expiryDate) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.user = user;
        this.createdAt = createdAt;
        this.expiryDate = expiryDate;
    }

    public AccessTokenEntity() {
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

    public RefreshTokenEntity getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshTokenEntity refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
