package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.ReportRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.IndividualResponse;
import com.jtb.taxpayerws.payload.response.NonIndividualResponse;
import com.jtb.taxpayerws.service.ReportService;
import com.jtb.taxpayerws.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("reports")
public class ReportController {
    private final ReportService reportService;
    private final ModelMapper modelMapper;

    public ReportController(ReportService reportService, ModelMapper modelMapper) {
        this.reportService = reportService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "individual", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<IndividualResponse>>> getIndividualReports(@RequestBody @Valid ApiRequest<ReportRequest> request) {
        ReportRequest reportRequest = request.getData();
        List<IndividualResponse> individuals = reportService.getIndividualReports(reportRequest.getKeyword(), reportRequest.getFrom(), reportRequest.getTo()).stream().map(x -> modelMapper.map(x, IndividualResponse.class)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(individuals));
    }

    @PostMapping(value = "nonindividual", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<NonIndividualResponse>>> getNonIndividualReports(@RequestBody @Valid ApiRequest<ReportRequest> request) {
        ReportRequest reportRequest = request.getData();
        List<NonIndividualResponse> nonIndividuals = reportService.getNonIndividualReports(reportRequest.getKeyword(), reportRequest.getFrom(), reportRequest.getTo()).stream().map(x -> modelMapper.map(x, NonIndividualResponse.class)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(nonIndividuals));
    }
}
