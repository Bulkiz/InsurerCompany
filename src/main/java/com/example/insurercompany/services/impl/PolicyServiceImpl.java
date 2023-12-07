package com.example.insurercompany.services.impl;

import com.example.insurercompany.entities.Maturity;
import com.example.insurercompany.entities.Payments;
import com.example.insurercompany.entities.Policy;
import com.example.insurercompany.repositories.*;
import com.example.insurercompany.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PolicyServiceImpl implements PolicyService {
    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private InsCompanyRepository insCompanyRepository;
    @Autowired
    private InsProductRepository insProductRepository;
    @Autowired
    private MaturityRepository maturityRepository;
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    public Policy savePolicy(Policy policy, Integer maturityCount) {
        policy.setModifDate(LocalDateTime.now());
        policy.getInsProduct().setInsCompany(insCompanyRepository.findByInsProducts(policy.getInsProduct()));
        Policy savedPolicy = policyRepository.save(policy);

        if (maturityCount != null && !maturityRepository.existsByPolicy(policy)) {
            createMaturities(maturityCount, savedPolicy);
        }

        return savedPolicy;
    }

    @Override
    public List<Policy> findAllBySearchParams(String policyNo, LocalDateTime policyDate, String clientEgnBulstat, String clientFullname, Integer insProdCode) {
        return policyRepository.findAllBySearchParams(policyNo, policyDate, clientEgnBulstat, clientFullname, insProdCode);
    }

    @Override
    public List<Policy> getAllByInsProductId(Integer insProductId) {
        return policyRepository.findAllByInsProduct(insProductRepository.findById(insProductId).get());
    }

    @Override
    public List<Maturity> getMaturitiesByPolicyId(Integer policyId) {
        return maturityRepository.findAllByPolicy(policyRepository.findById(policyId).get())
                .stream().filter(maturity -> maturity.getMaturityPaid().equals("N")).toList();
    }

    @Override
    public void payMaturity(Integer maturityId) {
        Maturity maturity = maturityRepository.findById(maturityId).get();
        Policy policy = policyRepository.findByMaturities(maturity);
        Payments payments = new Payments();
        payments.setPolicy(policy);
        payments.setPaymentSum(maturity.getMaturitySum());
        payments.setPaymentDate(LocalDateTime.now());
        payments.setModifDate(LocalDateTime.now());
        paymentsRepository.save(payments);
        maturityRepository.payMaturity(maturityId);
    }

    @Override
    public List<Payments> getPaymentsByPolicyId(Integer policyId) {
        return paymentsRepository.findAllByPolicy(policyRepository.findById(policyId).get());
    }

    @Override
    public Policy getByPolicyId(Integer policyId) {
        return policyRepository.findById(policyId).get();
    }

    @Override
    public Maturity getByMaturityId(Integer maturityId) {
        return maturityRepository.findById(maturityId).get();
    }

    @Override
    public Payments getByPaymentId(Integer paymentId) {
        return paymentsRepository.findById(paymentId).get();
    }

    private void createMaturities(Integer maturityCount, Policy savedPolicy) {
        Duration policyDuration = Duration.between(savedPolicy.getPolicyBeginDate(), savedPolicy.getPolicyEndDate());
        Duration maturityDuration = policyDuration.dividedBy(maturityCount);
        LocalDateTime beginDate = savedPolicy.getPolicyBeginDate();
        BigDecimal maturitySum = savedPolicy.getPolicySum().add(savedPolicy.getPolicyTax()).add(savedPolicy.getPolicyInsComiss()).
                add(savedPolicy.getPolicyPremia()).divide(BigDecimal.valueOf(maturityCount), RoundingMode.FLOOR);
        for (int i = 0; i < maturityCount; i++) {
            Maturity maturity = new Maturity();
            LocalDateTime nextMaturityDate = beginDate.plus(maturityDuration);
            maturity.setMaturityDate(nextMaturityDate);
            maturity.setPolicy(savedPolicy);
            maturity.setMaturityPaid("N");
            maturity.setMaturitySum(maturitySum);
            maturity.setMaturityNote(maturity.getMaturitySum() + " must be paid by: " + maturity.getMaturityDate() + "!");
            maturity.setModifDate(LocalDateTime.now());
            maturityRepository.save(maturity);
            beginDate = nextMaturityDate;

        }
    }
}
