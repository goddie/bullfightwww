package com.xiaba2.cms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 
 * 会员
 * 
 * @author goddie
 *
 */
@Entity
@Table(name = "db_cms_member")
public class Member extends BaseUUIDEntity {

	/**
	 * 会员类型
	 */
	@ManyToOne
	private MemberType memberType;

	/**
	 * 注册用户名
	 */
	@Column
	private String username;

	/**
	 * 用户密码
	 */
	@Column
	private String password;

	/**
	 * 昵称
	 */
	@Column
	private String nickname;

	/**
	 * 性别
	 */
	@Column
	private String sex;

	/**
	 * 会员级别值
	 */
	@Column
	private int rank;

	/**
	 * 更新时间
	 */
	@Column
	private Date upTime;

	/**
	 * 过期时间
	 */
	@Column
	private Date expTime;

	/**
	 * 会员金币
	 */
	@Column
	private int money;

	/**
	 * 邮箱
	 */
	@Column
	private String email;

	/**
	 * 积分
	 */
	@Column
	private int scores;

	/**
	 * 推荐
	 */
	@Column
	private String matt;

	/**
	 * 会员空间状况
	 */
	@Column
	private int spaceStatus;

	/**
	 * 头像
	 */
	@Column
	private String face;

	/**
	 * 安全提示问题
	 */
	@Column
	private String safeQuestion;

	/**
	 * 安全问题答案
	 */
	@Column
	private String safeAnswer;

	/**
	 * 注册时间
	 */
	@Column
	private Date regTime;

	/**
	 * 注册 IP
	 */
	@Column
	private String regIp;

	/**
	 * 登录时间
	 */
	@Column
	private Date loginTime;

	/**
	 * 登录 IP
	 */
	@Column
	private String loginIp;

	public String getEmail() {
		return email;
	}

	public Date getExpTime() {
		return expTime;
	}

	public String getFace() {
		return face;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public String getMatt() {
		return matt;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public int getMoney() {
		return money;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public int getRank() {
		return rank;
	}

	public String getRegIp() {
		return regIp;
	}

	public Date getRegTime() {
		return regTime;
	}

	public String getSafeAnswer() {
		return safeAnswer;
	}

	public String getSafeQuestion() {
		return safeQuestion;
	}

	public int getScores() {
		return scores;
	}

	public String getSex() {
		return sex;
	}

	public int getSpaceStatus() {
		return spaceStatus;
	}

	public Date getUpTime() {
		return upTime;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public void setMatt(String matt) {
		this.matt = matt;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public void setSafeAnswer(String safeAnswer) {
		this.safeAnswer = safeAnswer;
	}

	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSpaceStatus(int spaceStatus) {
		this.spaceStatus = spaceStatus;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
