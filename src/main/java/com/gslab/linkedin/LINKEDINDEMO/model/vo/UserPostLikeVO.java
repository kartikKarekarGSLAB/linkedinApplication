package com.gslab.linkedin.linkedindemo.model.vo;

public class UserPostLikeVO extends BeanBase {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6383575708484348838L;

	private int id;
	
	private String likedUserName;
	
	private int likedPostId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLikedUserName() {
		return likedUserName;
	}

	public void setLikedUserName(String likedUserName) {
		this.likedUserName = likedUserName;
	}

	public int getLikedPostId() {
		return likedPostId;
	}

	public void setLikedPostId(int likedPostId) {
		this.likedPostId = likedPostId;
	}
	
	public UserPostLikeVO() {
	}

	public UserPostLikeVO(int id, String likedUserName, int likedPostId) {
		super();
		this.id = id;
		this.likedUserName = likedUserName;
		this.likedPostId = likedPostId;
	}
	
	
}
