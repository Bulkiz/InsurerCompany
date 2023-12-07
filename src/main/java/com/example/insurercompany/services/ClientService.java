package com.example.insurercompany.services;

import com.example.insurercompany.entities.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAllBySearchParams(String clientEgnBulstat, String clientType, String clientFullname, String email, String telephone);

    Client saveClient(Client client);

    Client getByClientId(Integer clientId);
}
