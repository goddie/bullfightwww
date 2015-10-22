package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;


@Entity
@Table(name = "db_bullfight_feedback")
public class Feedback extends BaseUUIDEntity {

	
	/**
	 * 内容
	 */
	@Column(length=1000)
	private String content;
	
	@ManyToOne
	private User user;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
