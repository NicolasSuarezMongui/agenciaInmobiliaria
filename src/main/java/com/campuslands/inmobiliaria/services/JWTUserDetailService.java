package com.campuslands.inmobiliaria.services;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.campuslands.inmobiliaria.repositories.RepositoryPerson;
import com.campuslands.inmobiliaria.repositories.entities.Person;
import com.campuslands.inmobiliaria.repositories.entities.types.RoleType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JWTUserDetailService implements UserDetailsService{
    
    private final RepositoryPerson repositoryPerson;

    @Override
    public UserDetails loadUserByUsername(String username){
        Person userOptional = repositoryPerson.findByEmail(username);

        return new User(userOptional.getEmail(), userOptional.getPassword(), getAuthorities(userOptional.getRole()));
    }

    private List<GrantedAuthority> getAuthorities(RoleType role) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}
