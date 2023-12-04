package com.example.insurercompany.repositories;

import com.example.insurercompany.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT c.* FROM client c WHERE "
            + "(:clientEgnBulstat IS NULL OR c.client_egn_bulstat LIKE CONCAT(:clientEgnBulstat, '%')) AND "
            + "(:clientType IS NULL OR c.client_type = :clientType) AND "
            + "(:clientFullname IS NULL OR c.client_fullname LIKE CONCAT(:clientFullname, '%')) AND "
            + "(:email IS NULL OR c.email LIKE CONCAT(:email, '%')) AND "
            + "(:telephone IS NULL OR c.telephone LIKE CONCAT(:telephone, '%'))",
            nativeQuery = true)
    List<Client> findAllBySearchParams(@Param("clientEgnBulstat") String clientEgnBulstat, @Param("clientType") String clientType,
                                       @Param("clientFullname") String clientFullname, @Param("email") String email, @Param("telephone") String telephone);
}
