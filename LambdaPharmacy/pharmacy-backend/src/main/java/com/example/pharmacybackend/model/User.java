package com.example.pharmacybackend.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType= DiscriminatorType.STRING)
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name= "email", nullable = false)
	private String email;
	
	@Column(name="username", nullable = false)
	private String username;
   
	@JsonIgnore
    @Column(name = "password", nullable = false)
	private String password;
	
    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "First name has to be provided.")
	private String firstName;
	
    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "Last name has to be provided.")
    private String lastName;
	
    @Column(name= "address", nullable = false)
    private String address;
	
    @Column(name="phone_Number", nullable = false)  
    private String phoneNumber;
    
    
    //za potvrdu mejla nakon registracije
    @Column(name="approved", nullable = false)
    private boolean approved;
	
    
    public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	private List<Authority> authorities;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return  this.authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
