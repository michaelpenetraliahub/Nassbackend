package com.jtb.taxpayerws.service;

import com.jtb.taxpayerws.dto.AuditTrailDto;

public interface AuditTrailService {
    void log(AuditTrailDto auditTrailDto);
}
