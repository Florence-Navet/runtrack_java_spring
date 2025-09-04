package com.example.demo.controller;

import com.example.demo.Model.Person;
import com.example.demo.Model.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class ViewController {
    private final PersonRepository personRepository;

    public ViewController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/view")
    public String afficherMots(Model model) {
        model.addAttribute("message", "Coucou c'est le jour 2 !!!!");
        model.addAttribute("mots", List.of("ça me saoule grave", "Olivier", "Toulon", "Adeline", "Blabla"));
        model.addAttribute("formData", new FormData());
        List<Person> bidule = personRepository.findAll();
        model.addAttribute("persons", bidule);
        return "view";
    }

    @PostMapping("/view")
    public String handleForm(@Valid @ModelAttribute("formData") FormData formData,
                             BindingResult errors,
                             Model model) {
        // Ré-alimente le modèle
        model.addAttribute("message", "Coucou c'est le jour 2 !!!!");
        model.addAttribute("mots", List.of("ça me saoule grave", "Olivier", "Toulon", "Adeline", "Blabla"));

        if (errors.hasErrors()) {

            return "view";

        }

        Person person = new Person(formData.getWelcome(), formData.getAge());
        personRepository.save(person);

        List<Person> bidule = personRepository.findAll();
        model.addAttribute("persons", bidule);

        model.addAttribute("welcomeMsg",
                "Bienvenue, " + formData.getWelcome() + " ! Tu as " + formData.getAge() + " ans.");
        return "view";
    }

    // MISE À JOUR (POST /view/{id}/update)
    @PostMapping("/view/{id}/update")
    public String updatePerson(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam Integer age) {
        personRepository.findById(id).ifPresent(p -> {
            p.setName(name);
            p.setAge(age);
            personRepository.save(p);
        });
        return "redirect:/view";
    }

    // SUPPRESSION (POST /view/{id}/delete)
    @PostMapping("/view/{id}/delete")
    public String deletePerson(@PathVariable Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }
        return "redirect:/view";


    }
}