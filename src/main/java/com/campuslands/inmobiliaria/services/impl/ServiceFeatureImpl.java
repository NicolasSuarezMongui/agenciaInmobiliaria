package com.campuslands.inmobiliaria.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campuslands.inmobiliaria.repositories.RepositoryFeature;
import com.campuslands.inmobiliaria.repositories.entities.Feature;
import com.campuslands.inmobiliaria.services.ServiceFeature;

@Service
public class ServiceFeatureImpl implements ServiceFeature{

    private RepositoryFeature repositoryFeature;

    @Override
    @Transactional
    public Feature save(Feature feature) {
        return repositoryFeature.save(feature);
    }
    
}
