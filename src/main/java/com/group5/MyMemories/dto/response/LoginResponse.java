package com.group5.MyMemories.dto.response;

import lombok.Data;


@Data

public class LoginResponse {
    private Long id;
    private String username;
    private String email;
    private String token;
    private String message;
}
