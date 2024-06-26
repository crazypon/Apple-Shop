package com.projects.apple_crypto.dtos;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Long expiration;
}
