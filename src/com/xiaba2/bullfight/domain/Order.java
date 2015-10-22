package com.xiaba2.bullfight.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;

 
@Entity
@Table(name = "db_bullfight_order")
public class Order extends BaseUUIDEntity{

	/**
	 * 用户
	 */
	@ManyToOne
	private User user;
	
	/**
	 * 订单号
	 */
	@Column
	private String tradeNo;
	
	
	/**
	 * 比赛
	 */
	@ManyToOne
	private MatchFight matchFight;
	
	
	/**
	 * 支付状态
	 */
	@Column
	private int status;
	
	
	/**
	 * 产品名
	 */
	@Column
	private String name;
	
	
	/**
	 * 数量
	 */
	@Column
	private float count;
	
	/**
	 * 总金额
	 */
	@Column
	private float total;
	
	/**
	 * 单价
	 */
	@Column
	private float price;
	
	/**
	 * 支付方式
	 */
	@Column
	private int payType;
	
	/**
	 * 支付时间
	 */
	@Column
	private Date payDate;
	
	
	/**
	 * 支付宝支付通知
	 */
	@Column
	private String alipayServer;
	
	/**
	 * 说明备注
	 */
	@Column
	private String info;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public MatchFight getMatchFight() {
		return matchFight;
	}

	public void setMatchFight(MatchFight matchFight) {
		this.matchFight = matchFight;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCount() {
		return count;
	}

	public void setCount(float count) {
		this.count = count;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getAlipayServer() {
		return alipayServer;
	}

	public void setAlipayServer(String alipayServer) {
		this.alipayServer = alipayServer;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
}
