package com.example.insurercompany.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class InsProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insProdCode;
    @ManyToOne
    @JoinColumn(name = "ins_type_id")
    private InsType insType;
    @ManyToOne
    @JoinColumn(name = "ins_company_id")
    private InsCompany insCompany;
    @Column(nullable = false)
    private String insProdName;
    @Column(nullable = false, columnDefinition = "bpchar(1)")
    private String insProdDeferred;
    @Column(columnDefinition = "numeric(3, 2)")
    private BigDecimal insProdPremPerc;
    @Column(columnDefinition = "numeric(3, 2)")
    private BigDecimal insProdComissPerc;
    @Column
    private LocalDateTime modifDate;
    @OneToMany(mappedBy = "insProduct")
    private List<Policy> policies;

}
