package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


@Entity
@Table(name = "db_invest_popularapply")
public class PopularApply extends BaseUUIDEntity {

	/**
	 * 用户
	 */
	@ManyToOne
	private User user;
	/**
	 * 说明
	 */
	@Column(length=1000)
	private String content;
	/**
	 * 状态
	 */
	@Column
	private int status;
	
	
	
	public String getContent() {
		return content;
	}
	public int getStatus() {
		return status;
	}
	public User getUser() {
		return user;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
