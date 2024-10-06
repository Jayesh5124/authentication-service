package com.example.demo.dto;

import java.util.List;

import com.example.demo.dao.entity.Role;
import com.example.demo.dao.entity.UserCredentialsEntity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class AuthRequest {
	private String username;
	private String password;

}
