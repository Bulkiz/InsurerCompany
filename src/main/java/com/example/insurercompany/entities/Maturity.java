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
public class Maturity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maturityId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;
    @Column(nullable = false)
    private LocalDateTime maturityDate;
    @Column(nullable = false, columnDefinition = "numeric(15, 2)")
    private BigDecimal maturitySum;
    @Column(nullable = false, columnDefinition = "bpchar(1)")
    private String maturityPaid;
    @Column(nullable = false)
    private String maturityNote;
    @Column
    private LocalDateTime modifDate;
}
