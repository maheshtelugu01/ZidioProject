package com.nt.dto;

import com.nt.enums.Role;

import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String email;
	private String password;
	private Role role;
	private boolean active;
}
