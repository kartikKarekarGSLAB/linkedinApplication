package com.gslab.linkedin.linkedindemo.model.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserPostVO extends BeanBase{

	/**
	 * serialVersionUID for UserPostVO
	 */
	private static final long serialVersionUID = -3392675079933368279L;

	private Integer id;

	private String description;

	private String imageAttachment;

	private String authorName;
	
	private int postLikeCounter;
	
	private int commentCounter;
	
	private List<UserCommentVO> postCommentsList;
	
	private Date createdOn;
	
	private Date updatedOn;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
		
	public int getPostLikeCounter() {
		return postLikeCounter;
	}

	public void setPostLikeCounter(int postLikeCounter) {
		this.postLikeCounter = postLikeCounter;
	}

	public int getCommentCounter() {
		return commentCounter;
	}

	public void setCommentCounter(int commentCounter) {
		this.commentCounter = commentCounter;
	}

	public List<UserCommentVO> getPostCommentsList() {
		return postCommentsList;
	}

	public void setPostCommentsList(List<UserCommentVO> postCommentsList) {
		this.postCommentsList = postCommentsList;
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

	public UserPostVO() {
	}

	public UserPostVO(Integer id, String description, String imageAttachment, String authorName, int postLikeCounter,
			int commentCounter, List<UserCommentVO> postCommentsList, Date createdOn, Date updatedOn) {
		super();
		this.id = id;
		this.description = description;
		this.imageAttachment = imageAttachment;
		this.authorName = authorName;
		this.postLikeCounter = postLikeCounter;
		this.commentCounter = commentCounter;
		this.postCommentsList = postCommentsList;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	public UserPostVO(Integer id, String description, String imageAttachment, int postLikeCounter,
			int commentCounter, List<UserCommentVO> postCommentsList, Date createdOn, Date updatedOn) {
		super();
		this.id = id;
		this.description = description;
		this.imageAttachment = imageAttachment;
		this.postLikeCounter = postLikeCounter;
		this.commentCounter = commentCounter;
		this.postCommentsList = postCommentsList;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	
}
