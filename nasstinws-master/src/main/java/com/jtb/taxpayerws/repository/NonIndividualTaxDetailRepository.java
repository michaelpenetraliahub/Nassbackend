package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.NonIndividualTaxDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonIndividualTaxDetailRepository extends JpaRepository<NonIndividualTaxDetailEntity, Long> {
    NonIndividualTaxDetailEntity findByJtbTin(String tin);
    void deleteByJtbTin(String tin);
}
