package com.example.insurercompany.repositories;

import com.example.insurercompany.entities.InsCompany;
import com.example.insurercompany.entities.InsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsCompanyRepository extends JpaRepository<InsCompany, Integer> {
    @Query(value = "SELECT ic.* FROM ins_company ic "
            + "LEFT JOIN ins_product ip ON ic.ins_company_id = ip.ins_company_id WHERE "
            + "(:insCompanyId IS NULL OR ic.ins_company_id = :insCompanyId) AND "
            + "(:insCompanyName IS NULL OR ic.ins_company_name LIKE CONCAT(:insCompanyName, '%')) AND "
            + "(:insCompanyBulstat IS NULL OR ic.ins_company_bulstat LIKE CONCAT(:insCompanyBulstat, '%')) AND "
            + "(:insTypeId IS NULL OR ip.ins_type_id = :insTypeId)",
            nativeQuery = true)
    List<InsCompany> findAllBySearchParams(@Param("insCompanyId") Integer insCompanyId,
                                           @Param("insCompanyName") String insCompanyName,
                                           @Param("insCompanyBulstat") String insCompanyBulstat, @Param("insTypeId") Integer insTypeId);

    InsCompany findByInsCompanyName(String insCompanyName);

    boolean existsByInsCompanyName(String insCompanyName);

    InsCompany findByInsProducts(InsProduct insProduct);
}
