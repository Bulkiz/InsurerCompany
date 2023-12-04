package com.example.insurercompany.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class InsProductDto {
    private Integer insProdCode;
    private String insProdName;
    private InsTypeDto insTypeDto;
    private String insProdDeferred;
    private BigDecimal insProdPremPerc;
    private BigDecimal insProdComissPerc;
    private String insCompanyName;
}
