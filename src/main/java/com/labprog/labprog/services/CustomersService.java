package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.model.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomersService {
        @Autowired
        CustomerRepository customerRepository;

        private static final Logger logger = LoggerFactory.getLogger(CustomersService.class);

        public List<Customers> findAll() {
            return customerRepository.findAll();
        }

        public Optional<Customers> findById(UUID id) {
            return customerRepository.findById(id);
        }

        @Transactional
        public Customers save(Customers customer) {
            logger.info("Salvando novo cliente: {}", customer.getEmail());
            verifyCustomer(customer, true);
            logger.info("cliente verificado: {}", customer.getEmail());
            return customerRepository.save(customer);
        }

    public Customers update(UUID customerId, Customers updatedCustomer) {
        logger.info("cliente sendo efigtadp: {}", updatedCustomer.getEmail());
        Customers existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        verifyCustomer(updatedCustomer, false);
        logger.info("cliente veridicado para edicao: {}", updatedCustomer.getEmail());
        // Atualizar apenas os campos que realmente mudaram
        if (!updatedCustomer.getEmail().equals(existingCustomer.getEmail())
                && customerRepository.existsByEmail(updatedCustomer.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        if (!updatedCustomer.getUsername().equals(existingCustomer.getUsername())
                && customerRepository.existsByUsername(updatedCustomer.getUsername())) {
            throw new IllegalArgumentException("Nome de usuário já está em uso");
        }
        logger.info("campos da edicao sao validos: {}", updatedCustomer.getEmail());
        existingCustomer.setFirstname(updatedCustomer.getFirstname());
        existingCustomer.setLastname(updatedCustomer.getLastname());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPassword(updatedCustomer.getPassword());
        existingCustomer.setProfilePhoto(updatedCustomer.getProfilePhoto());
        logger.info("substituiu campos: {} {}", updatedCustomer.getFirstname(), existingCustomer.getFirstname());
        return customerRepository.save(existingCustomer);
    }


    public void deleteById(UUID customerId) {
            Optional<Customers> customer = customerRepository.findById(customerId);
            if (customer.isEmpty()) {
                throw new RuntimeException("Cliente não encontrado");
            }
            customerRepository.deleteById(customerId);
        }

    private void verifyCustomer(Customers customer, boolean isNewCustomer) {
        if (customer == null) {
            throw new IllegalArgumentException("Usuário inválido");
        }
        if (customer.getFirstname() == null || customer.getLastname() == null) {
            throw new IllegalArgumentException("Nome e sobrenome são obrigatórios");
        }
        if (customer.getEmail() == null) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        if (customer.getPassword() == null) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
//        if (customer.getAddresses() == null || customer.getAddresses().isEmpty()) {
//            throw new IllegalArgumentException("Pelo menos um endereço é obrigatório");
//        }
        if (customer.getUsername() == null) {
            throw new IllegalArgumentException("Nome de usuário é obrigatório");
        }

        if (isNewCustomer) {
            if (customerRepository.existsByEmail(customer.getEmail())) {
                throw new IllegalArgumentException("O email já está cadastrado");
            }
            if (customerRepository.existsByUsername(customer.getUsername())) {
                throw new IllegalArgumentException("O nome de usuário já está cadastrado");
            }
        }
    }



}
