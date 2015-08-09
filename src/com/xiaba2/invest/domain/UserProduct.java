package com.xiaba2.invest.domain;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 用户购买的产品
 * @author goddie
 *
 */
public class UserProduct extends BaseUUIDEntity {

	private User uesr;
	
	private Product product;
	
	/**
	 * 是否公开
	 */
	private int isShow;

	/**
	 * 拥有数量
	 */
	private float amount;

	public User getUesr() {
		return uesr;
	}

	public void setUesr(User uesr) {
		this.uesr = uesr;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
}
