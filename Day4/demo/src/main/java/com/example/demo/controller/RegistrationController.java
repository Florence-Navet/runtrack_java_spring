package com.example.demo.controller;

import com.example.demo.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final AppUserService userService;

    public RegistrationController(AppUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register (@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        userService.register(username, password);
        model.addAttribute("msg","Inscription réussie, vous pouvez vous connecter !");
        return "redirect:/view";
    }
}
