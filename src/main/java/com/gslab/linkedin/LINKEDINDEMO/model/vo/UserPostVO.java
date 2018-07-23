package com.gslab.linkedin.linkedindemo.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserPostVO extends BeanBase {

	/**
	 * serialVersionUID for UserPostVO
	 */
	private static final long serialVersionUID = -3392675079933368279L;

	private String description;

	private String imageAttachment;

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

}
