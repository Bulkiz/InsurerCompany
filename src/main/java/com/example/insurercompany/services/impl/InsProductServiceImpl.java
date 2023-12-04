package com.example.insurercompany.services.impl;

import com.example.insurercompany.entities.InsCompany;
import com.example.insurercompany.entities.InsProduct;
import com.example.insurercompany.exceptions.ObjectHasPolicies;
import com.example.insurercompany.exceptions.ObjectNameAlreadyExistsException;
import com.example.insurercompany.repositories.InsCompanyRepository;
import com.example.insurercompany.repositories.InsProductRepository;
import com.example.insurercompany.repositories.PolicyRepository;
import com.example.insurercompany.services.InsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class InsProductServiceImpl implements InsProductService {
    @Autowired
    private InsProductRepository insProductRepository;
    @Autowired
    private InsCompanyRepository insCompanyRepository;
    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public List<InsProduct> findAllBySearchParams(Integer insProdCode, String insProdName, String insProdDeferred,
                                                  Integer insTypeId, BigDecimal insProdPremPerc, BigDecimal insProdComissPerc, Integer insCompanyId) {
        return insProductRepository.findAllBySearchParams(insProdCode, insProdName, insProdDeferred,
                insTypeId, insProdPremPerc, insProdComissPerc, insCompanyId);
    }

    @Override
    public InsProduct saveInsProduct(InsProduct insProduct, String insCompanyName) {
        if (insProductRepository.existsByInsProdName(insProduct.getInsProdName())) {
            throw new ObjectNameAlreadyExistsException("Object with that name exists!");
        }
        InsCompany insCompany = insCompanyRepository.findByInsCompanyName(insCompanyName);
        insProduct.setInsCompany(insCompany);
        insProduct.setModifDate(LocalDateTime.now());
        return insProductRepository.save(insProduct);
    }

    @Override
    public InsProduct updateInsProduct(InsProduct insProduct, String insCompanyName) {
        if (policyRepository.existsByInsProduct(insProduct)) {
            throw new ObjectHasPolicies("There are policies for this object");
        }
        if (insProductRepository.existsByInsProdName(insProduct.getInsProdName())) {
            throw new ObjectNameAlreadyExistsException("Object with that name exists!");
        }
        InsCompany insCompany = insCompanyRepository.findByInsCompanyName(insCompanyName);
        insProduct.setInsCompany(insCompany);
        insProduct.setModifDate(LocalDateTime.now());
        return insProductRepository.save(insProduct);
    }

    @Override
    public void deleteInsProduct(Integer insProdCode) {
        InsProduct insProduct = insProductRepository.findById(insProdCode).get();
        if (policyRepository.existsByInsProduct(insProduct)) {
            throw new ObjectHasPolicies("There are policies for this object");
        }
        insProductRepository.delete(insProduct);
    }

    @Override
    public List<InsProduct> getAllByInsCompanyId(Integer insCompanyId) {
        return insProductRepository.findAllByInsCompany(insCompanyRepository.findById(insCompanyId).get());
    }
}
