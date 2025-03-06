package com.labprog.labprog.controllers;


import com.labprog.labprog.DTO.AuthDTO;
import com.labprog.labprog.DTO.AdminAuthRegisterDTO;
import com.labprog.labprog.DTO.TokenResponseDTO;
import com.labprog.labprog.model.entities.Admins;
import com.labprog.labprog.model.repositories.AdminRepository;
import com.labprog.labprog.services.AdminService;
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
    private AdminService adminService;

    @Autowired
    private TokensService tokensService;

    @PostMapping("/admin/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokensService.generateToken((Admins) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token, "ADMIN"));
    }

    @PostMapping("/admin/register")
    public ResponseEntity register(@RequestBody AdminAuthRegisterDTO data) {
        if (this.adminRepository.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Admins newAdmin = new Admins(data.getFirstname(), data.getLastname(), data.getUsername(), encryptedPassword,  data.getCpf(), data.getEmail());
        adminService.save(newAdmin);

        return ResponseEntity.ok().build();
    }

}
