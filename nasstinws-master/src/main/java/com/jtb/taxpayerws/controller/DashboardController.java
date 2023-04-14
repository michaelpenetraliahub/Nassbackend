package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.DashboardResponse;
import com.jtb.taxpayerws.service.DashboardService;
import com.jtb.taxpayerws.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard")
public class DashboardController {
    private final DashboardService dashboardService;
    private final ModelMapper modelMapper;

    public DashboardController(DashboardService dashboardService, ModelMapper modelMapper) {
        this.dashboardService = dashboardService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<DashboardResponse>> getIndividual() {
        DashboardResponse response = modelMapper.map(dashboardService.getAllDashboardInfo(), DashboardResponse.class);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }
}
