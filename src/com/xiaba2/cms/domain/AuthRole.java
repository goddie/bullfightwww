package com.xiaba2.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 
 * 角色
 * @author goddie
 *
 */

@Entity
@Table(name = "db_cms_authrole")
public class AuthRole extends BaseUUIDEntity {

	
	/**
	 * 名称
	 */
	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
