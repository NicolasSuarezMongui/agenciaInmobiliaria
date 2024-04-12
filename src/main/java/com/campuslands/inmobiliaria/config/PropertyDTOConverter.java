package com.campuslands.inmobiliaria.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.campuslands.inmobiliaria.dtos.PropertyDTO;
import com.campuslands.inmobiliaria.repositories.entities.Property;


@Component
public class PropertyDTOConverter {
    
    private ModelMapper dbm;

    public PropertyDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public PropertyDTO convertPropertyDTO(Property property) {

        PropertyDTO dto = dbm.map(property, PropertyDTO.class);
        if(property.getOwner() != null) {
            dto.setOwner_name(property.getOwner().getName());
            dto.setOwner_phone(property.getOwner().getPhone());
            dto.setOwner_email(property.getOwner().getEmail());
        }
        if(property.getOffice() != null) {
            dto.setOffice_zone(property.getOffice().getZone().getName());
        }
        if(property.getStays()!= null) {
            dto.setNumb_visits(property.getVisits().size());
        }
        return dto;

    }

    public Property convertProperty(PropertyDTO propertyDTO) {
        return dbm.map(propertyDTO, Property.class);
    }


}
