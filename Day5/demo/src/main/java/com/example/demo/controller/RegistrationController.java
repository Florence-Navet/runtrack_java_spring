package com.example.demo.controller;

import com.example.demo.service.AppUserService;
import com.example.demo.web.RegistrationForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final AppUserService userService;

    public RegistrationController(AppUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("form", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String submit(@Valid @ModelAttribute("form") RegistrationForm form,
                         BindingResult errors,
                         Model model) {
        if (errors.hasErrors()) return "register";

        if(!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "mismatch", "Les mots de passe ne correspondent pas");
            return "register";
        }

        try {
            userService.register(form.getUsername(), form.getPassword());

        }catch (IllegalArgumentException e) {
            errors.rejectValue("username", "exists", e.getMessage());
            return "register";
        }
        model.addAttribute("msg", "Inscription réussie, vous pouvez vous connecter yep !!");
        return "login";
    }


//    @PostMapping("/register")
//    public String register (@RequestParam String username,
//                            @RequestParam String password,
//                            Model model) {
//        userService.register(username, password);
//        model.addAttribute("msg","Inscription réussie, vous pouvez vous connecter !");
//        return "redirect:/view";
//    }
}