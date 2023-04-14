package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.IndividualTaxDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualTaxDetailRepository extends JpaRepository<IndividualTaxDetailEntity, Long> {
    IndividualTaxDetailEntity findByJtbTin(String tin);
    void deleteByJtbTin(String tin);
}
