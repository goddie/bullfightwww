package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 系统配置
 * @author goddie
 *
 */
@Entity
@Table(name = "db_bullfight_sysconfig")
public class SysConfig extends BaseUUIDEntity {

	
	/**
	 * 键
	 */
	@Column
	private String key;
	
	/**
	 * 说明
	 */
	@Column
	private String info;
	
	/**
	 * 值
	 */
	@Column
	private String value;

 

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
}
