package com.example.insurercompany.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PaymentsDto {
    private Integer paymentId;
    private LocalDateTime paymentDate;
    private BigDecimal paymentSum;
}
