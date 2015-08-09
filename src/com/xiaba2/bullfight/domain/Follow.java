package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;

/**
 * 关注
 * @author Administrator
 *
 */
@Entity
@Table(name = "db_bullfight_follow")
public class Follow extends BaseUUIDEntity {

	@ManyToOne
	private User from;
	
	@ManyToOne
	private User sendTo;
	
	@Column
	private int status;
	
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getSendTo() {
		return sendTo;
	}
	public void setSendTo(User sendTo) {
		this.sendTo = sendTo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
