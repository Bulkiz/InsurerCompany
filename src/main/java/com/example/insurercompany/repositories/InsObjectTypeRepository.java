package com.example.insurercompany.repositories;

import com.example.insurercompany.entities.InsObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsObjectTypeRepository extends JpaRepository<InsObjectType, Integer> {
    @Query(value = "SELECT iot.* FROM ins_object_type iot WHERE"
            + "(:insObjectTypeId IS NULL OR iot.ins_object_type_id = :insObjectTypeId) AND "
            + "(:insObjectTypeName IS NULL OR iot.ins_object_type_name LIKE CONCAT(:insObjectTypeName, '%'))",
            nativeQuery = true)
    List<InsObjectType> findAllBySearchParams(@Param("insObjectTypeId") Integer insObjectTypeId, @Param("insObjectTypeName") String insObjectTypeName);

    boolean existsByInsObjectTypeName(String insObjectTypeName);
}
