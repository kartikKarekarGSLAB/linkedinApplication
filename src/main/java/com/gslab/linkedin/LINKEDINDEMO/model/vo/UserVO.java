package com.gslab.linkedin.linkedindemo.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserVO extends BeanBase {

	/**
	 * serialVersionUID for UserVO
	 */
	private static final long serialVersionUID = 7489083255526010122L;
	private Integer id;
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String profilePictureUrl;
	private String email;
	private String companyName;
	private String designation;

	public UserVO() {
	}

	public UserVO(Integer id, String username, String password, String profilePictureUrl, String email,
			String companyName, String designation) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.profilePictureUrl = profilePictureUrl;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", username=" + username + ", password=" + password + ", profilePictureUrl="
				+ profilePictureUrl + ", email=" + email + ", companyName=" + companyName + ", designation="
				+ designation + "]";
	}

}
