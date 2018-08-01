package com.gslab.linkedin.linkedindemo.model.vo;

public class UserCommentLikeVO extends BeanBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6368774541380114884L;

	private int userAccountId;

	private int userCommentId;

	public int getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}

	public int getUserCommentId() {
		return userCommentId;
	}

	public void setUserCommentId(int userCommentId) {
		this.userCommentId = userCommentId;
	}

	public UserCommentLikeVO(int userAccountId, int userCommentId) {
		super();
		this.userAccountId = userAccountId;
		this.userCommentId = userCommentId;
	}

}
