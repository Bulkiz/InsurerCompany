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
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer policyId;
    @Column(nullable = false)
    private String policyNo;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime policyDate;
    @Column(nullable = false)
    private LocalDateTime policyBeginDate;
    @Column(nullable = false)
    private LocalDateTime policyEndDate;
    @ManyToOne
    @JoinColumn(name = "ins_prod_code")
    private InsProduct insProduct;
    @ManyToOne
    @JoinColumn(name = "ins_object_type_id")
    private InsObjectType insObjectType;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(nullable = false)
    private String objectDescription;
    @Column(nullable = false, columnDefinition = "bpchar(1)")
    private String policyActive;
    @Column(nullable = false, columnDefinition = "numeric(15, 2")
    private BigDecimal policySum;
    @Column(nullable = false, columnDefinition = "numeric(15, 2")
    private BigDecimal policyPremia;
    @Column(nullable = false, columnDefinition = "numeric(15, 2")
    private BigDecimal policyTax;
    @Column(columnDefinition = "numeric(15, 2")
    private BigDecimal policyInsComiss;
    @Column
    private String policyNote;
    @Column
    private LocalDateTime modifDate;
    @OneToMany(mappedBy = "policy", fetch = FetchType.LAZY)
    private List<Maturity> maturities;
    @OneToMany(mappedBy = "policy", fetch = FetchType.LAZY)
    private List<Payments> payments;


}
