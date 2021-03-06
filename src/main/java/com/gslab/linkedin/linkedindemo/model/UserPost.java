package com.gslab.linkedin.linkedindemo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "user_post")
public class UserPost {

	@GenericGenerator(name = "userPostSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "userPostSequence"),
			@Parameter(name = "initial_value", value = "8001"), @Parameter(name = "increment_size", value = "1") })
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "userPostSequenceGenerator")
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "image_attachment")
	private String imageAttachment;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_on")
	private Date updatedOn;

	@Column(name = "repost_user_id")
	private Integer repostUserId;

	@Column(name = "repost_post_id")
	private Integer repostPostId;

	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccount userAccount;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userPost")
	private Set<UserComment> userComment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userAccount")
	private Set<UserPostLike> userPostLike;

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

	public Integer getRepostUserId() {
		return repostUserId;
	}

	public void setRepostUserId(Integer repostUserId) {
		this.repostUserId = repostUserId;
	}

	public Integer getRepostPostId() {
		return repostPostId;
	}

	public void setRepostPostId(Integer repostPostId) {
		this.repostPostId = repostPostId;
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

	public Set<UserPostLike> getUserPostLike() {
		return userPostLike;
	}

	public void setUserPostLike(Set<UserPostLike> userPostLike) {
		this.userPostLike = userPostLike;
	}

	public UserPost() {
	}

	public UserPost(String description, String imageAttachment, Date createdOn, Date updatedOn,
			UserAccount userAccount) {
		super();
		this.description = description;
		this.imageAttachment = imageAttachment;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.userAccount = userAccount;
	}

	public UserPost(String description, String imageAttachment, Date updatedOn) {
		super();
		this.description = description;
		this.imageAttachment = imageAttachment;
		this.updatedOn = updatedOn;
	}

}
