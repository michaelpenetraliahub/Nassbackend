package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.dto.AuditTrailDto;
import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.AuditTrailRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.service.AuditTrailService;
import com.jtb.taxpayerws.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("audit")
public class AuditTrailController {
    private final AuditTrailService auditTrailService;
    private final ModelMapper modelMapper;

    public AuditTrailController(AuditTrailService auditTrailService, ModelMapper modelMapper) {
        this.auditTrailService = auditTrailService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity log(@RequestBody ApiRequest<AuditTrailRequest> request) {
        auditTrailService.log(modelMapper.map(request.getData(), AuditTrailDto.class));

        return ResponseEntity.ok().build();
    }
}
