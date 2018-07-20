package com.gslab.linkedin.linkedindemo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="user_post")
public class UserPost {
	
	@GenericGenerator(name = "userPostSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "userPostSequence"),
			@Parameter(name = "initial_value", value = "8001"), @Parameter(name = "increment_size", value = "1") })	
	@Id
	@Column(name="id")
	@GeneratedValue(generator="userPostSequenceGenerator")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="image_attachment")
	private String imageAttachment;
	
	@Column(name="created_on")
	private Date createdOn;
	
	@Column(name="updated_on")
	private Date updatedOn;	
	
	@ManyToOne(cascade=CascadeType.ALL)
	private UserAccount userAccount;
		
	@OneToMany(cascade = CascadeType.ALL,mappedBy="userPost")
	private Set<UserComment> userComment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageAttachment() {
		return imageAttachment;
	}

	public void setImageAttachment(String imageAttachment) {
		this.imageAttachment = imageAttachment;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	public Set<UserComment> getUserComment() {
		return userComment;
	}

	public void setUserComment(Set<UserComment> userComment) {
		this.userComment = userComment;
	}
}
