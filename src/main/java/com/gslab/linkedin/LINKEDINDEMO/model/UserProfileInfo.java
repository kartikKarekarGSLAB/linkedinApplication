package com.gslab.linkedin.LINKEDINDEMO.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="user_profile_info")
public class UserProfileInfo {
	
	@Id
	@Column(name="user_account_id")
	@GeneratedValue(generator="gen")
	@GenericGenerator(name = "gen", strategy = "foreign", 
	parameters = { @Parameter(name = "property", value = "userAccount") })	
	private int userAccountId;
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	@Column(name="profile_picture")
	private String profilePicture;
	@Column(name="email",length=40)
	private String email;
	@Column(name="company_name",length=30)
	private String companyName;
	@Column(name="designation",length=25)
	private String designation;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "user_account_id")
	private UserAccount userAccount;
	public int getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
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
		return "UserProfileInfo [ userAccountId=" + userAccountId + ", profilePicture=" + profilePicture
				+ ", email=" + email + ", companyName=" + companyName + ", designation=" + designation + "]";
	}
}
