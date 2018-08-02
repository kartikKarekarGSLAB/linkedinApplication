package com.gslab.linkedin.linkedindemo.model.vo;

import java.util.List;

public class UserCommentVO extends BeanBase {
	/**
	 * Serialization ID UserCommnetVO.
	 */
	private static final long serialVersionUID = -372404890662750311L;
	
	private String message;
	
	private String commentorUserName;
	
	private int commentLikeCounter;
	
	private List<UserCommentLikeVO> userCommentLikeList;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCommentorUserName() {
		return commentorUserName;
	}

	public void setCommentorUserName(String commentorUserName) {
		this.commentorUserName = commentorUserName;
	}
	
	public int getCommentLikeCounter() {
		return commentLikeCounter;
	}

	public void setCommentLikeCounter(int commentLikeCounter) {
		this.commentLikeCounter = commentLikeCounter;
	}

	public List<UserCommentLikeVO> getUserCommentLikeList() {
		return userCommentLikeList;
	}

	public void setUserCommentLikeList(List<UserCommentLikeVO> userCommentLikeList) {
		this.userCommentLikeList = userCommentLikeList;
	}

	public UserCommentVO() {
	}

	public UserCommentVO(String message, String commentorUserName, int commentLikeCounter,
			List<UserCommentLikeVO> userCommentLikeList) {
		super();
		this.message = message;
		this.commentorUserName = commentorUserName;
		this.commentLikeCounter = commentLikeCounter;
		this.userCommentLikeList = userCommentLikeList;
	}

	public UserCommentVO(String message, String commentorUserName) {
		super();
		this.message = message;
		this.commentorUserName = commentorUserName;
	}
	
}
