package com.example.demo.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FormData {

    @NotBlank(message = "Votre nom est obligatoire")
    private String welcome;

    @NotNull(message = "L'âge est obligatoire")
    @Min(value = 0, message = "L'âge doit être positif")
    @Max(value = 120, message = "Aie, t'es trop vieux, hihihi !")
    private Integer age;


    public String getWelcome() { return welcome; }
    public void setWelcome(String welcome) { this.welcome = welcome; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}
