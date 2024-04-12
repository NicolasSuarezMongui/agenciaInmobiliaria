package com.campuslands.inmobiliaria.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.inmobiliaria.config.OfficeDTOConverter;
import com.campuslands.inmobiliaria.dtos.OfficeDTO;
import com.campuslands.inmobiliaria.exceptions.BussinesRuleException;
import com.campuslands.inmobiliaria.repositories.RepositoryOffice;
import com.campuslands.inmobiliaria.repositories.RepositoryZone;
import com.campuslands.inmobiliaria.repositories.entities.Office;
import com.campuslands.inmobiliaria.repositories.entities.Zone;
import com.campuslands.inmobiliaria.services.ServiceOffice;

@Service
public class ServiceOfficeImpl implements ServiceOffice{

    private RepositoryOffice repositoryOffice;
    private RepositoryZone repositoryZone;

    private OfficeDTOConverter convert;

    @Override
    @Transactional(readOnly = true)
    public List<OfficeDTO> findAll() {
        List<Office> offices = (List<Office>) repositoryOffice.findAll();
        return offices.stream()
                    .map(office -> convert.convertOfficeDTO(office))
                    .toList();
    }

    @Override
    @Transactional
    public OfficeDTO save(OfficeDTO office) throws BussinesRuleException {
        Optional<Zone> currentZone = repositoryZone.findById(office.getZoneId());
        if(currentZone.isPresent()) {
            Office newOffice = convert.convertOffice(office);
            newOffice.setZone(currentZone.get());
            repositoryOffice.save(newOffice);
            return convert.convertOfficeDTO(newOffice);
        } else {
            throw new BussinesRuleException("11058","Zone not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Office findById(Long id) throws BussinesRuleException {
        Optional<Office> office = repositoryOffice.findById(id);
        if(office.isPresent()) {
            return office.get();
        } else {
            throw new BussinesRuleException("11059","Office not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public Office update(OfficeDTO office) {
        if (office.getId() == null){
            return null;
        }

        Optional<Office> updateOffice = repositoryOffice.findById(office.getId());
        if (updateOffice.isPresent()) { 
            return repositoryOffice.save(convert.convertOffice(office));
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Office> office = repositoryOffice.findById(id);
        if(office.isPresent()) {
            repositoryOffice.delete(office.get());
        } 
    }
    
}
