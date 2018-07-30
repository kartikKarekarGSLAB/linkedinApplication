package com.gslab.linkedin.linkedindemo.model.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NewUserVO extends BeanBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6520209764403058172L;

	private Integer id;
	
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private MultipartFile profilePicture;
	
	private String profilePictureURL;
	
	private String email;
	
	private String companyName;
	
	private String designation;

	public NewUserVO() {
	}
	
	
	
	public NewUserVO(String username, String password, MultipartFile profilePicture, String email,
			String companyName, String designation) {
		super();
		this.username = username;
		this.password = password;
		this.profilePicture = profilePicture;
		this.email = email;
		this.companyName = companyName;
		this.designation = designation;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getProfilePictureURL() {
		return profilePictureURL;
	}



	public void setProfilePictureURL(String profilePictureURL) {
		this.profilePictureURL = profilePictureURL;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "NewUserVO [id=" + id + ", username=" + username + ", password=" + password + ", profilePicture="
				+ profilePicture + ", email=" + email + ", companyName=" + companyName + ", designation=" + designation
				+ "]";
	}
	
	
}
