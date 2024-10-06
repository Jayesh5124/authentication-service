package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserCredentialsDao;
import com.example.demo.dao.entity.UserCredentialsEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired 
	UserCredentialsDao userCredentialsDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredentialsEntity> user = userCredentialsDao.findByName(username);
		System.out.println("user : " + user);
		return  user.map(CustomUserDetails::new)
				.orElseThrow(()->new UsernameNotFoundException("Username/password not valid!"));
	}
}



