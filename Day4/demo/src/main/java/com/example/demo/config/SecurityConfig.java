package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Autorisations
                .authorizeHttpRequests(auth -> auth
                        // ressources statiques / H2
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        // pages publiques
                        .requestMatchers("/", "/hello","/login", "/view").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // le reste nécessite une auth
                        .anyRequest().authenticated()
                )
                // Form login personnalisé
                .formLogin(login  -> login
                        .loginPage("/login") //GET : page de login
                        .loginProcessingUrl("/login") //POST : action du login
                        .defaultSuccessUrl("/view",  true)// redirection après succès
                        .failureUrl("/login?error")
                        .permitAll()//
                        )
                // Authentification HTTP Basic - pas utiliser en page HTML
                .httpBasic(httpBasic -> httpBasic.disable())
                // CSRF : on garde CSRF, mais on l'ignore pour H2-console
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                )
                // H2 a besoin d'iframes
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    // Démo: users en mémoire (remplace ensuite par JPA si tu veux)
    @Bean
    UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var user = User.withUsername("user").password(encoder.encode("password")).roles("USER").build();
        var admin = User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    // Utilisateurs en mémoire (pour démarrer vite)
//    @Bean
//    UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        var user = User.withUsername("user")
//                .password(encoder.encode("password"))
//                .roles("USER")
//                .build();
//        var admin = User.withUsername("admin")
//                .password(encoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
