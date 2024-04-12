package com.campuslands.inmobiliaria.repositories;

import org.springframework.data.repository.CrudRepository;

import com.campuslands.inmobiliaria.repositories.entities.Property;

public interface RepositoryProperty extends CrudRepository<Property, Long>{

}
