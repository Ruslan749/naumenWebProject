package com.example.naumenwebproject.service;

import com.example.naumenwebproject.model.Person;
import com.example.naumenwebproject.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();

        return userDetails.getUsername();
    }

    public Person getCurrentPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (Person) authentication.getPrincipal();
    }
}
