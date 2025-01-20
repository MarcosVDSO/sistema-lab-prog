package com.labprog.labprog.services;

import com.labprog.labprog.model.entities.Admins;
import com.labprog.labprog.model.entities.Employees;
import com.labprog.labprog.model.repositories.AdminRepository;
import com.labprog.labprog.model.repositories.EmployeesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServices {
    @Autowired
    AdminRepository adminRepository;


    public List<Admins> findAll() {
        return adminRepository.findAll();
    }

    public Optional<Admins> findById(UUID id) {
        return adminRepository.findById(id);
    }

    @Transactional
    public Admins save(Admins admin) {
        verifyCustomer(admin, true);
        return adminRepository.save(admin);
    }

    public Admins update(UUID adminId, Admins admin) {

        Admins updatedAdmin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        verifyCustomer(updatedAdmin, false);
        // Atualizar apenas os campos que realmente mudaram
        if (!updatedAdmin.getEmail().equals(updatedAdmin.getEmail())
                && adminRepository.existsByEmail(updatedAdmin.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        if (!updatedAdmin.getUsername().equals(updatedAdmin.getUsername())
                && adminRepository.existsByUsername(updatedAdmin.getUsername())) {
            throw new IllegalArgumentException("Nome de usuário já está em uso");
        }

        updatedAdmin.setFirstname(admin.getFirstname());
        updatedAdmin.setLastname(admin.getLastname());
        updatedAdmin.setEmail(admin.getEmail());
        updatedAdmin.setPassword(admin.getPassword());
        updatedAdmin.setProfilePhoto(admin.getProfilePhoto());
        return adminRepository.save(updatedAdmin);
        //coment

    }


    public void deleteById(UUID adminId) {
        Optional<Admins> employee = adminRepository.findById(adminId);
        if (employee.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        adminRepository.deleteById(adminId);
    }

    private void verifyCustomer(Admins admin, boolean isNewAdmin) {
        if (admin == null) {
            throw new IllegalArgumentException("Usuário inválido");
        }
        if (admin.getFirstname() == null || admin.getLastname() == null) {
            throw new IllegalArgumentException("Nome e sobrenome são obrigatórios");
        }
        if (admin.getEmail() == null) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        if (admin.getPassword() == null) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
//        if (customer.getAddresses() == null || customer.getAddresses().isEmpty()) {
//            throw new IllegalArgumentException("Pelo menos um endereço é obrigatório");
//        }
        if (admin.getUsername() == null) {
            throw new IllegalArgumentException("Nome de usuário é obrigatório");
        }

        if (isNewAdmin) {
            if (adminRepository.existsByEmail(admin.getEmail())) {
                throw new IllegalArgumentException("O email já está cadastrado");
            }
            if (adminRepository.existsByUsername(admin.getUsername())) {
                throw new IllegalArgumentException("O nome de usuário já está cadastrado");
            }
        }
    }
}
