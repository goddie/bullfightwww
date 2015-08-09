package com.xiaba2.cms.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


@Entity
@Table(name = "db_cms_authgroup_authrole")
public class AuthGroupRole extends BaseUUIDEntity {

	@ManyToOne
	private AuthGroup authGroup;
	
	@ManyToOne
	private AuthRole authRole;
	
	public AuthGroup getAuthGroup() {
		return authGroup;
	}
	public void setAuthGroup(AuthGroup authGroup) {
		this.authGroup = authGroup;
	}
	public AuthRole getAuthRole() {
		return authRole;
	}
	public void setAuthRole(AuthRole authRole) {
		this.authRole = authRole;
	}
}
