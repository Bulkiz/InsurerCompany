package com.example.insurercompany.repositories;

import com.example.insurercompany.entities.InsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsTypeRepository extends JpaRepository<InsType, Integer> {
}
