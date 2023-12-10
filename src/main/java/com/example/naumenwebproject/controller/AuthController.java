package com.example.naumenwebproject.controller;


import com.example.naumenwebproject.modals.Person;
import com.example.naumenwebproject.service.RegistrationServes;
import com.example.naumenwebproject.utill.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationServes registrationServes;
    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationServes registrationServes) {
        this.personValidator = personValidator;
        this.registrationServes = registrationServes;
    }

    // метод для регистрации
    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    // метод для получение данных из формы
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "auth/registration";
    }
    // регистрация данных в базе данных
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult){

        personValidator.validate(person,bindingResult);

        if (bindingResult.hasErrors()){
            return "redirect:/auth/registration";

        }
        registrationServes.register(person);
        return "redirect:/auth/login";

    }
}
