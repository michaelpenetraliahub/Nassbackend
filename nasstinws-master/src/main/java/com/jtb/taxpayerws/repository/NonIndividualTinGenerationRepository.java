package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.IndividualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;




public interface NonIndividualTinGenerationRepository extends JpaRepository<NonIndividualTinGenerationEntity, Long>, JpaSpecificationExecutor<NonIndividualTinGenerationEntity> {



}
