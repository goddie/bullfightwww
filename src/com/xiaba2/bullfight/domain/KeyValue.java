package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_bullfight_keyvalue")
public class KeyValue extends BaseUUIDEntity {
	/**
	 * 键
	 */
	@Column
	private String dictKey;

	/**
	 * 说明
	 */
	@Column
	private String info;

	/**
	 * 值
	 */
	@Column
	private String dictValue;

	public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	 
}
