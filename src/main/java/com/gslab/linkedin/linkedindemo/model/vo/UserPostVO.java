package com.gslab.linkedin.linkedindemo.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserPostVO extends BeanBase {

	/**
	 * serialVersionUID for UserPostVO
	 */
	private static final long serialVersionUID = -3392675079933368279L;

	private Integer id;

	private String description;

	private String imageAttachment;

	private String authorName;

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

	public UserPostVO() {
	}

	public UserPostVO(Integer id, String description, String imageAttachment) {
		super();
		this.id = id;
		this.description = description;
		this.imageAttachment = imageAttachment;
	}

	public UserPostVO(Integer id, String description, String imageAttachment, String authorName) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.description = description;
		this.imageAttachment = imageAttachment;
		this.authorName = authorName;
	}

}
