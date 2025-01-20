package com.labprog.labprog.model.entities;

import com.labprog.labprog.DTO.EmployeesDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employee_id")
    private UUID employeeId;

    @OneToMany(mappedBy = "addressId")
    private List<Addresses> addresses;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_photo", nullable = false)
    private String profilePhoto;

    @Column(name = "email", nullable = false)
    private String email;

    public Employees(EmployeesDTO employeesDTO) {
        this.employeeId = employeesDTO.getEmployeeId();
        this.firstname = employeesDTO.getFirstname();
        this.lastname = employeesDTO.getLastname();
        this.username = employeesDTO.getUsername();
        this.password = employeesDTO.getPassword();
        this.profilePhoto = employeesDTO.getProfilePhoto();
        this.email = employeesDTO.getEmail();
        this.addresses = employeesDTO.getAddresses();

    }
}
