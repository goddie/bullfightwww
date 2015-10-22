package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_bullfight_coupon")
public class Coupon extends BaseUUIDEntity {

	/**
	 * 优惠额度
	 */
	@Column
	private float cutOff;
	
	/**
	 * 优惠百分比
	 */
	@Column
	private float cutPercent;
	
	
	/**
	 * 使用状态
	 */
	@Column
	private int status;
	
	
	/**
	 * 编码
	 */
	@Column
	private String code;
	
	/**
	 * 使用人
	 */
	@ManyToOne
	private User user;

	public float getCutOff() {
		return cutOff;
	}

	public void setCutOff(float cutOff) {
		this.cutOff = cutOff;
	}

	public float getCutPercent() {
		return cutPercent;
	}

	public void setCutPercent(float cutPercent) {
		this.cutPercent = cutPercent;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
