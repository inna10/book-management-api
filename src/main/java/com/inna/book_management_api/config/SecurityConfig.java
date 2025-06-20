package com.inna.book_management_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    /**
     * Configures the security filter chain for the application.
     * Provides basic authentication and role-based access control.
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())// Disable CSRF since it is a none-browser application
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/h2-console/**").permitAll()// Allow access to Swagger and H2 console without authentication
                .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                .requestMatchers("/books/**").authenticated()
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))  // For H2 console
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        //TODO: Use a database or external service for user management in production
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("userpass"))
            .roles("USER")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("adminpass"))
            .roles("USER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
