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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://system-lab-prog-front-q1oo.vercel.app", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokensService tokensService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> loginUser(@RequestBody AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokensService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserAuthRegisterDTO data) {
//        if (this.userService.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        Users newUser = Users.builder()
                .firstname(data.getFirstname())
                .lastname(data.getLastname())
                .username(data.getUsername())
                .password(encryptedPassword)
                .cpf(data.getCpf())
                .email(data.getEmail())
                .role(data.getRole())
                .status("ENABLED")
                .build();

        userService.save(newUser);

        return ResponseEntity.ok().build();
    }

}
