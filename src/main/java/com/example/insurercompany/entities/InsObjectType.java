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
public class InsObjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insObjectTypeId;
    @Column(nullable = false)
    private String insObjectTypeName;
    @OneToMany(mappedBy = "insObjectType", fetch = FetchType.LAZY)
    private List<Policy> policies;
}
