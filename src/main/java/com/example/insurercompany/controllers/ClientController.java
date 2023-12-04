package com.example.insurercompany.controllers;

import com.example.insurercompany.dtos.ClientDto;
import com.example.insurercompany.mappers.ClientMapper;
import com.example.insurercompany.services.ClientService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;

    @GetMapping
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity<List<ClientDto>> findAll(
            @RequestParam(required = false) String clientEgnBulstat,
            @RequestParam(required = false) String clientType,
            @RequestParam(required = false) String clientFullname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String telephone
    ) {
        List<ClientDto> clientDtos = clientService.findAllBySearchParams(clientEgnBulstat, clientType, clientFullname, email, telephone)
                .stream().map(client -> clientMapper.toDto(client)).toList();
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientMapper.toDto(clientService.saveClient(clientMapper.toEntity(clientDto))), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientMapper.toDto(clientService.saveClient(clientMapper.toEntity(clientDto))), HttpStatus.OK);
    }
}
