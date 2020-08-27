package com.mitrais.chipper.tk.be.profileservice.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.chipper.tk.be.profileservice.config.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenProvider {
	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

	@Autowired
	private JwtConfig jwtConfig;

	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken, Date logoutTime) {
		try {
			Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(authToken).getBody();
			if (logoutTime == null || claims.getIssuedAt().after(logoutTime)) {
				return true;
			}
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}
}
