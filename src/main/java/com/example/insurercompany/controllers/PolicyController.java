package com.example.insurercompany.controllers;

import com.example.insurercompany.dtos.MaturityDto;
import com.example.insurercompany.dtos.PaymentsDto;
import com.example.insurercompany.dtos.PolicyDto;
import com.example.insurercompany.mappers.MaturityMapper;
import com.example.insurercompany.mappers.PaymentsMapper;
import com.example.insurercompany.mappers.PolicyMapper;
import com.example.insurercompany.services.PolicyService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/policies")
public class PolicyController {
    @Autowired
    private PolicyMapper policyMapper;
    @Autowired
    private PolicyService policyService;
    @Autowired
    private MaturityMapper maturityMapper;
    @Autowired
    private PaymentsMapper paymentsMapper;

    @GetMapping
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<List<PolicyDto>> findAll(
            @RequestParam(required = false) String policyNo,
            @RequestParam(required = false) LocalDateTime policyDate,
            @RequestParam(required = false) String clientEgnBulstat,
            @RequestParam(required = false) String clientFullname,
            @RequestParam(required = false) Integer insProdCode
    ) {
        List<PolicyDto> policyDtos = policyService.findAllBySearchParams(policyNo, policyDate, clientEgnBulstat,
                        clientFullname, insProdCode)
                .stream().map(policy -> policyMapper.toDto(policy)).toList();
        return new ResponseEntity<>(policyDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PolicyDto> savePolicy(@RequestBody PolicyDto policyDto) {
        return new ResponseEntity<>(policyMapper.toDto(policyService.savePolicy(policyMapper.toEntity(policyDto), policyDto.getMaturityCount())), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PolicyDto> updatePolicy(@RequestBody PolicyDto policyDto) {
        return new ResponseEntity<>(policyMapper.toDto(policyService.savePolicy(policyMapper.toEntity(policyDto), policyDto.getMaturityCount())), HttpStatus.OK);
    }

    @GetMapping("/ins-product")
    public ResponseEntity<List<PolicyDto>> getAllByInsProductId(@RequestParam Integer insProductId) {
        return new ResponseEntity<>(policyMapper.allToDtos(policyService.getAllByInsProductId(insProductId)), HttpStatus.OK);
    }

    @GetMapping("/maturities")
    public ResponseEntity<List<MaturityDto>> getMaturitiesByPolicyId(@RequestParam Integer policyId) {
        return new ResponseEntity<>(maturityMapper.allToDtos(policyService.getMaturitiesByPolicyId(policyId)), HttpStatus.OK);
    }

    @PutMapping("/maturities")
    public ResponseEntity<String> payMaturity(@RequestParam Integer maturityId) {
        policyService.payMaturity(maturityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentsDto>> getPaymentsByPolicyId(@RequestParam Integer policyId) {
        return new ResponseEntity<>(paymentsMapper.allToDtos(policyService.getPaymentsByPolicyId(policyId)), HttpStatus.OK);
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<PolicyDto> getByPolicyId(@PathVariable Integer policyId) {
        return new ResponseEntity<>(policyMapper.toDto(policyService.getByPolicyId(policyId)), HttpStatus.OK);
    }

    @GetMapping("/maturity/{maturityId}")
    public ResponseEntity<MaturityDto> getByMaturityId(@PathVariable Integer maturityId) {
        return new ResponseEntity<>(maturityMapper.toDto(policyService.getByMaturityId(maturityId)), HttpStatus.OK);
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentsDto> getByPaymentId(@PathVariable Integer paymentId) {
        return new ResponseEntity<>(paymentsMapper.toDto(policyService.getByPaymentId(paymentId)), HttpStatus.OK);
    }
}
