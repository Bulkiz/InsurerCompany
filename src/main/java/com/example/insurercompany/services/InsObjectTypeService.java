package com.example.insurercompany.services;

import com.example.insurercompany.entities.InsObjectType;

import java.util.List;

public interface InsObjectTypeService {
    List<InsObjectType> findAllBySearchParams(Integer insObjectTypeId, String insObjectTypeName);

    InsObjectType saveInsObjectType(InsObjectType insObjectType);

    void deleteInsObjectType(Integer insObjectTypeId);

    InsObjectType updateInsObjectType(InsObjectType insObjectType);
}
