package com.example.insurercompany.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InsCompanyDto {
    private Integer insCompanyId;
    private String insCompanyName;
    private String insCompanyBulstat;
    private String insCompanyAddr;
    private String insCompanyContact;
    private String insCompanyTel;
}
