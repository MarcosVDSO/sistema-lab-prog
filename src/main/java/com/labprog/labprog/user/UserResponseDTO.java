package com.labprog.labprog.user;

public record UserResponseDTO(Long id, String name) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName());
    }
}
