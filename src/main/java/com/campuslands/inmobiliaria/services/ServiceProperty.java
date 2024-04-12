package com.campuslands.inmobiliaria.services;

import java.util.List;

import com.campuslands.inmobiliaria.dtos.PropertyDTO;
import com.campuslands.inmobiliaria.exceptions.BussinesRuleException;

public interface ServiceProperty {
    
    List<PropertyDTO> findAll();

    PropertyDTO save(PropertyDTO property) throws BussinesRuleException;

    PropertyDTO findById(Long id) throws BussinesRuleException;

    List<PropertyDTO> findByType(String type);

    PropertyDTO update(Long id, PropertyDTO property);

    void deleteById(Long id);

}
