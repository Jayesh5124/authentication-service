package com.example.demo.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.example.demo.services.CustomUserDetailsService;

@Configuration

public class AuthConfig {
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//		.csrf(csrf -> csrf.disable())
//		.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/register","/api/auth/registerDetails","/api/auth/validate/token","/api/auth/validate/user")
//				.permitAll()).build();
	
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
										.anyRequest()
										.permitAll())
		.headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()));
	return http.build();
            // Allow iframe access for H2 console
//            ).authorizeHttpRequests(auth -> auth
//				 .requestMatchers("/swagger-ui/**","/h2-console/**").permitAll()
//	               // .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
//	                .requestMatchers("/api/departments/**").hasAnyAuthority("ADMIN", "USER")
//	                .requestMatchers("/api/employees/**").hasAnyAuthority("ADMIN")
//	                .requestMatchers("/api/auth/**").hasAuthority("ADMIN")
//	                
//	               .anyRequest().authenticated()
//	            )
//            .sessionManagement(session -> session
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Set stateless session management
//                )
//	            .formLogin(login -> login.permitAll())
//	            .logout(logout -> logout.permitAll())
//	            .exceptionHandling(eh -> eh.accessDeniedPage("/403"))
//	            ;
		       
		  
	        //return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}
