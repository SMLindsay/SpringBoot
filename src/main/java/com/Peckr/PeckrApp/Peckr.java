package com.Peckr.PeckrApp;

import java.time.LocalDateTime;

import org.apache.catalina.User;

import jakarta.persistence.*; 

@Entity 
public class Peckr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false) // Username cannot be a null field
	private String userName;
	
	@Column(nullable = false, length = 280) // A peck cannot be null, with maximum length of 280 characters
	private String peck;
	
	@Column(nullable = false)
	private LocalDateTime timestamp; // To store the timestamp when the peck was created, so it can be presented in chronological order
	
	@ManyToOne(fetch = FetchType.LAZY) // Multiple pecks can be linked to one user (ManyToOne) & User entity will not be loaded from the database until it's explicitly accessed (FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User author; // Establish a Many-to-One relationship with a User entity (assuming User entity exists)
	

    
	// Add constructors, getters, setters, and any additional methods you might need.
}
