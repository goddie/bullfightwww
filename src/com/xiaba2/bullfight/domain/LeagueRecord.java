package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


@Entity
@Table(name = "db_bullfight_leaguerecord")
public class LeagueRecord extends BaseUUIDEntity {

	/**
	 * 队伍
	 */
	@ManyToOne
	private Team team;
	
	/**
	 * 赢场
	 */
	@Column
	private int win;
	
	/**
	 * 输场
	 */
	@Column
	private int lose;
	
	/**
	 * 积分
	 */
	@Column
	private int score;
	
	
	/**
	 * 总得分
	 */
	@Column
	private  int pointSum;
	
	
	/**
	 * 场均得分
	 */
	@Column
	private int pointAvg;
	
	
	/**
	 * 场次
	 */
	@Column
	private int plays;
	
 

	public int getPointSum() {
		return pointSum;
	}

	public void setPointSum(int pointSum) {
		this.pointSum = pointSum;
	}

	public int getPointAvg() {
		return pointAvg;
	}

	public void setPointAvg(int pointAvg) {
		this.pointAvg = pointAvg;
	}

	public int getPlays() {
		return plays;
	}

	public void setPlays(int plays) {
		this.plays = plays;
	}

	/**
	 * 联赛
	 */
	@ManyToOne
	private League league;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
	
	
	
	
	
}
