package com.jtb.taxpayerws.repository;

import com.jtb.taxpayerws.entity.AuditTrailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditTrailRepository extends JpaRepository<AuditTrailEntity, Long> {
}
