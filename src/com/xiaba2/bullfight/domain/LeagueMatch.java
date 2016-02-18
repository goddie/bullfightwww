package com.xiaba2.bullfight.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_bullfight_leaguematch")
public class LeagueMatch extends BaseUUIDEntity {

	/**
	 * 主队
	 */
	@ManyToOne
	private Team host;
	
	/**
	 * 客队
	 */
	@ManyToOne
	private Team guest;
	
	/**
	 * 开始时间
	 */
	@Column
	private Date start;

	/**
	 * 结束时间
	 */
	@Column
	private Date end;


	/**
	 * 客队分数
	 */
	@Column
	private float guestScore;

	/**
	 * 主队分数
	 */
	@Column
	private float hostScore;
	
	/**
	 * 是否平局
	 */
	@Column
	private int draw;
	
	/**
	 * 胜队
	 */
	@ManyToOne
	private Team winner;
	
	/**
	 * 败队
	 */
	@ManyToOne
	private Team loser;

	public Team getHost() {
		return host;
	}

	public void setHost(Team host) {
		this.host = host;
	}

	public Team getGuest() {
		return guest;
	}

	public void setGuest(Team guest) {
		this.guest = guest;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public float getGuestScore() {
		return guestScore;
	}

	public void setGuestScore(float guestScore) {
		this.guestScore = guestScore;
	}

	public float getHostScore() {
		return hostScore;
	}

	public void setHostScore(float hostScore) {
		this.hostScore = hostScore;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public Team getWinner() {
		return winner;
	}

	public void setWinner(Team winner) {
		this.winner = winner;
	}

	public Team getLoser() {
		return loser;
	}

	public void setLoser(Team loser) {
		this.loser = loser;
	}
	
	
}
