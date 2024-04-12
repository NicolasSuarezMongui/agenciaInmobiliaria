package com.campuslands.inmobiliaria.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campuslands.inmobiliaria.dtos.PropertyDTO;
import com.campuslands.inmobiliaria.services.ServiceProperty;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/properties")
@AllArgsConstructor
public class PropertyController {

    private ServiceProperty serviceProperty;

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getProperties() {

        List<PropertyDTO> properties = serviceProperty.findAll();
        return new ResponseEntity<>(properties, HttpStatus.OK);

    }

    @GetMapping("/{type}")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByType(@RequestParam String type) {

        List<PropertyDTO> properties = serviceProperty.findAll();
        properties.stream().filter(property -> property.getOfferType().equals(type.toUpperCase()));
        return new ResponseEntity<>(properties, HttpStatus.OK);

    }
    
}
