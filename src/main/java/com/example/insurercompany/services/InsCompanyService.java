package com.example.insurercompany.services;

import com.example.insurercompany.entities.InsCompany;

import java.util.List;

public interface InsCompanyService {
    List<InsCompany> findAllBySearchParams(Integer insCompanyId, String insCompanyName, String insCompanyBulstat, Integer insTypeId);

    InsCompany saveInsCompany(InsCompany insCompany);

    List<InsCompany> getAll();

    void deleteInsCompany(Integer insCompanyId);

    InsCompany updateInsCompany(InsCompany insCompany);
}
