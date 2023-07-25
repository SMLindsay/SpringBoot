package com.Peckr.PeckrApp;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.*;

@Entity
public class PeckrUser {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column
    private String profilePictureUrl;
    
    @Column(length = 200)
    private String bio;
    
    @Column
    private LocalDate dateOfBirth;
    
    @Column
    private int followersCount;
    
    @Column
    private int followingCount;
    
    @Column(nullable = false)
    private ZonedDateTime registrationDate;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PeckrPeck> pecks; // Multiple pecks can be linked to one user (OneToMany)
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PeckrComment> comments; // Multiple comments can be linked to one user (OneToMany)
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PeckrPostReaction> postReactions; // Multiple reactions can be linked to one user (OneToMany)
    
    @ManyToMany // A Peckr user can follow and be followed by many other Peckr users
    @JoinTable( // The relationship is managed through a join table which contains references to associate followers with those they are following
        name = "peckr_user_followers",
        joinColumns = @JoinColumn(name = "follower_id"),
        inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<PeckrUser> following; // Each user can only follow another particular user once
    
    @ManyToMany(mappedBy = "following")
    private Set<PeckrUser> followers; // Each user can only be a follower once for a particular user

	public PeckrUser(Long id, String username, String email, String password, String profilePictureUrl, String bio,
			LocalDate dateOfBirth, int followersCount, int followingCount, ZonedDateTime registrationDate,
			List<PeckrPeck> pecks, List<PeckrComment> comments, List<PeckrPostReaction> postReactions,
			Set<PeckrUser> following, Set<PeckrUser> followers) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.profilePictureUrl = profilePictureUrl;
		this.bio = bio;
		this.dateOfBirth = dateOfBirth;
		this.followersCount = followersCount;
		this.followingCount = followingCount;
		this.registrationDate = registrationDate;
		this.pecks = pecks;
		this.comments = comments;
		this.postReactions = postReactions;
		this.following = following;
		this.followers = followers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}

	public ZonedDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(ZonedDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<PeckrPeck> getPecks() {
		return pecks;
	}

	public void setPecks(List<PeckrPeck> pecks) {
		this.pecks = pecks;
	}

	public List<PeckrComment> getComments() {
		return comments;
	}

	public void setComments(List<PeckrComment> comments) {
		this.comments = comments;
	}

	public List<PeckrPostReaction> getPostReactions() {
		return postReactions;
	}

	public void setPostReactions(List<PeckrPostReaction> postReactions) {
		this.postReactions = postReactions;
	}

	public Set<PeckrUser> getFollowing() {
		return following;
	}

	public void setFollowing(Set<PeckrUser> following) {
		this.following = following;
	}

	public Set<PeckrUser> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<PeckrUser> followers) {
		this.followers = followers;
	}

	@Override
	public int hashCode() { // A hash code is a unique number based on the attributes below. If PeckrUsers have the same bio or peck, the hash code will be the same 
		return Objects.hash(bio, comments, dateOfBirth, email, followers, followersCount, following, followingCount, id,
				password, pecks, postReactions, profilePictureUrl, registrationDate, username);
	}

	@Override
	public boolean equals(Object obj) { // Even if the hash code is the same, as long as the equals method returns false, it is not classed as the same PeckrUser
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeckrUser other = (PeckrUser) obj;
		return Objects.equals(bio, other.bio) && Objects.equals(comments, other.comments)
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email, other.email)
				&& Objects.equals(followers, other.followers) && followersCount == other.followersCount
				&& Objects.equals(following, other.following) && followingCount == other.followingCount
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(pecks, other.pecks) && Objects.equals(postReactions, other.postReactions)
				&& Objects.equals(profilePictureUrl, other.profilePictureUrl)
				&& Objects.equals(registrationDate, other.registrationDate) && Objects.equals(username, other.username);
	}
	

    
}
