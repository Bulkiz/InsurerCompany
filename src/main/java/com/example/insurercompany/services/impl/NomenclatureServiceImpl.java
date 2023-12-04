package com.example.insurercompany.services.impl;

import com.example.insurercompany.entities.InsType;
import com.example.insurercompany.repositories.InsTypeRepository;
import com.example.insurercompany.services.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NomenclatureServiceImpl implements NomenclatureService {
    @Autowired
    private InsTypeRepository insTypeRepository;

    @Override
    public List<InsType> getAllInsTypes() {
        return insTypeRepository.findAll();
    }
}
