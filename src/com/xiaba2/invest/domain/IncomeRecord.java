package com.xiaba2.invest.domain;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 
 * 收益记录
 * @author goddie
 *
 */
public class IncomeRecord extends BaseUUIDEntity {

	private User user;
	
	private Product product;
	/**
	 * 收益金额
	 */
	private float Amount;
	
	
	/**
	 * 当前产品年化
	 */
	private float yearRate;
	
	
	/**
	 * 7日年化
	 */
	private float weekRate;
	
	
	/**
	 * 万份收益
	 */
	private float tenThousand;


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public float getAmount() {
		return Amount;
	}


	public void setAmount(float amount) {
		Amount = amount;
	}


	public float getYearRate() {
		return yearRate;
	}


	public void setYearRate(float yearRate) {
		this.yearRate = yearRate;
	}


	public float getWeekRate() {
		return weekRate;
	}


	public void setWeekRate(float weekRate) {
		this.weekRate = weekRate;
	}


	public float getTenThousand() {
		return tenThousand;
	}


	public void setTenThousand(float tenThousand) {
		this.tenThousand = tenThousand;
	}
	
	
	
	
	
}
