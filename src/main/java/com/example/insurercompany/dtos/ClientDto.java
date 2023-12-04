package com.example.insurercompany.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientDto {
    private Integer clientId;
    private String clientType;
    private String clientEgnBulstat;
    private String clientFullname;
    private String email;
    private String telephone;
    private String adressText;
    private String clientNote;
}
