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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;
    @Column(nullable = false, columnDefinition = "bpchar(1)")
    private String clientType;
    @Column(nullable = false)
    private String clientEgnBulstat;
    @Column(nullable = false)
    private String clientFullname;
    @Column
    private String email;
    @Column
    private String telephone;
    @Column(nullable = false)
    private String adressText;
    @Column
    private String clientNote;
    @Column
    private LocalDateTime modifDate;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Policy> policies;
}
