package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 交易记录
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_traderecord")
public class TradeRecord extends BaseUUIDEntity {

	
	/**
	 * 记录标题
	 */
	@Column
	private String title;
	
	/**
	 * 资金量
	 */
	@Column
	private float amount;
	
	/**
	 * 操作名称
	 */
	@Column
	private String optName;
	
	/**
	 * 操作状态
	 */
	@Column
	private String optStatus;
	
	/**
	 * 用户
	 */
	@OneToOne
	private User user;
	
	@OneToOne
	private Product product;
	
	/**
	 * 同意购买
	 */
	@Column
	private int agreeBuy;
	
	/**
	 * 公开收益
	 */
	@Column
	private int isShow;
	
	/**
	 * 同步买入
	 */
	@Column
	private int synBuy;
	
	/**
	 * 同步卖出
	 */
	@Column
	private int synSell;
	
	/**
	 * 跟买
	 */
	@ManyToOne
	private User follow;
	
	/**
	 * 操作类型
	 * 1 交易操作
	 * 2 资金操作
	 */
	@Column
	private int type;
	
	

	public int getAgreeBuy() {
		return agreeBuy;
	}

	public float getAmount() {
		return amount;
	}

	public User getFollow() {
		return follow;
	}

	public int getIsShow() {
		return isShow;
	}

	public String getOptName() {
		return optName;
	}

	public String getOptStatus() {
		return optStatus;
	}

	public Product getProduct() {
		return product;
	}

	public int getSynBuy() {
		return synBuy;
	}

	public int getSynSell() {
		return synSell;
	}

	public String getTitle() {
		return title;
	}

	public int getType() {
		return type;
	}

	public User getUser() {
		return user;
	}


	public void setAgreeBuy(int agreeBuy) {
		this.agreeBuy = agreeBuy;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setFollow(User follow) {
		this.follow = follow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public void setOptStatus(String optStatus) {
		this.optStatus = optStatus;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setSynBuy(int synBuy) {
		this.synBuy = synBuy;
	}

	public void setSynSell(int synSell) {
		this.synSell = synSell;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
