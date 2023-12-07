package com.example.insurercompany.controllers;

import com.example.insurercompany.dtos.InsProductDto;
import com.example.insurercompany.mappers.InsProductMapper;
import com.example.insurercompany.services.InsProductService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/ins-products")
public class InsProductController {
    @Autowired
    private InsProductService insProductService;
    @Autowired
    private InsProductMapper insProductMapper;

    @GetMapping
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<List<InsProductDto>> findAll(
            @RequestParam(required = false) Integer insProdCode,
            @RequestParam(required = false) String insProdName,
            @RequestParam(required = false) String insProdDeferred,
            @RequestParam(required = false) Integer insTypeId,
            @RequestParam(required = false) BigDecimal insProdPremPerc,
            @RequestParam(required = false) BigDecimal insProdComissPerc,
            @RequestParam(required = false) Integer insCompanyId
    ) {
        List<InsProductDto> insProductDtos = insProductService.findAllBySearchParams(insProdCode, insProdName, insProdDeferred,
                        insTypeId, insProdPremPerc, insProdComissPerc, insCompanyId)
                .stream().map(insProduct -> insProductMapper.toDto(insProduct)).toList();
        return new ResponseEntity<>(insProductDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InsProductDto> saveInsProduct(@RequestBody InsProductDto insProductDto) throws Exception {
        InsProductDto insProduct = insProductMapper.toDto(insProductService.saveInsProduct(insProductMapper.toEntity(insProductDto),
                insProductDto.getInsCompanyName()));
        return new ResponseEntity<>(insProduct, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<InsProductDto> updateInsProduct(@RequestBody InsProductDto insProductDto) {
        InsProductDto insProduct = insProductMapper.toDto(insProductService.updateInsProduct(insProductMapper.toEntity(insProductDto),
                insProductDto.getInsCompanyName()));
        return new ResponseEntity<>(insProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{insProdCode}")
    public ResponseEntity<String> deleteInsProd(@PathVariable Integer insProdCode) {
        insProductService.deleteInsProduct(insProdCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/ins-company")
    public ResponseEntity<List<InsProductDto>> getAllByInsCompanyId(@RequestParam Integer insCompanyId) {
        return new ResponseEntity<>(insProductMapper.allToDtos(insProductService.getAllByInsCompanyId(insCompanyId)), HttpStatus.OK);
    }

    @GetMapping("/{insProductId}")
    public ResponseEntity<InsProductDto> getByInsProductId(@PathVariable Integer insProductId) {
        return new ResponseEntity<>(insProductMapper.toDto(insProductService.getByInsProductId(insProductId)), HttpStatus.OK);
    }
}
