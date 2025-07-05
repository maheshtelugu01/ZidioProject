package com.zidio.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zidio.entity.User;
import com.zidio.repository.UserRepository;
import com.zidio.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
	}

}
