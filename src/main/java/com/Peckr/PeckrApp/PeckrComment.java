package com.Peckr.PeckrApp;

import jakarta.persistence.*;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class PeckrComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content; // The content of the comment

    @Column(nullable = false)
    private ZonedDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peck_id", nullable = false)
    private PeckrPeck peck; // The particular peck the comment is associated to

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private PeckrUser author; // The user that created the comment

	public PeckrComment(Long id, String content, ZonedDateTime timestamp, PeckrPeck peck, PeckrUser author) {
		this.id = id;
		this.content = content;
		this.timestamp = timestamp;
		this.peck = peck;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public PeckrPeck getPeck() {
		return peck;
	}

	public void setPeck(PeckrPeck peck) {
		this.peck = peck;
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
