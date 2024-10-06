package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.entity.UserCredentialsEntity;
import com.example.demo.dto.AuthRequest;
import com.example.demo.services.JwtService;
import com.example.demo.services.UserCredentialsService;

@RestController
@RequestMapping("/api/auth")

public class UserCredentialsController {

	@Autowired
	JwtService jwtService;
	
	@Autowired
	private UserCredentialsService userCredService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public UserCredentialsEntity register(@RequestBody UserCredentialsEntity user) {
		return userCredService.register(user);
	}
	@GetMapping("/registerDetails")
	public List<UserCredentialsEntity> registerDetails(@RequestBody UserCredentialsEntity user) {
		return userCredService.registerDetails();
	}
	
	@GetMapping("/validate/token")
	public boolean validateToken(@RequestParam("token") String token) {
		
		//edit also create one class authrequest
		return userCredService.verifyToken(token);
	}
	
	@PostMapping("/validate/user")
	public String getToken(@RequestBody UserCredentialsEntity authRequest) {
		System.out.println("user : " + authRequest);
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
		System.out.println("authenticated?? : " + authenticate.isAuthenticated());
		if(authenticate.isAuthenticated()) {
			return userCredService.generateToken(authRequest.getName()); 
		}
		return null;
	
	}

}
