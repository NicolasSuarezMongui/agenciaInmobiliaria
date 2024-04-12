package com.campuslands.inmobiliaria.dtos;

import com.campuslands.inmobiliaria.repositories.entities.Office;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class OfficeDTO {
    
    private Long id;

    @JsonView(Office.class)
    private String address;

    private Long zoneId;
    @JsonView(Office.class)
    private String zoneName;

}
