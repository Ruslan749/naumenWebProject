package com.example.naumenwebproject.mapper;

import com.example.naumenwebproject.dto.PersonDto;
import com.example.naumenwebproject.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public PersonDto personToDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setUsername(person.getUsername());
        personDto.setPhone(person.getPhone());

        return personDto;
    }
}
