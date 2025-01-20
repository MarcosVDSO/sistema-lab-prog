package com.labprog.labprog.model.entities;

import com.labprog.labprog.DTO.AdminDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Admins")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "admin_id")
    private UUID adminId;

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

    public Admins(AdminDTO adminDTO) {
        this.adminId = adminDTO.getAdminId();
        this.firstname = adminDTO.getFirstname();
        this.lastname = adminDTO.getLastname();
        this.username = adminDTO.getUsername();
        this.password = adminDTO.getPassword();
        this.profilePhoto = adminDTO.getProfilePhoto();
        this.email = adminDTO.getEmail();
        this.addresses = adminDTO.getAddresses();
    }
}
