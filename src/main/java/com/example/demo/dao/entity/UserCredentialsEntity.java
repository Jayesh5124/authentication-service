package com.example.demo.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity

public class UserCredentialsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cred_id")
	private int id;
	private String name;
	private String email;
	private String password;

//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "cred_roles",
//            joinColumns = @JoinColumn(name = "cred_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//            )
   // private List<Role> roles ;
	
	

}
