package com.example.insurercompany.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PolicyDto {
    private Integer policyId;
    private String policyNo;
    private LocalDateTime policyDate;
    private LocalDateTime policyBeginDate;
    private LocalDateTime policyEndDate;
    private InsObjectTypeDto insObjectTypeDto;
    private InsProductDto insProductDto;
    private ClientDto clientDto;
    private String objectDescription;
    private String policyActive;
    private BigDecimal policySum;
    private BigDecimal policyPremia;
    private BigDecimal policyTax;
    private BigDecimal policyInsComiss;
    private String policyNote;
    private Integer maturityCount;
}
