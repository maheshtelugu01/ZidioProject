package com.nt.service;

import com.nt.dto.UserDto;
import com.nt.entity.User;

public interface IAuthService {
	public String register(UserDto req);
	public User currentUser();
	
}
