package com.example.insurercompany.repositories;

import com.example.insurercompany.entities.Maturity;
import com.example.insurercompany.entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaturityRepository extends JpaRepository<Maturity, Integer> {

    boolean existsByPolicy(Policy policy);

    List<Maturity> findAllByPolicy(Policy policy);

    @Modifying
    @Query(value = "UPDATE maturity SET maturity_paid = 'Y' WHERE maturity_id = :maturityId", nativeQuery = true)
    void payMaturity(@Param("maturityId") Integer maturityId);
}
