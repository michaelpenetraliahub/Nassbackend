package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.dto.AuditTrailDto;
import com.jtb.taxpayerws.entity.AuditTrailEntity;
import com.jtb.taxpayerws.repository.AuditTrailRepository;
import com.jtb.taxpayerws.service.AuditTrailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {
    private final ModelMapper modelMapper;
    private final AuditTrailRepository auditTrailRepository;

    public AuditTrailServiceImpl(ModelMapper modelMapper, AuditTrailRepository auditTrailRepository) {
        this.modelMapper = modelMapper;
        this.auditTrailRepository = auditTrailRepository;
    }

    @Override
    public void log(AuditTrailDto auditTrailDto) {
        AuditTrailEntity auditTrailEntity = modelMapper.map(auditTrailDto, AuditTrailEntity.class);

        auditTrailRepository.save(auditTrailEntity);
    }
}
