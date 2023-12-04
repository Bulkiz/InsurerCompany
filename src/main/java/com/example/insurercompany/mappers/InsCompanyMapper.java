package com.example.insurercompany.mappers;

import com.example.insurercompany.dtos.InsCompanyDto;
import com.example.insurercompany.entities.InsCompany;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsCompanyMapper implements ModelMapper<InsCompanyDto, InsCompany> {

    public InsCompanyDto toBasicInfo(InsCompany insCompany) {
        return InsCompanyDto.builder()
                .insCompanyId(insCompany.getInsCompanyId())
                .insCompanyName(insCompany.getInsCompanyName())
                .insCompanyBulstat(insCompany.getInsCompanyBulstat())
                .insCompanyAddr(insCompany.getInsCompanyAddr())
                .build();
    }

    @Override
    public InsCompanyDto toDto(InsCompany insCompany) {
        return InsCompanyDto.builder()
                .insCompanyId(insCompany.getInsCompanyId())
                .insCompanyName(insCompany.getInsCompanyName())
                .insCompanyBulstat(insCompany.getInsCompanyBulstat())
                .insCompanyAddr(insCompany.getInsCompanyAddr())
                .insCompanyContact(insCompany.getInsCompanyContact())
                .insCompanyTel(insCompany.getInsCompanyTel())
                .build();
    }

    @Override
    public InsCompany toEntity(InsCompanyDto insCompanyDto) {
        return InsCompany.builder()
                .insCompanyId(insCompanyDto.getInsCompanyId())
                .insCompanyName(insCompanyDto.getInsCompanyName())
                .insCompanyBulstat(insCompanyDto.getInsCompanyBulstat())
                .insCompanyAddr(insCompanyDto.getInsCompanyAddr())
                .insCompanyContact(insCompanyDto.getInsCompanyContact())
                .insCompanyTel(insCompanyDto.getInsCompanyTel())
                .build();
    }

    @Override
    public List<InsCompanyDto> allToDtos(List<InsCompany> insCompanies) {
        return insCompanies.stream().map(this::toDto).toList();
    }

    @Override
    public List<InsCompany> allToEntities(List<InsCompanyDto> insCompanyDtos) {
        return insCompanyDtos.stream().map(this::toEntity).toList();
    }
}
