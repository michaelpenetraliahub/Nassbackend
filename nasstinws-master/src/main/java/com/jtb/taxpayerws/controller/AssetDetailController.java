package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.dto.IndividualAssetDetailDto;
import com.jtb.taxpayerws.dto.NonIndividualAssetDetailDto;
import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.AssetDetailRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.IndividualAssetDetailResponse;
import com.jtb.taxpayerws.payload.response.NonIndividualAssetDetailResponse;
import com.jtb.taxpayerws.service.IndividualAssetDetailService;
import com.jtb.taxpayerws.service.NonIndividualAssetDetailService;
import com.jtb.taxpayerws.utils.DateUtil;
import com.jtb.taxpayerws.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asset")
public class AssetDetailController {
    private final IndividualAssetDetailService individualAssetDetailService;
    private final NonIndividualAssetDetailService nonIndividualAssetDetailService;
    private final ModelMapper modelMapper;

    public AssetDetailController(IndividualAssetDetailService individualAssetDetailService, NonIndividualAssetDetailService nonIndividualAssetDetailService, ModelMapper modelMapper) {
        this.individualAssetDetailService = individualAssetDetailService;
        this.nonIndividualAssetDetailService = nonIndividualAssetDetailService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/individual", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualAssetDetailResponse>> createIndividualAsset(@RequestBody ApiRequest<AssetDetailRequest> request, @RequestParam("token") String token) {
        IndividualAssetDetailDto individualAssetDto = modelMapper.map(request.getData(), IndividualAssetDetailDto.class);
        individualAssetDto.setDateAcquired(DateUtil.parseStringToDate(request.getData().getDateAcquiredStr()));

        IndividualAssetDetailResponse response = modelMapper.map(individualAssetDetailService.addAssetDetail(individualAssetDto, token), IndividualAssetDetailResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildCreatedResponse(response));
    }

    @GetMapping(value = "individual/{tin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualAssetDetailResponse>> getIndividual(@PathVariable("tin") String tin) {
        IndividualAssetDetailResponse response = modelMapper.map(individualAssetDetailService.getAssetDetail(tin), IndividualAssetDetailResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @PostMapping(value = "/nonindividual", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualAssetDetailResponse>> createNonIndividualAsset(@RequestBody ApiRequest<AssetDetailRequest> request, @RequestParam("token") String token) {
        NonIndividualAssetDetailDto nonIndividualAssetDto = modelMapper.map(request.getData(), NonIndividualAssetDetailDto.class);
        nonIndividualAssetDto.setDateAcquired(DateUtil.parseStringToDate(request.getData().getDateAcquiredStr()));

    NonIndividualAssetDetailResponse response = modelMapper.map(nonIndividualAssetDetailService.addAssetDetail(nonIndividualAssetDto, token), NonIndividualAssetDetailResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildCreatedResponse(response));
    }

    @GetMapping(value = "nonindividual/{tin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualAssetDetailResponse>> getNonIndividual(@PathVariable("tin") String tin) {
        NonIndividualAssetDetailResponse response = modelMapper.map(nonIndividualAssetDetailService.getAssetDetail(tin), NonIndividualAssetDetailResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }
}
