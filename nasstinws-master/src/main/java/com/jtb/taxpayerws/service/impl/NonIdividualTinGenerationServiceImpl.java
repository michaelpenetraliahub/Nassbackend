package com.jtb.taxpayerws.service.impl;

import com.jtb.taxpayerws.client.payload.request.JTBTaxpayerRequest;
import com.jtb.taxpayerws.client.payload.response.IndividualTaxpayer;
import com.jtb.taxpayerws.client.payload.response.JTBIndividualResponse;
import com.jtb.taxpayerws.client.proxy.JTBClientProxy;
import com.jtb.taxpayerws.dto.NonIndividualTinGenerationDto;
import com.jtb.taxpayerws.entity.NonIndividualTinGenerationEntity;
import com.jtb.taxpayerws.enums.ErrorInfo;
import com.jtb.taxpayerws.exception.ApiException;
import com.jtb.taxpayerws.payload.PageDetail;
import com.jtb.taxpayerws.payload.Paging;
import com.jtb.taxpayerws.payload.response.NonIndividualTinGenerationResponse;
import com.jtb.taxpayerws.repository.NonIndividualTinGenerationRepository;
import com.jtb.taxpayerws.service.AbstractJTBService;
import com.jtb.taxpayerws.service.NonIndividualTinGenerationService;
import com.jtb.taxpayerws.utils.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NonIndividualTinGenerationServiceImpl extends AbstractJTBService implements NonIndividualTinGenerationService {
    private final NonIndividualTinGenerationRepository nonIndividualTinGenerationRepository;
    private final ModelMapper modelMapper;

    public NonIndividualTinGenerationServiceImpl(JTBClientProxy jtbClientProxy, NonIndividualTinGenerationServiceImpl nonIndividualTinGenerationRepository, ModelMapper modelMapper) {
        super(jtbClientProxy);
        this.nonIndividualTinGenerationRepository = nonIndividualTinGenerationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NonIndividualTinGenerationDto createNonIndividualTinGeneration(NonIndividualTinGenerationDto nonIndividualTinGenerationDto) {
        NonIndividualTinGenerationEntity nonIndividualTinGenerationEntity = modelMapper.map(nonIndividualTinGenerationDto, NonIndividualTinGenerationEntity.class);

        return modelMapper.map(nonIndividualTinGenerationRepository.save(nonIndividualTinGenerationEntity), NonIndividualTinGemerationDto.class);
    }


}
