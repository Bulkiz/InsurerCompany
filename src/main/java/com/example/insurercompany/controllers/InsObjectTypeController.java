package com.example.insurercompany.controllers;

import com.example.insurercompany.dtos.InsObjectTypeDto;
import com.example.insurercompany.mappers.InsObjectTypeMapper;
import com.example.insurercompany.services.InsObjectTypeService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ins-object-types")
public class InsObjectTypeController {
    @Autowired
    private InsObjectTypeService insObjectTypeService;
    @Autowired
    private InsObjectTypeMapper insObjectTypeMapper;

    @GetMapping
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<List<InsObjectTypeDto>> findAll(
            @RequestParam(required = false) Integer insObjectTypeId,
            @RequestParam(required = false) String insObjectTypeName
    ) {
        List<InsObjectTypeDto> insObjectTypeDtos = insObjectTypeService.findAllBySearchParams(insObjectTypeId, insObjectTypeName)
                .stream().map(insObjectType -> insObjectTypeMapper.toDto(insObjectType)).toList();
        return new ResponseEntity<>(insObjectTypeDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InsObjectTypeDto> saveInsObjectType(@RequestBody InsObjectTypeDto insObjectTypeDto) {
        return new ResponseEntity<>(insObjectTypeMapper.toDto(insObjectTypeService.
                saveInsObjectType(insObjectTypeMapper.toEntity(insObjectTypeDto))), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<InsObjectTypeDto> updateInsObjectType(@RequestBody InsObjectTypeDto insObjectTypeDto) {
        return new ResponseEntity<>(insObjectTypeMapper.toDto(insObjectTypeService.
                updateInsObjectType(insObjectTypeMapper.toEntity(insObjectTypeDto))), HttpStatus.OK);
    }

    @DeleteMapping("/{insObjectTypeId}")
    public ResponseEntity<String> deleteInsObjectType(@PathVariable Integer insObjectTypeId) {
        insObjectTypeService.deleteInsObjectType(insObjectTypeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{insObjectTypeId}")
    public ResponseEntity<InsObjectTypeDto> getByInsObjectTypeId(@PathVariable Integer insObjectTypeId) {
        return new ResponseEntity<>(insObjectTypeMapper.toDto(insObjectTypeService.getByInsObjectTypeId(insObjectTypeId)), HttpStatus.OK);
    }
}
