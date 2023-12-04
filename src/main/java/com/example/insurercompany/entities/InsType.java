package com.example.insurercompany.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class InsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insTypeId;
    @Column(nullable = false)
    private String insTypeName;
    @OneToMany(mappedBy = "insType", fetch = FetchType.LAZY)
    private List<InsProduct> insProducts;
}
