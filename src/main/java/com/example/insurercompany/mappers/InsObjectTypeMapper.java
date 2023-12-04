package com.example.insurercompany.mappers;

import com.example.insurercompany.dtos.InsObjectTypeDto;
import com.example.insurercompany.entities.InsObjectType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsObjectTypeMapper implements ModelMapper<InsObjectTypeDto, InsObjectType> {
    @Override
    public InsObjectTypeDto toDto(InsObjectType insObjectType) {
        return InsObjectTypeDto.builder()
                .insObjectTypeId(insObjectType.getInsObjectTypeId())
                .insObjectTypeName(insObjectType.getInsObjectTypeName())
                .build();
    }

    @Override
    public InsObjectType toEntity(InsObjectTypeDto insObjectTypeDto) {
        return InsObjectType.builder()
                .insObjectTypeId(insObjectTypeDto.getInsObjectTypeId())
                .insObjectTypeName(insObjectTypeDto.getInsObjectTypeName())
                .build();
    }

    @Override
    public List<InsObjectTypeDto> allToDtos(List<InsObjectType> insObjectTypes) {
        return insObjectTypes.stream().map(this::toDto).toList();
    }

    @Override
    public List<InsObjectType> allToEntities(List<InsObjectTypeDto> insObjectTypeDtos) {
        return insObjectTypeDtos.stream().map(this::toEntity).toList();
    }
}
