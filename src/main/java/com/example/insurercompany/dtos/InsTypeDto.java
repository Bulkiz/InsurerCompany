package com.example.insurercompany.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InsTypeDto {
    private Integer insTypeId;
    private String insTypeName;
}
