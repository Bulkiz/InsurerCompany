package com.example.insurercompany.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MaturityDto {
    private Integer maturityId;
    private LocalDateTime maturityDate;
    private BigDecimal maturitySum;
    private String maturityPaid;
    private String maturityNote;
}
