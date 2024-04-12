package com.campuslands.inmobiliaria.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.inmobiliaria.dtos.OfficeDTO;
import com.campuslands.inmobiliaria.repositories.entities.Office;

@Component
public class OfficeDTOConverter {
    
    private ModelMapper dbm;

    public OfficeDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public OfficeDTO convertOfficeDTO(Office office) {

        OfficeDTO dto = dbm.map(office, OfficeDTO.class);
        if(office.getZone() != null) {
            dto.setZoneId(office.getZone().getId());
            dto.setZoneName(office.getZone().getName());
        }
        return dto;

    }

    public Office convertOffice(OfficeDTO officeDTO) {
        return dbm.map(officeDTO, Office.class);
    }

}
