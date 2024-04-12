package com.campuslands.inmobiliaria.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.campuslands.inmobiliaria.repositories.entities.Property;

public interface RepositoryProperty extends CrudRepository<Property, Long>{

}
