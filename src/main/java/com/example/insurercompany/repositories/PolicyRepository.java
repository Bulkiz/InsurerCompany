package com.example.insurercompany.repositories;

import com.example.insurercompany.entities.InsObjectType;
import com.example.insurercompany.entities.InsProduct;
import com.example.insurercompany.entities.Maturity;
import com.example.insurercompany.entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

    @Query(value = "SELECT p.* FROM policy p "
            + "LEFT JOIN ins_product ip ON p.ins_prod_code = ip.ins_prod_code "
            + "LEFT JOIN client c ON p.client_id = c.client_id WHERE "
            + "(:policyNo IS NULL OR p.policy_no LIKE CONCAT(:policyNo, '%')) AND "
            + "(:clientEgnBulstat IS NULL OR c.client_egn_bulstat LIKE CONCAT(:clientEgnBulstat, '%')) AND "
            + "(:clientFullname IS NULL OR c.client_fullname LIKE CONCAT(:clientFullname, '%')) AND "
            + "(:insProdCode IS NULL OR ip.ins_prod_code = :insProdCode) AND "
            + "(cast(:policyDate as timestamp) IS NULL OR p.policy_date <= :policyDate)",
            nativeQuery = true)
    List<Policy> findAllBySearchParams(@Param("policyNo") String policyNo, @Param("policyDate") LocalDateTime policyDate,
                                       @Param("clientEgnBulstat") String clientEgnBulstat, @Param("clientFullname") String clientFullname,
                                       @Param("insProdCode") Integer insProdCode);

    boolean existsByInsProduct(InsProduct insProduct);

    boolean existsByInsObjectType(InsObjectType insObjectType);

    Policy findByMaturities(Maturity maturity);

    List<Policy> findAllByInsProduct(InsProduct insProduct);
}
