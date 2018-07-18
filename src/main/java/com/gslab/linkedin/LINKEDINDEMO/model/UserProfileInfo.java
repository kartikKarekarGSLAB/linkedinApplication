package com.gslab.linkedin.LINKEDINDEMO.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="user_profile_info")
public class UserProfileInfo {
	
	@GenericGenerator(
            name = "userProfileSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "userProfileSequence"),
                    @Parameter(name = "initial_value", value = "20001"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )	
	@Id
	@Column(name="id")
	@GeneratedValue(generator="userProfileSequenceGenerator")
	private int id;
	@Column(name="user_account_id")
	private int userAccountId;
	@Column(name="profile_picture")
	private String profilePicture;
	@Column(name="email",length=40)
	private String email;
	@Column(name="company_name",length=30)
	private String companyName;
	@Column(name="designation",length=25)
	private String designation;
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
		return "UserProfileInfo [id=" + id + ", userAccountId=" + userAccountId + ", profilePicture=" + profilePicture
				+ ", email=" + email + ", companyName=" + companyName + ", designation=" + designation + "]";
	}
}
