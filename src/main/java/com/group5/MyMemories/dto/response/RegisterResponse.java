package com.group5.MyMemories.dto.response;

import lombok.Data;

@Data
public class RegisterResponse {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String token;


	public RegisterResponse() {
		// TODO Auto-generated constructor stub
	}
}
