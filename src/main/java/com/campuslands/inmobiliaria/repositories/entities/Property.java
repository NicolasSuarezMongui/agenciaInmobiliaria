package com.campuslands.inmobiliaria.repositories.entities;

import java.util.List;

import com.campuslands.inmobiliaria.repositories.entities.types.OfferType;
import com.campuslands.inmobiliaria.repositories.entities.types.PropertyType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Office.class)
    private Long id;

    @NotEmpty(message = "Address is required")
    @Column(nullable = false, unique = true)
    @JsonView(Office.class)
    private String address;

    @NotEmpty(message = "Price is required")
    @Column(nullable = false)
    @JsonView(Office.class)
    private float price;

    @NotEmpty(message = "Area is required")
    @Column(nullable = false)
    @JsonView(Office.class)
    private float area;

    @NotEmpty(message = "Offer type is required")
    @Column(nullable = false, name = "offer_type")
    @Enumerated(EnumType.ORDINAL)
    @JsonView(Office.class)
    private OfferType offerType;

    @NotEmpty(message = "Property type is required")
    @Column(nullable = false, name = "property_type")
    @Enumerated(EnumType.ORDINAL)
    @JsonView(Office.class)
    private PropertyType propertyType;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feature> features;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stay> stays;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private Person owner;

    @ManyToOne()
    @JoinColumn(name = "office_id")
    private Office office;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Visit> visits;
}
