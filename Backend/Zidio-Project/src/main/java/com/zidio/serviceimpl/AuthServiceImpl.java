package com.zidio.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zidio.dto.UserDto;
import com.zidio.entity.User;
import com.zidio.repository.UserRepository;
import com.zidio.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private UserRepository userRepo;
	

	@Override
	public String register(UserDto dto) {
		User user=new User();
		user.setEmail(dto.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
		user.setRole(dto.getRole());
		userRepo.save(user);
		return "User Registered Successfully";
	}
//	public String login(LoginRequest req) {
//		User user=userRepo.findByEmail(req.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
//		if(!new BCryptPasswordEncoder().matches(req.getPassword(),user.getPassword())) {
//			throw new RuntimeException("Invalid Password");
//		}
//		return JWTUtil.generateToken(user.getEmail());
//	}
	
	
}
