package com.example.insurercompany.mappers;

import com.example.insurercompany.dtos.InsTypeDto;
import com.example.insurercompany.entities.InsType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsTypeMapper implements ModelMapper<InsTypeDto, InsType> {
    @Override
    public InsTypeDto toDto(InsType insType) {
        return InsTypeDto.builder()
                .insTypeId(insType.getInsTypeId())
                .insTypeName(insType.getInsTypeName())
                .build();
    }

    @Override
    public InsType toEntity(InsTypeDto insTypeDto) {
        return InsType.builder()
                .insTypeId(insTypeDto.getInsTypeId())
                .insTypeName(insTypeDto.getInsTypeName())
                .build();
    }

    @Override
    public List<InsTypeDto> allToDtos(List<InsType> insTypes) {
        return insTypes.stream().map(this::toDto).toList();
    }

    @Override
    public List<InsType> allToEntities(List<InsTypeDto> insTypeDtos) {
        return insTypeDtos.stream().map(this::toEntity).toList();
    }
}
