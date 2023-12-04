package com.example.insurercompany.services.impl;

import com.example.insurercompany.entities.Client;
import com.example.insurercompany.repositories.ClientRepository;
import com.example.insurercompany.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAllBySearchParams(String clientEgnBulstat, String clientType, String clientFullname, String email, String telephone) {
        if (clientType != null && clientType.equals("INDIVIDUAL")) {
            clientType = "I";
        } else {
            clientType = "C";
        }
        return clientRepository.findAllBySearchParams(clientEgnBulstat, clientType, clientFullname, email, telephone);
    }

    @Override
    public Client saveClient(Client client) {
        client.setModifDate(LocalDateTime.now());
        return clientRepository.save(client);
    }
}
