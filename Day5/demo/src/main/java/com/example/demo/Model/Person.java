package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "T'es obligé de mettre ton nom !!")
    @Size(min = 2, max = 50, message = "T'as intéret de mettre entre 2 et 50 caractères !")
    private String name;

    @NotNull(message = "Obligé l'age, même si tu veux pas !!")
    @Min(value = 0, message = "Age >= 0. On prend pas les bébés.")
    @Max(value = 150, message ="Age <=  150. C'est pas prévu pour les dinosaures !!")
    private Integer age;


    public Person() { }

    // Constructeur pratique
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + name + "', age=" + age + "}";
    }
}