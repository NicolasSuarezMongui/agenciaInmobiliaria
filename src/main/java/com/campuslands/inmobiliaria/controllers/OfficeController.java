package com.campuslands.inmobiliaria.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campuslands.inmobiliaria.config.OfficeDTOConverter;
import com.campuslands.inmobiliaria.dtos.OfficeDTO;
import com.campuslands.inmobiliaria.repositories.entities.Office;
import com.campuslands.inmobiliaria.services.ServiceOffice;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/offices")
@AllArgsConstructor
public class OfficeController {

    private ServiceOffice serviceOffice;

    @GetMapping
    @JsonView(Office.class)
    public ResponseEntity<List<OfficeDTO>> getOffices() {

        List<OfficeDTO> offices = serviceOffice.findAll();
        return new ResponseEntity<>(offices, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody OfficeDTO office, BindingResult result) {
        OfficeDTO newOffice = null;
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
            newOffice = serviceOffice.save(office);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Partner has been successfully created");
        response.put("partner", newOffice);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateOffice(@Valid @RequestBody OfficeDTO office, BindingResult result) {
        Office newOffice = null;
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
            newOffice = serviceOffice.update(office);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Partner has been successfully created");
        response.put("partner", newOffice);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            serviceOffice.deleteById(id);
        } catch (DataAccessException e){
            response.put("message", "Error when inserting in the database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        response.put("message", "Partner delete successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
