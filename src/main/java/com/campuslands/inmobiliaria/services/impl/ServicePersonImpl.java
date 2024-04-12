package com.campuslands.inmobiliaria.services.impl;

import org.springframework.stereotype.Service;

import com.campuslands.inmobiliaria.repositories.RepositoryPerson;
import com.campuslands.inmobiliaria.repositories.entities.Person;
import com.campuslands.inmobiliaria.services.ServicePerson;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicePersonImpl implements ServicePerson{

    private final RepositoryPerson repositoryPerson;

    @Override
    @Transactional
    public Person save(Person person) {
        return repositoryPerson.save(person);
    }

    @Override
    @Transactional
    public Person update(Person person) {
        if(person.getId() == null){
            return null;
        }
        return repositoryPerson.save(person);
    }
    
}
