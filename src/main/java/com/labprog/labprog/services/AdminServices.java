package com.labprog.labprog.services;

import com.labprog.labprog.exceptions.*;
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
        verifyAdmin(admin, true);
        return adminRepository.save(admin);
    }

    public Admins update(UUID adminId, Admins admin) {

        Admins updatedAdmin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ObjectNotFoundException("Admin not found"));

        verifyAdmin(updatedAdmin, false);
        // Atualizar apenas os campos que realmente mudaram
        if (!updatedAdmin.getEmail().equals(updatedAdmin.getEmail())
                && adminRepository.existsByEmail(updatedAdmin.getEmail())) {
            throw new DuplicateEmailException();
        }
        if (!updatedAdmin.getUsername().equals(updatedAdmin.getUsername())
                && adminRepository.existsByUsername(updatedAdmin.getUsername())) {
            throw new DuplicateUserNameException();
        }

        updatedAdmin.setFirstname(admin.getFirstname());
        updatedAdmin.setLastname(admin.getLastname());
        updatedAdmin.setEmail(admin.getEmail());
        updatedAdmin.setPassword(admin.getPassword());
        return adminRepository.save(updatedAdmin);

    }


    public void deleteById(UUID adminId) {
        Optional<Admins> employee = adminRepository.findById(adminId);
        if (employee.isEmpty()) {
            throw new ObjectNotFoundException();
        }
        adminRepository.deleteById(adminId);
    }

    private void verifyAdmin(Admins admin, boolean isNewAdmin) {
        if (admin == null) {
            throw new ObjectNotFoundException();
        }
        if (admin.getFirstname() == null || admin.getLastname() == null) {
            throw new InvalidNameException();
        }
        if (admin.getEmail() == null) {
            throw new InvalidEmailException();
        }
        if (admin.getPassword() == null) {
            throw new InvalidPasswordException();
        }
        if (admin.getUsername() == null) {
            throw new InvalidUserNameException();
        }

        if (isNewAdmin) {
            if (adminRepository.existsByEmail(admin.getEmail())) {
                throw new DuplicateEmailException();
            }
            if (adminRepository.existsByUsername(admin.getUsername())) {
                throw new DuplicateUserNameException();
            }
        }
    }
}
