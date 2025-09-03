package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Value("${greeting.message}")
    private String greetingMessage;

    @Value("${greeting.tools}")
    private String toolsMessage;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", greetingMessage);
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("Forcer le redémarrage DevTools");
        System.out.println("DevTools est top nul");

        return greetingMessage + " | " + toolsMessage;
    }
}