package com.labprog.labprog.configs;

import com.labprog.labprog.model.repositories.AdminRepository;
import com.labprog.labprog.model.repositories.CustomerRepository;
import com.labprog.labprog.model.repositories.EmployeesRepository;
import com.labprog.labprog.services.TokensService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokensService tokenService;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    EmployeesRepository employeesRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            var username = tokenService.validateToken(token);

            UserDetails adminUser = adminRepository.findByUsername(username);
            if (adminUser != null) {
                var authentication = new UsernamePasswordAuthenticationToken(adminUser, null, adminUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            UserDetails employeeUser = employeesRepository.findByUsername(username);
            if (employeeUser != null) {
                var authentication = new UsernamePasswordAuthenticationToken(employeeUser, null, employeeUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            UserDetails customerUser = employeesRepository.findByUsername(username);
            if (customerUser != null) {
                var authentication = new UsernamePasswordAuthenticationToken(customerUser, null, customerUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
