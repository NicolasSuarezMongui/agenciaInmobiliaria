package com.campuslands.inmobiliaria.repositories.entities;

import java.util.List;

import com.campuslands.inmobiliaria.repositories.entities.types.RoleType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "DNI is required")
    @Column(nullable = false, unique = true)
    private String dni;

    @NotEmpty(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Phone is required")
    @Column(nullable = false, unique = true)
    private String phone;

    @NotEmpty(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @NotEmpty(message = "Role is required")
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private RoleType role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Property> properties;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Visit> visits;

}
