package com.labprog.labprog.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/addresses/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/addresses/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/addresses/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/addresses/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/carts/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/carts/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/carts/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/carts/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/orders/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/orders/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/orders/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/productSku/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/productSku/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/productSku/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/product/addComment").permitAll()
                        .anyRequest().permitAll())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
