package com.zidio.dto;

import com.zidio.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	private String email;
	private String password;
	private Role role;
}
