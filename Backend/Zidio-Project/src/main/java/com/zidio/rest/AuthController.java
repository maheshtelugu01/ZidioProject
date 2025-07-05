package com.zidio.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zidio.dto.LoginRequest;
import com.zidio.dto.UserDto;
import com.zidio.jwt.JWTUtil;
import com.zidio.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTUtil util;
	
	@PostMapping("/register")
	public ResponseEntity<String>register(@RequestBody UserDto dto){
		return ResponseEntity.ok(authService.register(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<String>login(@RequestBody LoginRequest req){
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(req.getEmail(),req.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(authentication);
		String token=util.generateToken(authentication);
		//return ResponseEntity.ok(authService.login(req));
		return ResponseEntity.ok("user token: "+token);
	}
}
