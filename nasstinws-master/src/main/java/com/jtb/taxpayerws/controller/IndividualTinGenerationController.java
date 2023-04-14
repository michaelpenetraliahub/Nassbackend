package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.dto.IndividualTinGenerationDto;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.EditIndividualTinGenerationRequest;
import com.jtb.taxpayerws.payload.request.IndividualTinGenerationRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.IndividualTinGenerationResponse;
import com.jtb.taxpayerws.service.IndividualTinGenerationService;
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
@RequestMapping("individualTinGeneration")
public class IndividualTinGenerationController {

    private final IndividualTinGenerationService individualTinGenerationService;
    private final ModelMapper modelMapper;

    public IndividualTinGenerationControllerController(IndividualTinGenerationService individualTinGenerationService, ModelMapper modelMapper) {
        this.individualTinGenerationService = individualTinGenerationService;
        this.modelMapper = modelMapper;
    }





    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualTinGenerationResponse>> createIndividualTinGeneration(@RequestBody ApiRequest<IndividualTinGenerationRequest> individualTinGenerationRequest) {
        IndividualTinGenerationDto individualTinGenerationDto = modelMapper.map(individualTinGenerationRequest.getData(), IndividualTinGenerationDto.class);
        IndividualTinGenerationResponse response = modelMapper.map(individualTinGenerationService.createIndividualTinGeneration(individualTinGenerationDto), IndividualTinGenerationResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildCreatedResponse(response));
    }




    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualTinGenerationResponse>> updateIndividualTinGeneration(@RequestBody @Valid ApiRequest<EditIndividualTinGenerationRequest> individualTinGenerationRequest, @PathVariable("id") Long id) {
        IndividualTinGenerationDto individualTinGenerationDto = modelMapper.map(individualTinGenerationRequest.getData(), IndividualTinGenerationDto.class);
        individualTinGenerationDto.setId(id);
        IndividualTinGenerationResponse response = modelMapper.map(individualTinGenerationService.updateIndividualTinGeneration(individualTinGenerationDto), IndividualTinGenerationResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }




    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualTinGenerationResponse>> getIndividualTinGeneration(@PathVariable("id") Long id) {
        IndividualTinGenerationResponse response = modelMapper.map(individualTinGenerationService.getIndividualTinGenerationDetails(id), IndividualTinGenerationResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PageDetail>> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(individualService.getAllIndividualTinGenerations(page, limit)));
    }




    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualTinGenerationResponse>> findIndividualTinGeneration(
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        IndividualTinGenerationResponse response = modelMapper.map(individualTinGenerationService.findIndividualTinGenerationByKeyword(keyword), IndividualTinGenerationResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

}
