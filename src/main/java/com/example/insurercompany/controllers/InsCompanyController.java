package com.example.insurercompany.controllers;

import com.example.insurercompany.dtos.InsCompanyDto;
import com.example.insurercompany.mappers.InsCompanyMapper;
import com.example.insurercompany.services.InsCompanyService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ins-companies")
public class InsCompanyController {
    @Autowired
    private InsCompanyService insCompanyService;

    @Autowired
    private InsCompanyMapper insCompanyMapper;

    @GetMapping
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<List<InsCompanyDto>> findAll(
            @RequestParam(required = false) Integer insCompanyId,
            @RequestParam(required = false) String insCompanyName,
            @RequestParam(required = false) String insCompanyBulstat,
            @RequestParam(required = false) Integer insTypeId
    ) {
        List<InsCompanyDto> insCompanies = insCompanyService.findAllBySearchParams(insCompanyId, insCompanyName, insCompanyBulstat, insTypeId)
                .stream().map(insCompany -> insCompanyMapper.toDto(insCompany)).toList();
        return new ResponseEntity<>(insCompanies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InsCompanyDto> saveInsCompany(@RequestBody InsCompanyDto insCompanyDto) {
        return new ResponseEntity<>(insCompanyMapper.toDto(insCompanyService.saveInsCompany(insCompanyMapper.toEntity(insCompanyDto))), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<InsCompanyDto> updateInsCompany(@RequestBody InsCompanyDto insCompanyDto) {
        return new ResponseEntity<>(insCompanyMapper.toDto(insCompanyService.updateInsCompany(insCompanyMapper.toEntity(insCompanyDto))), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<InsCompanyDto>> getAll() {
        return new ResponseEntity<>(insCompanyMapper.allToDtos(insCompanyService.getAll()), HttpStatus.OK);
    }

    @DeleteMapping("/{insCompanyId}")
    public ResponseEntity<Integer> deleteInsCompany(@PathVariable Integer insCompanyId) {
        insCompanyService.deleteInsCompany(insCompanyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
