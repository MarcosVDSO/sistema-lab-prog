package com.labprog.labprog.controllers;


import com.labprog.labprog.DTO.AuthDTO;
import com.labprog.labprog.DTO.AdminAuthRegisterDTO;
import com.labprog.labprog.DTO.TokenResponseDTO;
import com.labprog.labprog.model.entities.Admins;
import com.labprog.labprog.model.entities.Customers;
import com.labprog.labprog.model.entities.Employees;
import com.labprog.labprog.model.repositories.AdminRepository;
import com.labprog.labprog.model.repositories.CustomerRepository;
import com.labprog.labprog.model.repositories.EmployeesRepository;
import com.labprog.labprog.services.AdminService;
import com.labprog.labprog.services.CustomersService;
import com.labprog.labprog.services.EmployeesService;
import com.labprog.labprog.services.TokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private CustomersService customersService;

    @Autowired
    private TokensService tokensService;

    @PostMapping("/admin/login")
    public ResponseEntity<TokenResponseDTO> loginAdmin(@RequestBody AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokensService.generateToken((Admins) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token, "ADMIN"));
    }

    @PostMapping("/employee/login")
    public ResponseEntity<TokenResponseDTO> loginEmployee(@RequestBody AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokensService.generateToken((Employees) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token, "EMPLOYEE"));
    }

    @PostMapping("/customer/login")
    public ResponseEntity<TokenResponseDTO> loginCustomer(@RequestBody AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokensService.generateToken((Customers) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token, "CUSTOMER"));
    }

    @PostMapping("/admin/register")
    public ResponseEntity registerAdmin(@RequestBody AdminAuthRegisterDTO data) {
        if (this.adminRepository.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Admins newAdmin = new Admins(data.getFirstname(), data.getLastname(), data.getUsername(), encryptedPassword,  data.getCpf(), data.getEmail());
        adminService.save(newAdmin);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/employee/register")
    public ResponseEntity registerEmployee(@RequestBody AdminAuthRegisterDTO data) {
        if (this.employeesRepository.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Employees newEmployee = new Employees(data.getFirstname(), data.getLastname(), data.getUsername(), encryptedPassword,  data.getCpf(), data.getEmail());
        employeesService.save(newEmployee);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer/register")
    public ResponseEntity registerCustomer(@RequestBody AdminAuthRegisterDTO data) {
        if (this.customerRepository.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Customers newCustomer = new Customers(data.getFirstname(), data.getLastname(), data.getUsername(), encryptedPassword,  data.getCpf(), data.getEmail());
        customersService.save(newCustomer);

        return ResponseEntity.ok().build();
    }

}
