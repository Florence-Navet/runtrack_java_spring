package com.example.demo.controller;

import com.example.demo.Model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ViewController {

    private final PersonService personService;

    public ViewController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/view")
    public String afficherMots(Model model) {
        model.addAttribute("message", "Coucou c'est le jour 2 !!!!");
        model.addAttribute("mots", List.of("ça me saoule grave", "Olivier", "Toulon", "Adeline", "Blabla"));
        model.addAttribute("formData", new FormData());
        model.addAttribute("persons", personService.findAll());
        return "view";
    }

    // Zones de test
    @GetMapping("/coco")
    @ResponseBody
    public String zoneProtegee() {
        return "Bienvenue dans la zone coco !";
    }


    @GetMapping("/user")
    @ResponseBody
    public String userZone() {
        return "Bienvenue dans la zone user !";
    }


    @PostMapping("/view")
    public String handleForm(@Valid @ModelAttribute("formData") FormData formData,
                             BindingResult errors,
                             Model model) {
        model.addAttribute("message", "Coucou c'est le jour 2 !!!!");
        model.addAttribute("mots", List.of("ça me saoule grave", "Olivier", "Toulon", "Adeline", "Blabla"));

        if (errors.hasErrors()) {
            model.addAttribute("persons", personService.findAll());
            return "redirect:/view";
        }

        personService.save(new Person(formData.getWelcome(), formData.getAge()));
        model.addAttribute("welcomeMsg", "Bienvenue, " + formData.getWelcome() + " ! Tu as " + formData.getAge() + " ans.");
        model.addAttribute("persons", personService.findAll());
        return "redirect:/view"; // ou "redirect:/view" si tu veux PRG
    }

    // UPDATE : ADMIN uniquement
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/view/{id}/update")
    public String updatePerson(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam Integer age) {
        personService.findById(id).ifPresent(p -> {
            p.setName(name);
            p.setAge(age);
            personService.save(p);
        });
        return "redirect:/view";
    }

    // DELETE : ADMIN uniquement
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/view/{id}/delete")
    public String deletePerson(@PathVariable Long id) {
        if (personService.existsById(id)) {
            personService.deleteById(id);
        }
        return "redirect:/view";
    }
}