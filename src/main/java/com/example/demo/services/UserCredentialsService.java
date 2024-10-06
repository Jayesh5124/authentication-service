package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.demo.dao.UserCredentialsDao;
import com.example.demo.dao.entity.UserCredentialsEntity;

@Service
public class UserCredentialsService {
	@Autowired 
	JwtService jwtService;
	
	@Autowired
	UserCredentialsDao authDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
    private UserDetailsService userDetailsService;
	
	
	public UserCredentialsEntity register(UserCredentialsEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return authDao.saveAndFlush(user);
	}
	public String generateToken(String name) {
		// UserDetails userDetails = userDetailsService.loadUserByUsername(name);
		return jwtService.generateToken(name);
	}
	public boolean verifyToken(String token) {
		jwtService.validateToken(token);
		return true;
	}
	public List<UserCredentialsEntity> registerDetails() {
		// TODO Auto-generated method stub
		return authDao.findAll();
	}

}
