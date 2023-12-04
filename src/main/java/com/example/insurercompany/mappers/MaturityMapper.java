package com.example.insurercompany.mappers;

import com.example.insurercompany.dtos.MaturityDto;
import com.example.insurercompany.entities.Maturity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MaturityMapper implements ModelMapper<MaturityDto, Maturity> {

    @Override
    public MaturityDto toDto(Maturity maturity) {
        return MaturityDto.builder()
                .maturityId(maturity.getMaturityId())
                .maturityDate(maturity.getMaturityDate())
                .maturitySum(maturity.getMaturitySum())
                .maturityPaid(maturity.getMaturityPaid())
                .maturityNote(maturity.getMaturityNote())
                .build();
    }

    @Override
    public Maturity toEntity(MaturityDto maturityDto) {
        return Maturity.builder()
                .maturityId(maturityDto.getMaturityId())
                .maturityDate(maturityDto.getMaturityDate())
                .maturitySum(maturityDto.getMaturitySum())
                .maturityPaid(maturityDto.getMaturityPaid())
                .maturityNote(maturityDto.getMaturityNote())
                .build();
    }

    @Override
    public List<MaturityDto> allToDtos(List<Maturity> maturities) {
        return maturities.stream().map(this::toDto).toList();
    }

    @Override
    public List<Maturity> allToEntities(List<MaturityDto> maturityDtos) {
        return maturityDtos.stream().map(this::toEntity).toList();
    }
}
