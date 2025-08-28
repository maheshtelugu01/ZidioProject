package com.nt.dto;

import com.nt.enums.Role;

import lombok.Data;

@Data
public class UserDto {
	private Long uid;
	private String email;
	private String password;
	private Role role;
}
