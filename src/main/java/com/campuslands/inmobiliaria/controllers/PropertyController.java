package com.campuslands.inmobiliaria.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campuslands.inmobiliaria.dtos.OfficeDTO;
import com.campuslands.inmobiliaria.dtos.PropertyDTO;
import com.campuslands.inmobiliaria.repositories.RepositoryProperty;
import com.campuslands.inmobiliaria.repositories.entities.Feature;
import com.campuslands.inmobiliaria.repositories.entities.Stay;
import com.campuslands.inmobiliaria.services.ServiceFeature;
import com.campuslands.inmobiliaria.services.ServiceProperty;
import com.campuslands.inmobiliaria.services.ServiceStay;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/properties")
@AllArgsConstructor
public class PropertyController {

    private ServiceProperty serviceProperty;
    private ServiceFeature serviceFeature;
    private ServiceStay serviceStay;

    private RepositoryProperty repositoryProperty;

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getProperties() {

        List<PropertyDTO> properties = serviceProperty.findAll();
        return new ResponseEntity<>(properties, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody PropertyDTO office, BindingResult result) {
        PropertyDTO newOffice = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            newOffice = serviceProperty.save(office);
            for(Feature feature : office.getFeatures()) {
                feature.setProperty(repositoryProperty.findById(newOffice.getId()).get());
                serviceFeature.save(feature);
            }
            for(Stay stay : office.getStays()) {
                stay.setProperty(repositoryProperty.findById(newOffice.getId()).get());
                serviceStay.save(stay);
            }
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Property has been successfully created");
        response.put("partner", newOffice);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/{type}")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByType(@PathVariable String type) {

        List<PropertyDTO> properties = serviceProperty.findByType(type.toUpperCase());
        return new ResponseEntity<>(properties, HttpStatus.OK);

    }
    
}
