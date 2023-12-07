package com.example.insurercompany.services;

import com.example.insurercompany.entities.Maturity;
import com.example.insurercompany.entities.Payments;
import com.example.insurercompany.entities.Policy;

import java.time.LocalDateTime;
import java.util.List;

public interface PolicyService {
    Policy savePolicy(Policy policy, Integer maturityCount);

    List<Policy> findAllBySearchParams(String policyNo, LocalDateTime policyDate, String clientEgnBulstat, String clientFullname, Integer insProdCode);

    List<Policy> getAllByInsProductId(Integer insProductId);

    List<Maturity> getMaturitiesByPolicyId(Integer policyId);

    void payMaturity(Integer maturityId);

    List<Payments> getPaymentsByPolicyId(Integer policyId);

    Policy getByPolicyId(Integer policyId);

    Maturity getByMaturityId(Integer maturityId);

    Payments getByPaymentId(Integer paymentId);
}
