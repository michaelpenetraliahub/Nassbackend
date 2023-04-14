package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.dto.IndividualTaxDetailDto;
import com.jtb.taxpayerws.dto.NonIndividualTaxDetailDto;
import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.TaxDetailRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.IndividualTaxDetailResponse;
import com.jtb.taxpayerws.payload.response.NonIndividualTaxDetailResponse;
import com.jtb.taxpayerws.service.IndividualTaxDetailService;
import com.jtb.taxpayerws.service.NonIndividualTaxDetailService;
import com.jtb.taxpayerws.utils.DateUtil;
import com.jtb.taxpayerws.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tax")
public class TaxDetailController {
    private final IndividualTaxDetailService individualTaxDetailService;
    private final NonIndividualTaxDetailService nonIndividualTaxDetailService;
    private final ModelMapper modelMapper;

    public TaxDetailController(IndividualTaxDetailService individualTaxDetailService, NonIndividualTaxDetailService nonIndividualTaxDetailService, ModelMapper modelMapper) {
        this.individualTaxDetailService = individualTaxDetailService;
        this.nonIndividualTaxDetailService = nonIndividualTaxDetailService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/individual", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualTaxDetailResponse>> createIndividualTax(@RequestBody ApiRequest<TaxDetailRequest> request, @RequestParam("token") String token) {
        IndividualTaxDetailDto individualTaxDto = modelMapper.map(request.getData(), IndividualTaxDetailDto.class);
        individualTaxDto.setPaymentDate(DateUtil.parseStringToDate(request.getData().getPaymentDateStr()));

        IndividualTaxDetailResponse response = modelMapper.map(individualTaxDetailService.addTaxDetail(individualTaxDto, token), IndividualTaxDetailResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildCreatedResponse(response));
    }

    @GetMapping(value = "individual/{tin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualTaxDetailResponse>> getIndividual(@PathVariable("tin") String tin) {
        IndividualTaxDetailResponse response = modelMapper.map(individualTaxDetailService.getTaxDetail(tin), IndividualTaxDetailResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @PostMapping(value = "/nonindividual", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualTaxDetailResponse>> createNonIndividualTax(@RequestBody ApiRequest<TaxDetailRequest> request, @RequestParam("token") String token) {
        NonIndividualTaxDetailDto nonIndividualTaxDto = modelMapper.map(request.getData(), NonIndividualTaxDetailDto.class);
        nonIndividualTaxDto.setPaymentDate(DateUtil.parseStringToDate(request.getData().getPaymentDateStr()));

        NonIndividualTaxDetailResponse response = modelMapper.map(nonIndividualTaxDetailService.addTaxDetail(nonIndividualTaxDto, token), NonIndividualTaxDetailResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildCreatedResponse(response));
    }

    @GetMapping(value = "nonindividual/{tin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualTaxDetailResponse>> getNonIndividual(@PathVariable("tin") String tin) {
        NonIndividualTaxDetailResponse response = modelMapper.map(nonIndividualTaxDetailService.getTaxDetail(tin), NonIndividualTaxDetailResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }
}
