package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;

/**
 * 队伍比赛数据
 * @author goddie
 *
 */
@Entity
@Table(name = "db_bullfight_matchdatateam")
public class MatchDataTeam extends BaseUUIDEntity {

	/**
	 * 比赛
	 */
	@ManyToOne
	private MatchFight matchFight;
	
	/**
	 * 代表队伍
	 */
	@ManyToOne
	private Team team;

 



	/**
	 * 命中
	 */
	@Column
	private float goal;

	/**
	 * 出手
	 */
	@Column
	private float shot;

	/**
	 * 命中率
	 */
	@Column
	private float goalPercent;

	/**
	 * 三分出手
	 */
	@Column
	private float threeShot;

	/**
	 * 三分命中
	 */
	@Column
	private float threeGoal;

	/**
	 * 三分命中率
	 */
	@Column
	private float threeGoalPercent;

	/**
	 * 罚球
	 */
	@Column
	private float free;

	/**
	 * 罚球命中
	 */
	@Column
	private float freeGoal;

	/**
	 * 罚球命中率
	 */
	@Column
	private float freeGoalPercent;
	
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
	 * 犯规次数
	 */
	@Column
	private float foul;

 
	

	
	
	

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public float getTurnover() {
		return turnover;
	}

	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}

	public float getAssist() {
		return assist;
	}

	public float getBlock() {
		return block;
	}

	public float getFoul() {
		return foul;
	}

	public float getFree() {
		return free;
	}

	public float getFreeGoal() {
		return freeGoal;
	}

	public float getFreeGoalPercent() {
		return freeGoalPercent;
	}

	public float getGoal() {
		return goal;
	}

	public float getGoalPercent() {
		return goalPercent;
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

	public float getShot() {
		return shot;
	}

	public float getSteal() {
		return steal;
	}

	public float getThreeGoal() {
		return threeGoal;
	}

	public float getThreeGoalPercent() {
		return threeGoalPercent;
	}

	public float getThreeShot() {
		return threeShot;
	}


	public void setAssist(float assist) {
		this.assist = assist;
	}

	public void setBlock(float block) {
		this.block = block;
	}

	public void setFoul(float foul) {
		this.foul = foul;
	}

	public void setFree(float free) {
		this.free = free;
	}

	public void setFreeGoal(float freeGoal) {
		this.freeGoal = freeGoal;
	}

	public void setFreeGoalPercent(float freeGoalPercent) {
		this.freeGoalPercent = freeGoalPercent;
	}

	public void setGoal(float goal) {
		this.goal = goal;
	}

	public void setGoalPercent(float goalPercent) {
		this.goalPercent = goalPercent;
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

	public void setShot(float shot) {
		this.shot = shot;
	}

	public void setSteal(float steal) {
		this.steal = steal;
	}

	public void setThreeGoal(float threeGoal) {
		this.threeGoal = threeGoal;
	}

	public void setThreeGoalPercent(float threeGoalPercent) {
		this.threeGoalPercent = threeGoalPercent;
	}

	public void setThreeShot(float threeShot) {
		this.threeShot = threeShot;
	}



}
