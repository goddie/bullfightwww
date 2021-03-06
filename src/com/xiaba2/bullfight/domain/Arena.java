package com.xiaba2.bullfight.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;

/**
 * 场馆
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "db_bullfight_arena")
public class Arena extends BaseUUIDEntity {

	@Column
	private String name;

	@Column
	private String address;

	/**
	 * 经度
	 */
	@Column
	private double lon;

	/**
	 * 纬度
	 */
	@Column
	private double lat;
	
	@Column
	private int status;
	
	@ManyToOne
	private User user;
	
	/**
	 * 单价
	 */
	@Column
	private float price;
	
	/**
	 * 1 团队 2野战
	 */
	@Column
	private int matchType;
	
	
	

	public int getMatchType() {
		return matchType;
	}

	public void setMatchType(int matchType) {
		this.matchType = matchType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public String getName() {
		return name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public void setName(String name) {
		this.name = name;
	}

}
