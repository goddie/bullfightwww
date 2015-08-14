package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 队伍比赛数据
 * @author goddie
 *
 */
@Entity
@Table(name = "db_bullfight_matchdatateam")
public class MatchDataTeam extends BaseUUIDEntity {

	@ManyToOne
	private MatchFight matchFight;
	
	@ManyToOne
	private Team team;
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


	public float getAssist() {
		return assist;
	}


	public float getBlock() {
		return block;
	}


	public MatchFight getMatchFight() {
		return matchFight;
	}


	public float getRebound() {
		return rebound;
	}


	public float getScoring() {
		return scoring;
	}


	public float getSteal() {
		return steal;
	}


	public Team getTeam() {
		return team;
	}


	public float getTurnover() {
		return turnover;
	}


	public void setAssist(float assist) {
		this.assist = assist;
	}


	public void setBlock(float block) {
		this.block = block;
	}


	public void setMatchFight(MatchFight matchFight) {
		this.matchFight = matchFight;
	}


	public void setRebound(float rebound) {
		this.rebound = rebound;
	}


	public void setScoring(float scoring) {
		this.scoring = scoring;
	}


	public void setSteal(float steal) {
		this.steal = steal;
	}


	public void setTeam(Team team) {
		this.team = team;
	}


	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}
}
