package com.example.pharmacybackend.model;

import java.util.List;

//DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenState {
	
private String username;	
private String token;
private Long expiresIn;
private List<String> authorities;

public UserTokenState() {
   this.token = null;
   this.expiresIn = null;
  
}

public UserTokenState(String token, long expiresIn) {
   this.token = token;
   this.expiresIn = expiresIn;
}

public UserTokenState(String token, long expiresIn, List<String> authorities) {
   this.token = token;
   this.expiresIn = expiresIn;
   this.authorities= authorities;
}

public UserTokenState(String username, String token,long expiresIn, List<String> authorities) {
	
	 this.username=username;
	 this.token = token;
	 this.expiresIn= expiresIn;
	 this.authorities= authorities;
	 
}

public UserTokenState(String username, String token, List<String> authorities) {
	 super();
	 this.username=username;
	 this.token = token;
	 
	 this.authorities= authorities;
	 
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}


public String getToken() {
   return token;
}

public void setToken(String token) {
   this.token = token;
}

public Long getExpiresIn() {
   return expiresIn;
}

public void setExpiresIn(Long expiresIn) {
   this.expiresIn = expiresIn;
}
}