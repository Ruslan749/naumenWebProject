package com.example.naumenwebproject.service;

import com.example.naumenwebproject.model.Person;
import com.example.naumenwebproject.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationService(PeopleRepository peopleRepository,
                               PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(Person person){
        String encodedPassword = passwordEncoder.encode(person.getPassword()); // шифруем пороль
        person.setPassword(encodedPassword); // обнавляем пороль на уже зашифрованный
        person.setRoles("ROLE_USER"); // каждому зарегистрерованому пользователю назначаем роль user

        peopleRepository.save(person);
    }
}
