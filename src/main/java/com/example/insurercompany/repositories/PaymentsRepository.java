package com.example.insurercompany.repositories;

import com.example.insurercompany.entities.Payments;
import com.example.insurercompany.entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
    List<Payments> findAllByPolicy(Policy policy);
}
