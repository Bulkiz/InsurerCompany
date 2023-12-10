package com.example.insurercompany.repositories;

import com.example.insurercompany.entities.InsCompany;
import com.example.insurercompany.entities.InsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InsProductRepository extends JpaRepository<InsProduct, Integer> {

    @Query(value = "SELECT ip.* FROM ins_product ip "
            + "LEFT JOIN ins_company ic ON ic.ins_company_id = ip.ins_company_id WHERE "
            + "(:insProdCode IS NULL OR ip.ins_prod_code = :insProdCode) AND "
            + "(:insCompanyId IS NULL OR ic.ins_company_id = :insCompanyId) AND "
            + "(:insProdDeferred IS NULL OR ip.ins_prod_deferred = :insProdDeferred) AND "
            + "(:insProdName IS NULL OR ip.ins_prod_name LIKE CONCAT(:insProdName, '%')) AND "
            + "(:insProdPremPerc IS NULL OR ip.ins_prod_prem_perc <= :insProdPremPerc) AND "
            + "(:insProdComissPerc IS NULL OR ip.ins_prod_comiss_perc <= :insProdComissPerc) AND "
            + "(:insTypeId IS NULL OR ip.ins_type_id = :insTypeId)",
            nativeQuery = true)
    List<InsProduct> findAllBySearchParams(@Param("insProdCode") Integer insProdCode, @Param("insProdName") String insProdName,
                                           @Param("insProdDeferred") String insProdDeferred, @Param("insTypeId") Integer insTypeId,
                                           @Param("insProdPremPerc") BigDecimal insProdPremPerc, @Param("insProdComissPerc") BigDecimal insProdComissPerc,
                                           @Param("insCompanyId") Integer insCompanyId);

    boolean existsByInsProdName(String insProdName);

    List<InsProduct> findAllByInsCompany(InsCompany insCompany);
}
