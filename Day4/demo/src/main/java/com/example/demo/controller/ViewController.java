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

//    @GetMapping("/admin")
//    @ResponseBody
//    public String adminZone() {
//        return "Bienvenue dans la zone ADMIN !";
//    }

    @GetMapping("/user")
    @ResponseBody
    public String userZone() {
        return "Bienvenue dans la zone user !";
    }

    // Page de login (si tu n'utilises pas WebConfig addViewControllers)
//    @GetMapping("/login")
//    public String loginPage(@RequestParam(value = "error", required = false) String error,
//                            @RequestParam(value = "logout", required = false) String logout,
//                            Model model) {
//        if (error != null)  model.addAttribute("errorMsg", "Identifiants invalides.");
//        if (logout != null) model.addAttribute("logoutMsg", "Vous avez été déconnecté.");
//        return "login";
//    }

    // CREATE : accessible USER et ADMIN (côté HTTP via SecurityConfig)
    // (option : ajouter @PreAuthorize("hasAnyRole('USER','ADMIN')"))
    @PostMapping("/view")
    public String handleForm(@Valid @ModelAttribute("formData") FormData formData,
                             BindingResult errors,
                             Model model) {
        model.addAttribute("message", "Coucou c'est le jour 2 !!!!");
        model.addAttribute("mots", List.of("ça me saoule grave", "Olivier", "Toulon", "Adeline", "Blabla"));

        if (errors.hasErrors()) {
            model.addAttribute("persons", personService.findAll());
            return "view";
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
