package com.Peckr.PeckrApp;

import java.time.Duration;
//import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*; 

@Entity 
public class PeckrPeck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false) // Username cannot be a null field
	private String username;
	
	@Column(nullable = false, length = 280) // A peck cannot be null, with maximum length of 280 characters
	private String peck;
	
	@Column(nullable = false)
	@CreationTimestamp 
	private ZonedDateTime timestamp; // To store the timestamp (with timezone) of when the peck was created, so it can be presented in chronological order
	
	@ManyToOne(fetch = FetchType.LAZY) // Multiple pecks can be linked to one user (ManyToOne) & User entity will not be loaded from the database until it's explicitly accessed (FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private PeckrUser author; // Establish a Many-to-One relationship with a User entity (assuming User entity exists)

	public PeckrPeck(int id, String userName, String peck, ZonedDateTime timestamp, PeckrUser author) {
		this.id = id;
		this.username = userName;
		this.peck = peck;
		this.timestamp = timestamp;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPeck() {
		return peck;
	}

	public void setPeck(String peck) {
		this.peck = peck;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public PeckrUser getAuthor() {
		return author;
	}

	public void setAuthor(PeckrUser author) {
		this.author = author;
	}
	
	public String getFormattedTimestamp() {
        ZonedDateTime now = ZonedDateTime.now();
        Duration duration = Duration.between(timestamp, now); // The difference between the time the peck was made and now

        if (duration.toMinutes() < 1) {
            return "Just now"; // If the difference is less than 1 minute
        } else if (duration.toMinutes() < 60) {
            return duration.toMinutes() + " minutes ago"; // If the difference is less than 1 hour
        } else if (duration.toHours() < 24) {
            return duration.toHours() + " hours ago"; // If the difference is less than 1 day
        } else if (duration.toDays() < 30) {
            return duration.toDays() + " days ago"; // If the difference is less than 1 month
        } else {
            // For comments older than a month, the full timestamp will be shown
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
            return timestamp.format(formatter);
        }
	}
	
	
}