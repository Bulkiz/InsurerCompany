package com.example.insurercompany.mappers;

import com.example.insurercompany.dtos.PolicyDto;
import com.example.insurercompany.entities.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolicyMapper implements ModelMapper<PolicyDto, Policy> {
    @Autowired
    private InsProductMapper insProductMapper;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private InsObjectTypeMapper insObjectTypeMapper;

    @Override
    public PolicyDto toDto(Policy policy) {
        return PolicyDto.builder()
                .policyId(policy.getPolicyId())
                .policyNo(policy.getPolicyNo())
                .policyDate(policy.getPolicyDate())
                .policyBeginDate(policy.getPolicyBeginDate())
                .policyEndDate(policy.getPolicyEndDate())
                .insProductDto(insProductMapper.toDto(policy.getInsProduct()))
                .insObjectTypeDto(insObjectTypeMapper.toDto(policy.getInsObjectType()))
                .clientDto(clientMapper.toDto(policy.getClient()))
                .objectDescription(policy.getObjectDescription())
                .policyActive(policy.getPolicyActive())
                .policySum(policy.getPolicySum())
                .policyPremia(policy.getPolicyPremia())
                .policyTax(policy.getPolicyTax())
                .policyInsComiss(policy.getPolicyInsComiss())
                .policyNote(policy.getPolicyNote())
                .build();

    }

    @Override
    public Policy toEntity(PolicyDto policyDto) {
        return Policy.builder()
                .policyId(policyDto.getPolicyId())
                .policyNo(policyDto.getPolicyNo())
                .policyDate(policyDto.getPolicyDate())
                .policyBeginDate(policyDto.getPolicyBeginDate())
                .policyEndDate(policyDto.getPolicyEndDate())
                .insProduct(insProductMapper.toEntity(policyDto.getInsProductDto()))
                .insObjectType(insObjectTypeMapper.toEntity(policyDto.getInsObjectTypeDto()))
                .client(clientMapper.toEntity(policyDto.getClientDto()))
                .objectDescription(policyDto.getObjectDescription())
                .policyActive(policyDto.getPolicyActive())
                .policySum(policyDto.getPolicySum())
                .policyPremia(policyDto.getPolicyPremia())
                .policyTax(policyDto.getPolicyTax())
                .policyInsComiss(policyDto.getPolicyInsComiss())
                .policyNote(policyDto.getPolicyNote())
                .build();
    }

    @Override
    public List<PolicyDto> allToDtos(List<Policy> policies) {
        return policies.stream().map(this::toDto).toList();
    }

    @Override
    public List<Policy> allToEntities(List<PolicyDto> policyDtos) {
        return policyDtos.stream().map(this::toEntity).toList();
    }
}
