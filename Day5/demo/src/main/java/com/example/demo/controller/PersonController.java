package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



import com.example.demo.Model.Person;
import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;

// PersonController.java
@Controller
public class PersonController {

    private final PersonService personService;
    public PersonController(PersonService personService) { this.personService = personService; }

    @GetMapping("/persons")
    public String list(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "persons"; // => templates/persons.html
    }
}
