package com.xiaba2.cms.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


@Entity
@Table(name = "db_cms_authrole_member")
public class AuthRoleMember extends BaseUUIDEntity {

	/**
	 * 用户
	 */
	@ManyToOne
	private Member member;
	
	/**
	 * 角色
	 */
	@ManyToOne
	private AuthRole authRole;

	public AuthRole getAuthRole() {
		return authRole;
	}

	public Member getMember() {
		return member;
	}

	public void setAuthRole(AuthRole authRole) {
		this.authRole = authRole;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
	
}
