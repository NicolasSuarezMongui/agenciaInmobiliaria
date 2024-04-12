package com.campuslands.inmobiliaria.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.inmobiliaria.config.PropertyDTOConverter;
import com.campuslands.inmobiliaria.dtos.PropertyDTO;
import com.campuslands.inmobiliaria.exceptions.BussinesRuleException;
import com.campuslands.inmobiliaria.repositories.RepositoryOffice;
import com.campuslands.inmobiliaria.repositories.RepositoryPerson;
import com.campuslands.inmobiliaria.repositories.RepositoryProperty;
import com.campuslands.inmobiliaria.repositories.entities.Office;
import com.campuslands.inmobiliaria.repositories.entities.Person;
import com.campuslands.inmobiliaria.repositories.entities.Property;
import com.campuslands.inmobiliaria.services.ServiceProperty;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicePropertyImpl implements ServiceProperty{

    private final RepositoryProperty repositoryProperty;
    private final RepositoryPerson repositoryPerson;
    private final RepositoryOffice repositoryOffice;

    private final PropertyDTOConverter convert;

    @Override
    @Transactional(readOnly = true)
    public List<PropertyDTO> findAll() {
        List<Property> properties = (List<Property>) repositoryProperty.findAll();
        return properties.stream()
                    .map(property -> convert.convertPropertyDTO(property))
                    .toList();
    }

    @Override
    @Transactional
    public PropertyDTO save(PropertyDTO property) throws BussinesRuleException{
        Optional<Person> owner = repositoryPerson.findById(property.getOwner_id());
        Optional<Office> office = repositoryOffice.findById(property.getOffice_id());
        Property result = convert.convertProperty(property);
        if(!owner.isPresent()) {
            throw new BussinesRuleException("1080", "Error! Owner doesn't exists", HttpStatus.PRECONDITION_FAILED);
        }
        if(!office.isPresent()) {
            throw new BussinesRuleException("1081", "Error! Office doesn't exists", HttpStatus.PRECONDITION_FAILED);
        }
        result.setOwner(owner.get());
        result.setOffice(office.get());
        return convert.convertPropertyDTO(repositoryProperty.save(result));
    }

    @Override
    public PropertyDTO findById(Long id) throws BussinesRuleException {
        Optional<Property> result = repositoryProperty.findById(id);
        if(!result.isPresent()) {
            throw new BussinesRuleException("1082", "Error! Property doesn't exists", HttpStatus.NOT_FOUND);
        }
        return convert.convertPropertyDTO(result.get());
    }

    @Override
    public PropertyDTO update(Long id, PropertyDTO property) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Long id) {
        Optional<Property> result = repositoryProperty.findById(id);
        if(result.isPresent()){
            repositoryProperty.delete(result.get());
        }
    }
    


}
