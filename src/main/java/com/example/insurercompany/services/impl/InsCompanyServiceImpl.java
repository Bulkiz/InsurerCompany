package com.example.insurercompany.services.impl;

import com.example.insurercompany.entities.InsCompany;
import com.example.insurercompany.entities.InsProduct;
import com.example.insurercompany.exceptions.ObjectHasPolicies;
import com.example.insurercompany.exceptions.ObjectNameAlreadyExistsException;
import com.example.insurercompany.repositories.InsCompanyRepository;
import com.example.insurercompany.repositories.InsProductRepository;
import com.example.insurercompany.repositories.PolicyRepository;
import com.example.insurercompany.services.InsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class InsCompanyServiceImpl implements InsCompanyService {
    @Autowired
    private InsCompanyRepository insCompanyRepository;
    @Autowired
    private InsProductRepository insProductRepository;
    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public List<InsCompany> findAllBySearchParams(Integer insCompanyId, String insCompanyName, String insCompanyBulstat, Integer insTypeId) {
        return insCompanyRepository.findAllBySearchParams(insCompanyId, insCompanyName, insCompanyBulstat, insTypeId);
    }

    @Override
    public InsCompany saveInsCompany(InsCompany insCompany) {
        if (insCompanyRepository.existsByInsCompanyName(insCompany.getInsCompanyName())) {
            throw new ObjectNameAlreadyExistsException("Object with that name exists!");
        }
        insCompany.setModifDate(LocalDateTime.now());
        return insCompanyRepository.save(insCompany);
    }

    @Override
    public List<InsCompany> getAll() {
        return insCompanyRepository.findAll();
    }

    @Override
    public void deleteInsCompany(Integer insCompanyId) {
        InsCompany insCompany = insCompanyRepository.findById(insCompanyId).get();
        List<InsProduct> insProducts = insProductRepository.findAllByInsCompany(insCompany);
        insProducts.forEach(insProduct -> {
            if (policyRepository.existsByInsProduct(insProduct)) {
                throw new ObjectHasPolicies("There are policies for this object");
            }
        });
        InsProduct insProduct = insProductRepository.findByInsCompany(insCompany);
        insProductRepository.delete(insProduct);
        insCompanyRepository.delete(insCompany);
    }

    @Override
    public InsCompany updateInsCompany(InsCompany insCompany) {
        List<InsProduct> insProducts = insProductRepository.findAllByInsCompany(insCompany);
        insProducts.forEach(insProduct -> {
            if (policyRepository.existsByInsProduct(insProduct)) {
                throw new ObjectHasPolicies("There are policies for this object");
            }
        });
        if (insCompanyRepository.existsByInsCompanyName(insCompany.getInsCompanyName())) {
            throw new ObjectNameAlreadyExistsException("Object with that name exists!");
        }
        insCompany.setModifDate(LocalDateTime.now());
        return insCompanyRepository.save(insCompany);
    }
}
