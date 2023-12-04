package com.example.insurercompany.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class InsCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insCompanyId;
    @Column(nullable = false)
    private String insCompanyName;
    @Column(nullable = false)
    private String insCompanyBulstat;
    @Column
    private String insCompanyAddr;
    @Column
    private String insCompanyContact;
    @Column
    private String insCompanyTel;
    @Column
    private LocalDateTime modifDate;
    @OneToMany(mappedBy = "insCompany", fetch = FetchType.LAZY)
    private List<InsProduct> insProducts;
}
