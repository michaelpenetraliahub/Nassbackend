package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findByToken(String token);
    Boolean existsByToken(String token);
    Long deleteByToken(String token);
}
