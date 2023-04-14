package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.NonIndividualAssetDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonIndividualAssetDetailRepository extends JpaRepository<NonIndividualAssetDetailEntity, Long> {
    NonIndividualAssetDetailEntity findByTin(String tin);
    void deleteByTin(String tin);
}
