package com.nt.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nt.jwt.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JWTUtil util;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token=getToken(request);
		if(StringUtils.hasText(token)&& util.validateToken(token)) {
			String email=util.getEmailFromToken(token);
			UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken authentication=
					new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
		}
		filterChain.doFilter(request, response);
	}
	private String getToken(HttpServletRequest req) {
		String token=req.getHeader("Authorization");
		if(StringUtils.hasText(token)&&token.startsWith("Bearer ")) {
			return token.substring(7,token.length());
		}
		return null;
	}

}
