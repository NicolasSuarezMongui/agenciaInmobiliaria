package com.campuslands.inmobiliaria.dtos;

import java.util.List;

import com.campuslands.inmobiliaria.repositories.entities.Feature;
import com.campuslands.inmobiliaria.repositories.entities.Property;
import com.campuslands.inmobiliaria.repositories.entities.Stay;
import com.campuslands.inmobiliaria.repositories.entities.types.OfferType;
import com.campuslands.inmobiliaria.repositories.entities.types.PropertyType;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class PropertyDTO {
    
    private Long id;

    @JsonView(Property.class)
    private String address;

    @JsonView(Property.class)
    private float price;

    @JsonView(Property.class)
    private float area;

    @JsonView(Property.class)
    private OfferType offerType;

    @JsonView(Property.class)
    private PropertyType propertyType;

    private Long owner_id;
    @JsonView(Property.class)
    private String owner_name;
    @JsonView(Property.class)
    private String owner_phone;
    @JsonView(Property.class)
    private String owner_email;


    private Long office_id;
    @JsonView(Property.class)
    private String office_zone;
    @JsonView(Property.class)
    private int numb_visits;

    @JsonView(Property.class)
    private List<Feature> features;

    @JsonView(Property.class)
    private List<Stay> stays;


}
