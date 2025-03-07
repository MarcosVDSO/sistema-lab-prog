package com.labprog.labprog.model.entities;

import com.labprog.labprog.DTO.EmployeesDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Employees implements User, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employee_id")
    private UUID employeeId;

    @OneToMany(mappedBy = "employee")
    private List<Addresses> addresses;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    public Employees(EmployeesDTO employeesDTO) {
        this.employeeId = employeesDTO.getEmployeeId();
        this.firstname = employeesDTO.getFirstname();
        this.lastname = employeesDTO.getLastname();
        this.username = employeesDTO.getUsername();
        this.password = employeesDTO.getPassword();
        this.email = employeesDTO.getEmail();
        this.addresses = employeesDTO.getAddresses();
        this.cpf = employeesDTO.getCpf();
    }

    public Employees(String firstname, String lastname, String username, String password, String cpf, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.cpf = cpf;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_EMPLOYEE"),
                new SimpleGrantedAuthority("ROLE_CUSTOMER")
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
