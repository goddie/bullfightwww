package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.User;
import com.xiaba2.core.BaseUUIDEntity;


/**
 * 比赛评论
 * @author goddie
 *
 */
@Entity
@Table(name = "db_bullfight_commet")
public class Commet extends BaseUUIDEntity {
	@ManyToOne
	private MatchFight matchFight;
	
	@Column(length=1000)
	private String content;
	
	@Column
	private String title;
	
	@ManyToOne
	private User from;
	
	@ManyToOne
	private User reply;
	
	@Column
	private String pic1;
	
	@Column
	private String pic2;
	
	@Column
	private String pic3;
	
	@Column
	private String pic4;
	
	@Column
	private String pic5;
	
	@Column
	private String pic6;

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

	public User getReply() {
		return reply;
	}

	public void setReply(User reply) {
		this.reply = reply;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getPic4() {
		return pic4;
	}

	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}

	public String getPic5() {
		return pic5;
	}

	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}

	public String getPic6() {
		return pic6;
	}

	public void setPic6(String pic6) {
		this.pic6 = pic6;
	}

	
	
}
