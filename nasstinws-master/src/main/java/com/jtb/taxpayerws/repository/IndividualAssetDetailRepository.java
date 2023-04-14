package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.IndividualAssetDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualAssetDetailRepository extends JpaRepository<IndividualAssetDetailEntity, Long> {
    IndividualAssetDetailEntity findByTin(String tin);
    void deleteByTin(String tin);
}
