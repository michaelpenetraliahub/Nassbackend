package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.dto.IndividualDto;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.EditIndividualRequest;
import com.jtb.taxpayerws.payload.request.IndividualRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.IndividualResponse;
import com.jtb.taxpayerws.service.IndividualService;
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
@RequestMapping("individual")
public class IndividualController {

    private final IndividualService individualService;
    private final ModelMapper modelMapper;

    public IndividualController(IndividualService individualService, ModelMapper modelMapper) {
        this.individualService = individualService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualResponse>> createIndividual(@RequestBody ApiRequest<IndividualRequest> individualRequest) {
        IndividualDto individualDto = modelMapper.map(individualRequest.getData(), IndividualDto.class);
        IndividualResponse response = modelMapper.map(individualService.createIndividual(individualDto), IndividualResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildCreatedResponse(response));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualResponse>> updateIndividual(@RequestBody @Valid ApiRequest<EditIndividualRequest> individualRequest, @PathVariable("id") Long id) {
        IndividualDto individualDto = modelMapper.map(individualRequest.getData(), IndividualDto.class);
        individualDto.setId(id);
        IndividualResponse response = modelMapper.map(individualService.updateIndividual(individualDto), IndividualResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualResponse>> getIndividual(@PathVariable("id") Long id) {
        IndividualResponse response = modelMapper.map(individualService.getIndividualDetails(id), IndividualResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PageDetail>> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(individualService.getAllIndividuals(page, limit)));
    }

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<IndividualResponse>> findIndividual(
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        IndividualResponse response = modelMapper.map(individualService.findIndividualByKeyword(keyword), IndividualResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }
}
