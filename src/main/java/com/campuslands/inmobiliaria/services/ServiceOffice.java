package com.campuslands.inmobiliaria.services;

import java.util.List;

import com.campuslands.inmobiliaria.dtos.OfficeDTO;
import com.campuslands.inmobiliaria.exceptions.BussinesRuleException;
import com.campuslands.inmobiliaria.repositories.entities.Office;

public interface ServiceOffice {
    
    List<OfficeDTO> findAll();

    OfficeDTO save(OfficeDTO office) throws BussinesRuleException;

    Office findById(Long id) throws BussinesRuleException;

    Office update(OfficeDTO office);

    void deleteById(Long id);

}
