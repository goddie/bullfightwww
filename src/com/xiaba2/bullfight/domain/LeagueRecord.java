package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import com.xiaba2.core.BaseUUIDEntity;

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
	 * 得分
	 */
	@Column
	private int score;
	
	/**
	 * 联赛
	 */
	@Column
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
