package com.example.insurercompany.controllers;

import com.example.insurercompany.dtos.InsTypeDto;
import com.example.insurercompany.mappers.InsTypeMapper;
import com.example.insurercompany.services.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nomenclature")
public class NomenclatureController {
    @Autowired
    private NomenclatureService nomenclatureService;
    @Autowired
    private InsTypeMapper insTypeMapper;

    @GetMapping("/ins-types")
    public ResponseEntity<List<InsTypeDto>> getAllInsTypes() {
        return new ResponseEntity<>(insTypeMapper.allToDtos(nomenclatureService.getAllInsTypes()), HttpStatus.OK);
    }
}
