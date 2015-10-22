package com.xiaba2.cms.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_cms_authgroup_member")
public class AuthGroupMember extends BaseUUIDEntity {

	@ManyToOne
	private AuthGroup authGroup;
	
	@ManyToOne
	private Member member;
	
	public AuthGroup getAuthGroup() {
		return authGroup;
	}
	public Member getMember() {
		return member;
	}
	public void setAuthGroup(AuthGroup authGroup) {
		this.authGroup = authGroup;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}
