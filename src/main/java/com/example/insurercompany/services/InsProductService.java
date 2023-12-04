package com.example.insurercompany.services;

import com.example.insurercompany.entities.InsProduct;

import java.math.BigDecimal;
import java.util.List;

public interface InsProductService {
    List<InsProduct> findAllBySearchParams(Integer insProdCode, String insProdName, String insProdDeferred,
                                           Integer insTypeId, BigDecimal insProdPremPerc, BigDecimal insProdComissPerc, Integer insCompanyId);

    InsProduct saveInsProduct(InsProduct insProduct, String insCompanyName) throws Exception;

    void deleteInsProduct(Integer insProdCode);

    List<InsProduct> getAllByInsCompanyId(Integer insCompanyId);

    InsProduct updateInsProduct(InsProduct insProduct, String insCompanyName);
}
