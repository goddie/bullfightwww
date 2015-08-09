package com.xiaba2.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 权限
 * @author goddie
 *
 */

@Entity
@Table(name = "db_cms_authres")
public class AuthRes extends BaseUUIDEntity {

	
	/**
	 * 名称
	 */
	@Column
	private String name;
	
	/**
	 * 访问URL
	 */
	@Column
	private String URL;

	public String getName() {
		return name;
	}

	public String getURL() {
		return URL;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
}
