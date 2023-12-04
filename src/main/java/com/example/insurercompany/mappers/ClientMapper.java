package com.example.insurercompany.mappers;

import com.example.insurercompany.dtos.ClientDto;
import com.example.insurercompany.entities.Client;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientMapper implements ModelMapper<ClientDto, Client> {
    @Override
    public ClientDto toDto(Client client) {
        ClientDto clientDto = ClientDto.builder()
                .clientId(client.getClientId())
                .clientType(client.getClientType())
                .clientEgnBulstat(client.getClientEgnBulstat())
                .clientFullname(client.getClientFullname())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .adressText(client.getAdressText())
                .clientNote(client.getClientNote())
                .build();
        clientDto.setClientType(setClientType(clientDto.getClientType(), true));
        return clientDto;
    }

    @Override
    public Client toEntity(ClientDto clientDto) {
        Client client = Client.builder()
                .clientId(clientDto.getClientId())
                .clientType(clientDto.getClientType())
                .clientEgnBulstat(clientDto.getClientEgnBulstat())
                .clientFullname(clientDto.getClientFullname())
                .email(clientDto.getEmail())
                .telephone(clientDto.getTelephone())
                .adressText(clientDto.getAdressText())
                .clientNote(clientDto.getClientNote())
                .build();
        setClientType(client.getClientType(), false);
        client.setClientType(setClientType(client.getClientType(), false));
        return client;
    }

    @Override
    public List<ClientDto> allToDtos(List<Client> clients) {
        return clients.stream().map(this::toDto).toList();
    }

    @Override
    public List<Client> allToEntities(List<ClientDto> clientDtos) {
        return clientDtos.stream().map(this::toEntity).toList();
    }

    public String setClientType(String clientType, boolean ToDto) {
        if (ToDto) {
            if (clientType.equals("I")) {
                clientType = "INDIVIDUAL";
            } else {
                clientType = "CORPORATE";
            }
        } else {
            if (clientType.equals("INDIVIDUAL")) {
                clientType = ("I");
            } else {
                clientType = ("C");
            }
        }
        return clientType;
    }
}
