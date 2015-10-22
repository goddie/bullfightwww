package com.xiaba2.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 
 * 组
 * @author goddie
 *
 */
@Entity
@Table(name = "db_cms_authgroup")

public class AuthGroup extends BaseUUIDEntity {

	/**
	 * 父级组
	 */
	@ManyToOne
	private AuthGroup authGroup;
	
	
	/**
	 * 名称
	 */
	@Column
	private String name;


	public AuthGroup getAuthGroup() {
		return authGroup;
	}


	public void setAuthGroup(AuthGroup authGroup) {
		this.authGroup = authGroup;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
