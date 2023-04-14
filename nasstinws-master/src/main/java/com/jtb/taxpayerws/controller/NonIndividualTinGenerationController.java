package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.dto.NonIndividualTinGenerationDto;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.EditNonIndividualTinGenerationRequest;
import com.jtb.taxpayerws.payload.request.NonIndividualTinGenerationRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.NonIndividualTinGenerationResponse;
import com.jtb.taxpayerws.service.NonIndividualTinGenerationService;
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
@RequestMapping("nonindividualtingeneration")
public class NonIndividualController {
    private final NonIndividualTinGenerationService nonIndividualTinGenerationService;
    private final ModelMapper modelMapper;

    public NonIndividualTinGenerationController(NonIndividualTinGenerationService nonIndividualTinGenerationService, ModelMapper modelMapper) {
        this.nonIndividualTinGenerationService = nonIndividualTinGenerationService;
        this.modelMapper = modelMapper;
    }




    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualTinGenerationResponse>> createNonIndividualTinGeneration(@RequestBody ApiRequest<NonIndividualTinGenerationRequest> nonIndividualTinGenerationRequest) {
        NonIndividualTinGenerationDto nonIndividualTinGenerationDto = modelMapper.map(nonIndividualTinGenerationRequest.getData(), NonIndividualTinGenerationDto.class);
        NonIndividualTinGenerationResponse response = modelMapper.map(nonIndividualTinGenerationService.createNonIndividualTinGeneration(nonIndividualTinGenerationDto), NonIndividualTinGenerationResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildCreatedResponse(response));
    }




    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualTinGenerationResponse>> updateNonIndividualTinGeneration(@RequestBody @Valid ApiRequest<EditNonIndividualTinGenerationRequest> NonindividualTinGenerationRequest, @PathVariable("id") Long id) {
        NonIndividualTinGenerationDto nonIndividualTinGenerationDto = modelMapper.map(nonIndividualTinGenerationRequest.getData(), NonIndividualTinGenerationDto.class);
        nonIndividualTinGenerationDto.setId(id);
        NonIndividualTinGenerationResponse response = modelMapper.map(nonIndividualTinGenerationService.updateNonIndividualTinGeneration(nonIndividualTinGenerationDto), NonIndividualTinGenerationResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }




    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualTinGenerationResponse>> getNonIndividualTinGeneration(@PathVariable("id") Long id) {
        NonIndividualTinGenerationResponse response = modelMapper.map(nonIndividualTinGenerationService.getNonIndividualTinGenerationDetails(id), NonIndividualTinGenerationResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }




    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PageDetail>> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(nonIndividualTinGenerationService.getAllNonIndividualTinGenerations(page, limit)));
    }




    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualTinGenerationResponse>> findNonIndividualTinGeneration(
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        NonIndividualTinGenerationResponse response = modelMapper.map(nonIndividualTinGenerationService.findNonIndividualTinGenerationByKeyword(keyword), NonIndividualTinGenerationResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

}
