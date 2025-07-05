package com.zidio.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JWTUtil {
	public static String generateToken(Authentication authentication) {
	
				String token=Jwts.builder()
				.setSubject(authentication.getName())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(SignatureAlgorithm.HS256,"Mahesh123")
				.compact();
		return token;
	}
	 
	public String getEmailFromToken(String token) {
		Claims claims=Jwts.parser().setSigningKey("Mahesh123").build()
				.parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey("Mahesh123").build()
			.parseClaimsJws(token);
			return true;
		}
		catch(Exception e) {
			throw new RuntimeException("token expired...");
		}
	}
	
}
