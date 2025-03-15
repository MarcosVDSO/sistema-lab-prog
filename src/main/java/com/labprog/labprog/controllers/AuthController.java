package com.labprog.labprog.controllers;


import com.labprog.labprog.DTO.AuthDTO;
import com.labprog.labprog.DTO.UserAuthRegisterDTO;
import com.labprog.labprog.DTO.TokenResponseDTO;
import com.labprog.labprog.model.entities.Users;
import com.labprog.labprog.model.repositories.UserRepository;
import com.labprog.labprog.services.UserService;
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
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokensService tokensService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> loginAdmin(@RequestBody AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokensService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token, "ADMIN"));
    }

    @PostMapping("/register")
    public ResponseEntity registerAdmin(@RequestBody UserAuthRegisterDTO data) {
        if (this.userRepository.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Users newUser = new Users(
                data.getFirstname(),
                data.getLastname(),
                data.getUsername(),
                encryptedPassword,
                data.getCpf(),
                data.getEmail(),
                data.getRole(),
                data.getStatus()
        );
        userService.save(newUser);

        return ResponseEntity.ok().build();
    }

}
