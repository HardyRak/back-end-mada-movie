package com.spring.hard.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    private String token;

    private long expiresIn;
    
}
