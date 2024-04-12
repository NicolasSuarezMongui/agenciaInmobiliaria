package com.campuslands.inmobiliaria.services;

import com.campuslands.inmobiliaria.repositories.entities.Person;

public interface ServicePerson {
    
    Person save(Person person);

    Person update(Person person);

}
