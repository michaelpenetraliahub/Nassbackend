package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.AccessTokenEntity;
import com.jtb.taxpayerws.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessTokenEntity, String> {
    Boolean existsByToken(String token);
    Long deleteByRefreshToken(RefreshTokenEntity refreshToken);
    Long deleteByToken(String token);
}
