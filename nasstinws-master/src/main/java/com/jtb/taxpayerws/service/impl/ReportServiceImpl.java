package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.dto.IndividualDto;
import com.jtb.taxpayerws.dto.NonIndividualDto;
import com.jtb.taxpayerws.entity.IndividualEntity;
import com.jtb.taxpayerws.entity.NonIndividualEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.enums.SearchOperation;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.repository.IndividualRepository;
import com.jtb.taxpayerws.repository.NonIndividualRepository;
import com.jtb.taxpayerws.repository.specs.CustomSpecification;
import com.jtb.taxpayerws.repository.specs.SearchCriteria;
import com.jtb.taxpayerws.service.ReportService;
import com.jtb.taxpayerws.utils.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private final IndividualRepository individualRepository;
    private final NonIndividualRepository nonIndividualRepository;
    private final ModelMapper modelMapper;

    public ReportServiceImpl(IndividualRepository individualRepository, NonIndividualRepository nonIndividualRepository, ModelMapper modelMapper) {
        this.individualRepository = individualRepository;
        this.nonIndividualRepository = nonIndividualRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<IndividualDto> getIndividualReports(String keyword, String from, String to) {
        CustomSpecification<IndividualEntity> individualSpec = new CustomSpecification<>();
        LocalDate fromDate = DateUtil.parseStringToDate(from);
        LocalDate toDate = DateUtil.parseStringToDate(to);

        if (!(Objects.isNull(keyword) || keyword.trim().isEmpty())) {
            individualSpec.add(new SearchCriteria("lga", keyword.trim(), SearchOperation.EQUALS));
        }

        if (!DateUtil.isValidDateInterval(fromDate, toDate)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorInfo.INVALID_DATE_INTERVAL.getErrorMessage());
        }

        individualSpec.add(new SearchCriteria("dateOfRegistration", fromDate, SearchOperation.GREATER_THAN_EQUAL));
        individualSpec.add(new SearchCriteria("dateOfRegistration", toDate, SearchOperation.LESS_THAN_EQUAL));

        return individualRepository.findAll(individualSpec).stream().map(x -> modelMapper.map(x, IndividualDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<NonIndividualDto> getNonIndividualReports(String keyword, String from, String to) {
        CustomSpecification<NonIndividualEntity> nonIndividualSpec = new CustomSpecification<>();
        LocalDate fromDate = DateUtil.parseStringToDate(from);
        LocalDate toDate = DateUtil.parseStringToDate(to);

        if (!(Objects.isNull(keyword) || keyword.trim().isEmpty())) {
            nonIndividualSpec.add(new SearchCriteria("lga", keyword.trim(), SearchOperation.EQUALS));
        }

        if (!DateUtil.isValidDateInterval(fromDate, toDate)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorInfo.INVALID_DATE_INTERVAL.getErrorMessage());
        }

        nonIndividualSpec.add(new SearchCriteria("dateOfRegistration", fromDate, SearchOperation.GREATER_THAN_EQUAL));
        nonIndividualSpec.add(new SearchCriteria("dateOfRegistration", toDate, SearchOperation.LESS_THAN_EQUAL));

        return nonIndividualRepository.findAll(nonIndividualSpec).stream().map(x -> modelMapper.map(x, NonIndividualDto.class)).collect(Collectors.toList());
    }
}
