package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;

/**
 * 球队
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "db_bullfight_team")
public class Team extends BaseUUIDEntity {

	@Column
	private String name;

	@Column(length = 2000)
	private String info;
	
	/**
	 * 城市
	 */
	@Column
	private String city;

	/**
	 * 头像
	 */
	@Column
	private String avatar;

	/**
	 * 管理员
	 */
	@ManyToOne
	private User admin;

	/**
	 * 状态
	 */
	@Column
	private int status;

	/**
	 * 得分
	 */
	@Column
	private float scoring;

	/**
	 * 篮板
	 */
	@Column
	private float rebound;

	/**
	 * 助攻
	 */
	@Column
	private float assist;

	/**
	 * 盖帽
	 */
	@Column
	private float block;

	/**
	 * 抢断
	 */
	@Column
	private float steal;

	/**
	 * 失误
	 */
	@Column
	private float turnover;

	/**
	 * 胜利
	 */
	@Column
	private float win;

	/**
	 * 失败
	 */
	@Column
	private float lose;

	/**
	 * 平局
	 */
	@Column
	private float draw;
 
	/**
	 * 比赛场数
	 */
	@Column
	private float playCount;
 
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public User getAdmin() {
		return admin;
	}

	public float getAssist() {
		return assist;
	}

	public String getAvatar() {
		return avatar;
	}

	public float getBlock() {
		return block;
	}

	public float getDraw() {
		return draw;
	}

	public String getInfo() {
		return info;
	}

	public float getLose() {
		return lose;
	}

	public String getName() {
		return name;
	}

	public float getPlayCount() {
		return playCount;
	}

	public float getRebound() {
		return rebound;
	}

	public float getScoring() {
		return scoring;
	}

	public int getStatus() {
		return status;
	}

	public float getSteal() {
		return steal;
	}

	public float getTurnover() {
		return turnover;
	}

	public float getWin() {
		return win;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public void setAssist(float assist) {
		this.assist = assist;
	}

 

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setBlock(float block) {
		this.block = block;
	}

	public void setDraw(float draw) {
		this.draw = draw;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setLose(float lose) {
		this.lose = lose;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlayCount(float playCount) {
		this.playCount = playCount;
	}

	public void setRebound(float rebound) {
		this.rebound = rebound;
	}

	public void setScoring(float scoring) {
		this.scoring = scoring;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setSteal(float steal) {
		this.steal = steal;
	}

	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}

	public void setWin(float win) {
		this.win = win;
	}

 

}
