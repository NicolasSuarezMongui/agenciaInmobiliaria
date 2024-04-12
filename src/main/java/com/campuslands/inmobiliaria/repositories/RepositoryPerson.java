package com.campuslands.inmobiliaria.repositories;

import org.springframework.data.repository.CrudRepository;

import com.campuslands.inmobiliaria.repositories.entities.Person;

public interface RepositoryPerson extends CrudRepository<Person, Long>{

    Person findByEmail(String email);

}
