package com.jtb.taxpayerws.service;

import com.jtb.taxpayerws.dto.TaxDetailDto;

public interface IndividualTaxDetailService {
    TaxDetailDto addTaxDetail(TaxDetailDto taxDetailDto, String token);

    TaxDetailDto getTaxDetail(String tin);
}
