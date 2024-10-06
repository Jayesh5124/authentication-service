package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dao.entity.Role;
import com.example.demo.dao.entity.UserCredentialsEntity;

public class CustomUserDetails implements UserDetails{
	private String name;
	private String password;
	//List<Role> roles;
	
	
	public CustomUserDetails(UserCredentialsEntity user) {
	this.name = user.getName();
	this.password = user.getPassword();
	//this.roles= user.getRoles();
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
 
//    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//     
//    for (Role role : roles) {
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		return null;
    }
     
  //  return authorities;}

	@Override
	public String getPassword() {
	return password;
	}

	@Override
	public String getUsername() {
	return name;
	}
	
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}
	@Override
	public boolean isEnabled()
	{
		return true;
	}

}
