package com.xiaba2.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 文章分类
 * @author goddie
 *
 */
@Entity
@Table(name = "db_cms_article_type")
public class ArticleType extends BaseUUIDEntity {

	
	/**
	 * 父级分类
	 */
	@ManyToOne
	private ArticleType parent;
	
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

	

	public ArticleType getParent() {
		return parent;
	}


	public void setParent(ArticleType parent) {
		this.parent = parent;
	}


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
