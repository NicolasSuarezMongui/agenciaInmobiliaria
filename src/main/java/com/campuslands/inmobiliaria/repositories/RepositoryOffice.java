package com.campuslands.inmobiliaria.repositories;

import org.springframework.data.repository.CrudRepository;

import com.campuslands.inmobiliaria.repositories.entities.Office;

public interface RepositoryOffice extends CrudRepository<Office, Long>{
    
}
