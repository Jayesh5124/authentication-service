package com.example.demo.services;



import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	public void validateToken(String token) {
		Jwts
		.parserBuilder()
		.setSigningKey(getSignKey())
		.build()
		.parseClaimsJws(token);
	}
	public String generateToken(String userName){
		Map<String,Object> claims=new HashMap<>();
		//claims.put("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		return createToken(claims,userName);
	}
	
//	public String generateToken(String userName, Collection<? extends GrantedAuthority> authorities) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("roles", authorities.stream()
//            .map(GrantedAuthority::getAuthority)
//            .collect(Collectors.toList())); // Add roles to claims
//        return createToken(claims, userName);
//    }
	private String createToken(Map<String, Object> claims, String userName) {
	return Jwts
			.builder()
			.setClaims(claims)
			.setSubject(userName)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 *30))
			.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}
	private Key getSignKey() {
		byte[] keyBytes= Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}


}
