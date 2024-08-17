package br.edu.ifpb.saac.business.service;

import javax.servlet.http.HttpServletRequest;

import br.edu.ifpb.saac.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;


public interface TokenService {
	
	String generate(User user);
	
	Claims getClaims(String token) throws ExpiredJwtException;
	
	boolean isValid(String token);
	
	String getUserName(String token);
	
	Integer getUserId(String token);
	
	String get(HttpServletRequest request); 
	
}