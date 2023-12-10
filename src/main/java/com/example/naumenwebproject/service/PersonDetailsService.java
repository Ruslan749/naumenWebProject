package com.example.naumenwebproject.service;


import com.example.naumenwebproject.modals.Person;
import com.example.naumenwebproject.repositories.PeopleRepositories;
import com.example.naumenwebproject.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepositories peopleRepositories;
    @Autowired
    public PersonDetailsService(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepositories.findByUsername(username);

        if (person.isEmpty()){
            throw new UsernameNotFoundException("нет такого человека");
        }

        return new PersonDetails(person.get());
    }
}
