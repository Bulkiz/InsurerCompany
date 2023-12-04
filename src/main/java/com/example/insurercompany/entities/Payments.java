package com.example.insurercompany.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;
    @Column(nullable = false)
    private LocalDateTime paymentDate;
    @Column(nullable = false, columnDefinition = "numeric(15, 2)")
    private BigDecimal paymentSum;
    @Column
    private LocalDateTime modifDate;
}
