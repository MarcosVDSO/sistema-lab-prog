//package com.labprog.labprog.services.utils;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PasswordEncryptionService {
//    private final PasswordEncoder passwordEncoder;
//
//    public PasswordEncryptionService() {
//        this.passwordEncoder = new BCryptPasswordEncoder();
//    }
//
//
//    public String encryptPassword(String plainPassword) {
//        return passwordEncoder.encode(plainPassword);
//    }
//
//    public boolean matchesPassword(String plainPassword, String encryptedPassword) {
//        return passwordEncoder.matches(plainPassword, encryptedPassword);
//    }
//}
