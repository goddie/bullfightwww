package com.xiaba2.bullfight.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
