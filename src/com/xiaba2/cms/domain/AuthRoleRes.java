package com.xiaba2.cms.domain;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


/**
 * 角色权限
 * @author goddie
 *
 */
@Table(name = "db_cms_authrole_authres")
public class AuthRoleRes extends BaseUUIDEntity {

	
	/**
	 * 角色
	 */
	@ManyToOne
	private AuthRole authRole;
	
	/**
	 * 权限
	 */
	@ManyToOne
	private AuthRes authRes;

	public AuthRes getAuthRes() {
		return authRes;
	}

	public AuthRole getAuthRole() {
		return authRole;
	}

	public void setAuthRes(AuthRes authRes) {
		this.authRes = authRes;
	}

	public void setAuthRole(AuthRole authRole) {
		this.authRole = authRole;
	}
}
