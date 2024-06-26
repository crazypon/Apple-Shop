package com.projects.apple_crypto.dtos;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String fullName;
    private String username;
    private String password;
}
