package com.example.insurercompany.services.impl;

import com.example.insurercompany.entities.InsObjectType;
import com.example.insurercompany.exceptions.ObjectHasPolicies;
import com.example.insurercompany.exceptions.ObjectNameAlreadyExistsException;
import com.example.insurercompany.repositories.InsObjectTypeRepository;
import com.example.insurercompany.repositories.PolicyRepository;
import com.example.insurercompany.services.InsObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InsObjectTypeServiceImpl implements InsObjectTypeService {
    @Autowired
    private InsObjectTypeRepository insObjectTypeRepository;
    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public List<InsObjectType> findAllBySearchParams(Integer insObjectTypeId, String insObjectTypeName) {
        return insObjectTypeRepository.findAllBySearchParams(insObjectTypeId, insObjectTypeName);
    }

    @Override
    public InsObjectType saveInsObjectType(InsObjectType insObjectType) {
        if (insObjectTypeRepository.existsByInsObjectTypeName(insObjectType.getInsObjectTypeName())) {
            throw new ObjectNameAlreadyExistsException("Object with that name exists!");
        }
        return insObjectTypeRepository.save(insObjectType);
    }

    @Override
    public InsObjectType updateInsObjectType(InsObjectType insObjectType) {
        if (policyRepository.existsByInsObjectType(insObjectType)) {
            throw new ObjectHasPolicies("There are policies for this object");
        }
        if (insObjectTypeRepository.existsByInsObjectTypeName(insObjectType.getInsObjectTypeName())) {
            throw new ObjectNameAlreadyExistsException("Object with that name exists!");
        }
        return insObjectTypeRepository.save(insObjectType);
    }

    @Override
    public void deleteInsObjectType(Integer insObjectTypeId) {
        InsObjectType insObjectType = insObjectTypeRepository.findById(insObjectTypeId).get();
        if (policyRepository.existsByInsObjectType(insObjectType)) {
            throw new ObjectHasPolicies("There are policies for this object");
        }
        insObjectTypeRepository.delete(insObjectType);
    }
}
