package com.example.insurercompany.mappers;

import com.example.insurercompany.dtos.InsProductDto;
import com.example.insurercompany.entities.InsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsProductMapper implements ModelMapper<InsProductDto, InsProduct> {
    @Autowired
    private InsTypeMapper insTypeMapper;

    public InsProductDto toBasicInfo(InsProduct insProduct) {
        return InsProductDto.builder()
                .insProdCode(insProduct.getInsProdCode())
                .insProdName(insProduct.getInsProdName()).build();

    }

    @Override
    public InsProductDto toDto(InsProduct insProduct) {
        return InsProductDto.builder()
                .insProdCode(insProduct.getInsProdCode())
                .insProdName(insProduct.getInsProdName())
                .insTypeDto(insTypeMapper.toDto(insProduct.getInsType()))
                .insProdDeferred(insProduct.getInsProdDeferred())
                .insProdPremPerc(insProduct.getInsProdPremPerc())
                .insProdComissPerc(insProduct.getInsProdComissPerc())
                .insCompanyName(insProduct.getInsCompany().getInsCompanyName())
                .build();
    }

    @Override
    public InsProduct toEntity(InsProductDto insProductDto) {
        return InsProduct.builder()
                .insProdCode(insProductDto.getInsProdCode())
                .insProdName(insProductDto.getInsProdName())
                .insType(insTypeMapper.toEntity(insProductDto.getInsTypeDto()))
                .insProdDeferred(insProductDto.getInsProdDeferred())
                .insProdPremPerc(insProductDto.getInsProdPremPerc())
                .insProdComissPerc(insProductDto.getInsProdComissPerc())
                .build();
    }

    @Override
    public List<InsProductDto> allToDtos(List<InsProduct> insProducts) {
        return insProducts.stream().map(this::toDto).toList();
    }

    @Override
    public List<InsProduct> allToEntities(List<InsProductDto> insProductDtos) {
        return insProductDtos.stream().map(this::toEntity).toList();
    }
}
