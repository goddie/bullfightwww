package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 机构信息
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_organization")
public class Organization extends BaseUUIDEntity {

	
	/**
	 * 机构名称
	 */
	@Column
	private String name;

	/**
	 * 机构介绍
	 */
	@Column(length=1000)
	private String info;

	public String getInfo() {
		return info;
	}

	public String getName() {
		return name;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
