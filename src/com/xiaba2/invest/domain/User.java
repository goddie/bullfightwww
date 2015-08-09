package com.xiaba2.invest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.BaseUUIDEntity;

/**
 * 用户
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_user")
public class User extends BaseUUIDEntity 
{
	@Column
	private String username;
	
	
	@Column
	private String password;

	@Column
	private String email;
	
	@Column
	private String regIp;


	/**
	 * 成员
	 */
	@ManyToOne
	private Member member;


	/**
	 * 昵称
	 */
	@Column
	private String nickname;


	/**
	 * 等级
	 */
	@Column
	private int level;


	/**
	 * 总财产
	 */
	@Column
	private float wealth;
	
	
	/**
	 * 猫币
	 */
	@Column
	private float score;

	/**
	 * 累计收益率
	 */
	@Column
	private float totalRate;

	/**
	 * 年收益率
	 */
	@Column
	private float yearRate;


	/**
	 * 跟买人数 
	 */
	@Column
	private float followBuy;


	/**
	 * 头像
	 */
	@Column
	private String avatar;


	/**
	 * 个人介绍
	 */
	@Column(length=1000)
	private String info;


	/**
	 * 手机
	 */
	@Column
	private String phone;
	
	/**
	 * 微信账号
	 */
	@Column
	private String weichat;
	
	/**
	 * 微博账号
	 */
	@Column
	private String weibo;


	/**
	 * 总收益
	 */
	private float totalIncome;


	/**
	 * 昨日收益
	 */
	private float lastIncome;
	
	
	public String getAvatar() {
		return avatar;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	public float getFollowBuy() {
		return followBuy;
	}
	
	public String getInfo() {
		return info;
	}
	
	
	public float getLastIncome() {
		return lastIncome;
	}
	
	public int getLevel() {
		return level;
	}


	public Member getMember() {
		return member;
	}


	public String getNickname() {
		return nickname;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	public String getPhone() {
		return phone;
	}


	public String getRegIp() {
		return regIp;
	}


	public float getScore() {
		return score;
	}


 

	public float getTotalIncome() {
		return totalIncome;
	}


	public float getTotalRate() {
		return totalRate;
	}


	public String getUsername() {
		return username;
	}


	public float getWealth() {
		return wealth;
	}


	public String getWeibo() {
		return weibo;
	}


	public String getWeichat() {
		return weichat;
	}


	public float getYearRate() {
		return yearRate;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setFollowBuy(float followBuy) {
		this.followBuy = followBuy;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public void setLastIncome(float lastIncome) {
		this.lastIncome = lastIncome;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


 

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setTotalIncome(float totalIncome) {
		this.totalIncome = totalIncome;
	}


	public void setTotalRate(float totalRate) {
		this.totalRate = totalRate;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setWealth(float wealth) {
		this.wealth = wealth;
	}
	
	
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	
	public void setWeichat(String weichat) {
		this.weichat = weichat;
	}
	
	
	public void setYearRate(float yearRate) {
		this.yearRate = yearRate;
	}
	
}
