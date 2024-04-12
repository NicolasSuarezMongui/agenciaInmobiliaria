package com.campuslands.inmobiliaria.services.impl;

import org.springframework.stereotype.Service;

import com.campuslands.inmobiliaria.repositories.RepositoryStay;
import com.campuslands.inmobiliaria.repositories.entities.Stay;
import com.campuslands.inmobiliaria.services.ServiceStay;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceStayImpl implements ServiceStay{

    private final RepositoryStay repositoryStay;

    @Override
    public Stay save(Stay stay) {
        return repositoryStay.save(stay);
    }
    


}
