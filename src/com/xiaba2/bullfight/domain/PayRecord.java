package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;


@Entity
@Table(name = "db_bullfight_payrecord")

public class PayRecord extends BaseUUIDEntity {

	/**
	 * 场地费
	 */
	@Column
	private float arenaFee;
	
	/**
	 * 裁判费
	 */
	@Column
	private float judgeFee;
	
	/**
	 * 数据员费
	 */
	@Column
	private float dataRecordFee;
	
	/**
	 * 总计
	 */
	@Column
	private float total;

	/**
	 * 优惠幅度
	 */
	@Column
	private float cutOff;

	/**
	 * 优惠百分比
	 */
	@Column
	private float cutPercent;

	/**
	 * 支付方式
	 */
	@Column
	private int payType;
	
	/**
	 * 0 未支付
	 * 1 已支付
	 */
	@Column
	private int status;
	
	@ManyToOne
	private MatchFight matchFight;
	
	@ManyToOne
	private User user;

	@ManyToOne
	private Coupon coupon;

	public float getArenaFee() {
		return arenaFee;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public float getCutOff() {
		return cutOff;
	}

	public float getCutPercent() {
		return cutPercent;
	}

	public float getDataRecordFee() {
		return dataRecordFee;
	}

	public float getJudgeFee() {
		return judgeFee;
	}

	public MatchFight getMatchFight() {
		return matchFight;
	}

	public int getPayType() {
		return payType;
	}

	public int getStatus() {
		return status;
	}

	public float getTotal() {
		return total;
	}

	public User getUser() {
		return user;
	}

	public void setArenaFee(float arenaFee) {
		this.arenaFee = arenaFee;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public void setCutOff(float cutOff) {
		this.cutOff = cutOff;
	}

	public void setCutPercent(float cutPercent) {
		this.cutPercent = cutPercent;
	}

	public void setDataRecordFee(float dataRecordFee) {
		this.dataRecordFee = dataRecordFee;
	}

	public void setJudgeFee(float judgeFee) {
		this.judgeFee = judgeFee;
	}

	public void setMatchFight(MatchFight matchFight) {
		this.matchFight = matchFight;
	}
	
	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
