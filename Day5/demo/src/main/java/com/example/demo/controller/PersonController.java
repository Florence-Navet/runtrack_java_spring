package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.*;
import com.example.demo.Model.Person;
import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


// PersonController.java
@Controller
public class PersonController {

    private final PersonService personService;
    public PersonController(PersonService personService) { this.personService = personService; }

    @GetMapping("/persons")
    public String list(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "persons";
    }

    // Affiche le formulaire
    @GetMapping("/persons/add")
    public String showAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";     // ton template
    }

    // Traite le formulaire
    @PostMapping("/persons")
    public String addPerson(@Valid @ModelAttribute("person") Person person,
                            BindingResult errors,
                            Model model) {
        if (errors.hasErrors()) {
            return "add-person"; // si erreurs -> on revient au formulaire
        }
        personService.save(person);
        return "redirect:/persons";
    }
}

