package com.example.demo.config;

import com.example.demo.repository.AppUserRepository;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
                        .requestMatchers("/", "/hello","/login", "/view", "/register").permitAll()

                        //creation des deux roles USER ou ADMIN
                        .requestMatchers(HttpMethod.POST, "/view").hasAnyRole("USER", "ADMIN")

                        //action reservé à l'admin
                        .requestMatchers(HttpMethod.POST,"/view/*/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/view/*/delete").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // le reste nécessite une auth
                        .anyRequest().authenticated()
                )
                // Form login personnalisé
                .formLogin(form  -> form
                        .loginPage("/login") //GET : page de login
                        .loginProcessingUrl("/login") //POST : action du login
                        .defaultSuccessUrl("/view",  true)// redirection après succès
                                .failureUrl("/login?error")
                        .permitAll()//
                        )
                .requestCache(rc -> rc.disable())

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())

                // H2 console : CSRF ignoré + iframes autorisés
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                // H2 a besoin d'iframes
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    // Démo: users en mémoire (remplace ensuite par JPA si tu veux)
//    @Bean
//    UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        var user = User.withUsername("user").password(encoder.encode("password")).roles("USER").build();
//        var admin = User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//version BDD
    @Bean
    UserDetailsService userDetailsService(AppUserRepository repo) {
        return username -> repo.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getRoles().toArray(new String[0]))
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
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
