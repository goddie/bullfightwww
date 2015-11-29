package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;


@Entity
@Table(name = "db_bullfight_message")
public class Message extends BaseUUIDEntity {

	@ManyToOne
	private MatchFight matchFight;
	
	@Column(length=1000)
	private String content;
	
	@Column
	private String title;
	
	@ManyToOne
	private User from;
	
	@ManyToOne
	private User sendTo;
	
	/**
	 * 0 未读
	 * 1 已读
	 */
	@Column
	private int status;
	
	/**
	 * 消息类型 
	 * 1 通知队长
	 * 2 邀请入会
	 * 3 新闻回复
	 * 4 约战回复
	 * 5 约战评论
	 */
	@Column
	private int type;
	
	/**
	 * 邀请加入的队伍
	 */
	@ManyToOne
	private Team team;
	
	/**
	 * 是否推送
	 */
	@Column
	private int isPush;
	
	/**
	 * 评论
	 */
	@ManyToOne
	private Commet commet;
	
	
	
 
	public int getIsPush() {
		return isPush;
	}
	public void setIsPush(int isPush) {
		this.isPush = isPush;
	}
	public Commet getCommet() {
		return commet;
	}
	public void setCommet(Commet commet) {
		this.commet = commet;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public User getSendTo() {
		return sendTo;
	}
	public void setSendTo(User sendTo) {
		this.sendTo = sendTo;
	}
	public MatchFight getMatchFight() {
		return matchFight;
	}
	public void setMatchFight(MatchFight matchFight) {
		this.matchFight = matchFight;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
