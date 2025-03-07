package com.labprog.labprog.services;


import com.labprog.labprog.model.repositories.AdminRepository;
import com.labprog.labprog.model.repositories.CustomerRepository;
import com.labprog.labprog.model.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails admin = adminRepository.findByUsername(username);
        if (admin != null) {
            return admin;
        }

        UserDetails employee = employeesRepository.findByUsername(username);
        if (employee != null) {
            return employee;
        }

        UserDetails customer = customerRepository.findByUsername(username);
        if (customer != null) {
            return customer;
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
