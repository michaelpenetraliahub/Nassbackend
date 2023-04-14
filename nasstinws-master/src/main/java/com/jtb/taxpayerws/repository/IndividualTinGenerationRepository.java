package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.IndividualEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



import java.time.LocalDate;



public interface IndividualTinGenerationRepository extends JpaRepository<IndividualTinGenerationEntity, Long>, JpaSpecificationExecutor<IndividualTinGenerationEntity> {




}
