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
	 * 序号
	 */
	@Column
	private int no;
	
	/**
	 * 键
	 */
	@Column
	private String key;
	
	/**
	 * 值
	 */
	@Column
	private String value;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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
