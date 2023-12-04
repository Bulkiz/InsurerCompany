package com.example.insurercompany.mappers;

import com.example.insurercompany.dtos.PaymentsDto;
import com.example.insurercompany.entities.Payments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentsMapper implements ModelMapper<PaymentsDto, Payments> {
    @Override
    public PaymentsDto toDto(Payments payments) {
        return PaymentsDto.builder()
                .paymentId(payments.getPaymentId())
                .paymentDate(payments.getPaymentDate())
                .paymentSum(payments.getPaymentSum())
                .build();
    }

    @Override
    public Payments toEntity(PaymentsDto paymentsDto) {
        return Payments.builder()
                .paymentId(paymentsDto.getPaymentId())
                .paymentDate(paymentsDto.getPaymentDate())
                .paymentSum(paymentsDto.getPaymentSum())
                .build();
    }

    @Override
    public List<PaymentsDto> allToDtos(List<Payments> payments) {
        return payments.stream().map(this::toDto).toList();
    }

    @Override
    public List<Payments> allToEntities(List<PaymentsDto> paymentsDtos) {
        return paymentsDtos.stream().map(this::toEntity).toList();
    }
}
