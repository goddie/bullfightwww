package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 产品详情
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_productinfo")
public class ProductInfo extends BaseUUIDEntity {

	
	/**
	 * 投资产品
	 */
	@ManyToOne
	private Product product;
	
	/**
	 * 1=购买须知 2=产品详情
	 */
	@Column
	private int type;

	/**
	 * 小标题
	 */
	@Column
	private String subTitle;
	
	/**
	 * 标题内容
	 */
	@Column(length=1000)
	private String subContent;
	
	/**
	 * 排序数字
	 */
	@Column
	private int sortNum;

 

	public Product getProduct() {
		return product;
	}



	public int getSortNum() {
		return sortNum;
	}



	public String getSubContent() {
		return subContent;
	}

 

	public String getSubTitle() {
		return subTitle;
	}

	public int getType() {
		return type;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

 

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
