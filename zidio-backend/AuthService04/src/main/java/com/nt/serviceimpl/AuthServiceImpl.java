package com.nt.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.dto.UserDto;
import com.nt.entity.User;
import com.nt.repository.UserRepository;
import com.nt.service.IAuthService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String register(UserDto dto) {
		log.info("register(-)-->AuthService");
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setRole(dto.getRole());
		userRepo.save(user);
		return "User Registered Successfully";
	}
	@Override
	public User currentUser() {
		log.info("currentUser(-)-->AuthService");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userRepo.findByEmail(userDetails.getUsername()).get();
		return user;
	}
	

}
