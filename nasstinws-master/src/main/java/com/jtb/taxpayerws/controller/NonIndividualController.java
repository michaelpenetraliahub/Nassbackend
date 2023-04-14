package com.jtb.taxpayerws.controller;

import com.jtb.taxpayerws.dto.NonIndividualDto;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.request.ApiRequest;
import com.jtb.taxpayerws.payload.request.EditNonIndividualRequest;
import com.jtb.taxpayerws.payload.request.NonIndividualRequest;
import com.jtb.taxpayerws.payload.response.ApiResponse;
import com.jtb.taxpayerws.payload.response.NonIndividualResponse;
import com.jtb.taxpayerws.service.NonIndividualService;
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
@RequestMapping("nonindividual")
public class NonIndividualController {
    private final NonIndividualService nonIndividualService;
    private final ModelMapper modelMapper;

    public NonIndividualController(NonIndividualService nonIndividualService, ModelMapper modelMapper) {
        this.nonIndividualService = nonIndividualService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualResponse>> createNonIndividual(@RequestBody ApiRequest<NonIndividualRequest> nonIndividualRequest) {
        NonIndividualDto nonIndividualDto = modelMapper.map(nonIndividualRequest.getData(), NonIndividualDto.class);
        NonIndividualResponse response = modelMapper.map(nonIndividualService.createNonIndividual(nonIndividualDto), NonIndividualResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildCreatedResponse(response));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualResponse>> updateNonIndividual(@RequestBody @Valid ApiRequest<EditNonIndividualRequest> nonIndividualRequest, @PathVariable("id") Long id) {
        NonIndividualDto nonIndividualDto = modelMapper.map(nonIndividualRequest.getData(), NonIndividualDto.class);
        nonIndividualDto.setId(id);
        NonIndividualResponse response = modelMapper.map(nonIndividualService.updateNonIndividual(nonIndividualDto), NonIndividualResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualResponse>> getNonIndividual(@PathVariable("id") Long id) {
        NonIndividualResponse response = modelMapper.map(nonIndividualService.getNonIndividualDetails(id), NonIndividualResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PageDetail>> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(nonIndividualService.getAllNonIndividuals(page, limit)));
    }

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NonIndividualResponse>> findNonIndividual(
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        NonIndividualResponse response = modelMapper.map(nonIndividualService.findNonIndividualByKeyword(keyword), NonIndividualResponse.class);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ResponseUtil.buildOkResponse(response));
    }
}
