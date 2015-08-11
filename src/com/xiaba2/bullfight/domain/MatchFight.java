package com.xiaba2.bullfight.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_bullfight_matchfight")
public class MatchFight extends BaseUUIDEntity {

	/**
	 * 1 团队 2野战
	 */
	@Column
	private int matchType;

	/**
	 * 1 未开始 0待应战 2已结束
	 */
	@Column
	private int status;

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

	@Column
	private String weather;

	@ManyToOne
	private Team guest;

	@ManyToOne
	private Team host;

	@Column
	private int guestScore;

	@Column
	private int hostScore;

	@Column
	private int guestMember;

	@Column
	private int hostMember;
	
	

	/**
	 * 1 1on1
	 * 2 2on2
	 * 3 3on3
	 * 4 4on4
	 * 5 5on5
	 */
	@Column
	private int teamSize;

	@ManyToOne
	private Arena arena;

	
	/**
	 * 裁判人数
	 */
	@Column
	private int judge;

	
	/**
	 * 数据员人数
	 */
	@Column
	private int dataRecord;
	
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
	
	/**
	 * 是否支付
	 */
	@Column
	private int isPay;

	
	
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


	public int getIsPay() {
		return isPay;
	}


	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}


	public Arena getArena() {
		return arena;
	}

	
	public int getDataRecord() {
		return dataRecord;
	}

	public Date getEnd() {
		return end;
	}

	
	public Team getGuest() {
		return guest;
	}
	
	public int getGuestMember() {
		return guestMember;
	}
	
	public int getGuestScore() {
		return guestScore;
	}
	
	public Team getHost() {
		return host;
	}
	
	public int getHostMember() {
		return hostMember;
	}
	
	

	public int getHostScore() {
		return hostScore;
	}

	public int getJudge() {
		return judge;
	}

	public int getMatchType() {
		return matchType;
	}

	public Date getStart() {
		return start;
	}

	public int getStatus() {
		return status;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public String getWeather() {
		return weather;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public void setDataRecord(int dataRecord) {
		this.dataRecord = dataRecord;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setGuest(Team guest) {
		this.guest = guest;
	}

	public void setGuestMember(int guestMember) {
		this.guestMember = guestMember;
	}

	public void setGuestScore(int guestScore) {
		this.guestScore = guestScore;
	}

	public void setHost(Team host) {
		this.host = host;
	}

	public void setHostMember(int hostMember) {
		this.hostMember = hostMember;
	}

	public void setHostScore(int hostScore) {
		this.hostScore = hostScore;
	}

	public void setJudge(int judge) {
		this.judge = judge;
	}

	public void setMatchType(int matchType) {
		this.matchType = matchType;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

}
