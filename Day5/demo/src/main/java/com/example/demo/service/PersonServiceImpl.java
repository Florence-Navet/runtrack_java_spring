package com.example.demo.service;

import com.example.demo.Model.Person;
import com.example.demo.Model.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repo;

    public PersonServiceImpl(PersonRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Person> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<Person> findByName(String name) {
        return Optional.ofNullable(repo.findByName(name));
    }

    @Override
    public Optional<Person> findByAge(Integer age) {
        return Optional.ofNullable(repo.findByAge(age));
    }

    @Override
    public Person save(Person p) {
        return repo.save(p);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }
}