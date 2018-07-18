package com.gslab.linkedin.LINKEDINDEMO.model;

public class UserVO {
	private String username;
	private String password;
	private String profilePictureUrl;
	private String email;
	private String companyName;
	private String designation;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "UserVO [username=" + username + ", password=" + password + ", profilePictureUrl=" + profilePictureUrl
				+ ", emial=" + email + ", companyName=" + companyName + ", designation=" + designation + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
