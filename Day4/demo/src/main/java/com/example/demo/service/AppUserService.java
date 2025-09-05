// src/main/java/com/example/demo/service/AppUserService.java
package com.example.demo.service;

import com.example.demo.Model.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AppUserService {
    private final AppUserRepository repo;
    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public AppUser register(String username, String rawPassword) {
        if (repo.existsByUsername(username)) {
            throw new IllegalArgumentException("On te connait déjà, tente pas de recommencer !!");
        }
        var user = new AppUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(rawPassword)); // BCrypt !
        user.setRoles(Set.of("ROLE_USER")); // rôle par défaut
        return repo.save(user);
    }
}
