package com.xiaba2.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


/**
 * 会员分类
 * @author goddie
 *
 */
@Entity
@Table(name = "db_cms_member_type")
public class MemberType  extends BaseUUIDEntity{

	/**
	 * 名称
	 */
	@Column
	private String name;
	
	/**
	 * 排序
	 */
	@Column
	private int sortRank;
	
	
	/**
	 * 说明
	 */
	@Column
	private String description;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getSortRank() {
		return sortRank;
	}


	public void setSortRank(int sortRank) {
		this.sortRank = sortRank;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
}
