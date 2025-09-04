package com.example.demo.service;

import com.example.demo.Model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> findAll();
    Optional<Person> findById(Long id);
    Optional<Person> findByName(String name);
    Optional<Person> findByAge(Integer age);
    Person save(Person p);
    void deleteById(Long id);
    boolean existsById(Long id);
}
