package com.campuslands.inmobiliaria.services.impl;

import org.springframework.stereotype.Service;

import com.campuslands.inmobiliaria.repositories.RepositoryVisit;
import com.campuslands.inmobiliaria.repositories.entities.Visit;
import com.campuslands.inmobiliaria.services.ServiceVisit;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceVisitImpl implements ServiceVisit{

    private final RepositoryVisit repositoryVisit;

    @Override
    public Visit save(Visit visit) {
        return repositoryVisit.save(visit);
    }
    

}
