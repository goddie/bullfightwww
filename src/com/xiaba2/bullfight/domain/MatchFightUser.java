package com.xiaba2.bullfight.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;

/**
 * 参加比赛的人员
 * @author goddie
 *
 */
@Entity
@Table(name = "db_bullfight_matchfightuser")
public class MatchFightUser extends BaseUUIDEntity {

	/**
	 * 玩家
	 */
	@ManyToOne
	private User user;
	
	
	/**
	 * 比赛
	 */
	@ManyToOne
	private MatchFight matchFight;
	
	
	/**
	 * 玩家代表队伍
	 */
	@ManyToOne
	private Team team;


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public MatchFight getMatchFight() {
		return matchFight;
	}


	public void setMatchFight(MatchFight matchFight) {
		this.matchFight = matchFight;
	}


	public Team getTeam() {
		return team;
	}


	public void setTeam(Team team) {
		this.team = team;
	}
	
	
	
	
}
