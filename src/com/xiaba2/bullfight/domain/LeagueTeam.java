package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_bullfight_leagueteam")
public class LeagueTeam extends BaseUUIDEntity{

	/**
	 * 联赛
	 */
	@ManyToOne
	private League league;
	
	/**
	 * 队伍
	 */
	@ManyToOne
	private Team team;
	
	/**
	 * 排序
	 */
	@Column
	private int orderNum;
	
	/**
	 * 小组
	 */
	@Column
	private int groupNum;
	
	/**
	 * 种子队伍
	 */
	@Column
	private int isSeed;
	
	
	/**
	 * 是否支付
	 */
	@Column
	private int isPay;
	

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public int getIsSeed() {
		return isSeed;
	}

	public void setIsSeed(int isSeed) {
		this.isSeed = isSeed;
	}
	
	
	
	
}
