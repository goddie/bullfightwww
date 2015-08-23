package com.xiaba2.cms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

@Entity
@Table(name = "db_cms_user")
public class User extends BaseUUIDEntity {

	@Column
	private String username;

	private String password;

	@Column
	private String nickname;

	@Column
	private String email;

	@Column
	private String avatar;
	
	/**
	 * 球龄
	 */
	@Column
	private int age;
	
	/**
	 * 电话
	 */
	@Column
	private String phone;
	
	/**
	 * 微信
	 */
	@Column
	private String weiChat;
	
	/**
	 * 微博
	 */
	@Column
	private String weibo;

	/*---------------------*/

	@Column
	private float height;
	
	@Column
	private float weight;
	
	@Column
	private Date birthday;
	
	@Column
	private String position;
	/**
	 * 总得分
	 */
	@Column
	private float scoring;
	
	/**
	 * 场均得分
	 */
	@Column
	private float scoringAvg;
	
	/**
	 * 命中率
	 */
	@Column
	private float goalPercent;

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
	 * 篮板
	 */
	@Column
	private float rebound;
	
	@Column
	private float block;
	
	@Column
	private float steal;

	
	/**
	 * 助攻
	 */
	@Column
	private float assist;
	
	/**
	 * 胜利
	 */
	@Column
	private float win;

	/**
	 * 平局
	 */
	@Column
	private float draw;
	
	/**
	 * 比赛场数
	 */
	@Column
	private float playCount;
	
	/**
	 * 失败
	 */
	@Column
	private float lose;
	
	/**
	 * 关注他人人数
	 */
	@Column
	private int follows;
	
	/**
	 * 自己粉丝数
	 */
	@Column
	private int fans;
	
	@Column
	private String city;
	
	
	/**
	 * 银行卡号
	 * 存银行名称，帐号JSON
	 */
	@Column
	private String bankNo;
	
	/**
	 * 支付密码
	 */
	@Column
	private String payPassword;
	
	/**
	 * 犯规次数
	 */
	@Column
	private float foul;
	
	
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getFoul() {
		return foul;
	}

	public void setFoul(float foul) {
		this.foul = foul;
	}

	public float getSteal() {
		return steal;
	}

	public void setSteal(float steal) {
		this.steal = steal;
	}

	public float getBlock() {
		return block;
	}

	public void setBlock(float block) {
		this.block = block;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public float getScoringAvg() {
		return scoringAvg;
	}

	public void setScoringAvg(float scoringAvg) {
		this.scoringAvg = scoringAvg;
	}

	public int getFollows() {
		return follows;
	}

	public void setFollows(int follows) {
		this.follows = follows;
	}

	public int getFans() {
		return fans;
	}

	public void setFans(int fans) {
		this.fans = fans;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeiChat() {
		return weiChat;
	}

	public void setWeiChat(String weiChat) {
		this.weiChat = weiChat;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public float getDraw() {
		return draw;
	}

	public void setDraw(float draw) {
		this.draw = draw;
	}

	public float getWin() {
		return win;
	}

	public void setWin(float win) {
		this.win = win;
	}

	public float getPlayCount() {
		return playCount;
	}

	public void setPlayCount(float playCount) {
		this.playCount = playCount;
	}

	public float getLose() {
		return lose;
	}

	public void setLose(float lose) {
		this.lose = lose;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

 

 
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public float getScoring() {
		return scoring;
	}

	public void setScoring(float scoring) {
		this.scoring = scoring;
	}

	public float getGoalPercent() {
		return goalPercent;
	}

	public void setGoalPercent(float goalPercent) {
		this.goalPercent = goalPercent;
	}

	public float getThreeGoal() {
		return threeGoal;
	}

	public void setThreeGoal(float threeGoal) {
		this.threeGoal = threeGoal;
	}

	public float getThreeGoalPercent() {
		return threeGoalPercent;
	}

	public void setThreeGoalPercent(float threeGoalPercent) {
		this.threeGoalPercent = threeGoalPercent;
	}

	public float getRebound() {
		return rebound;
	}

	public void setRebound(float rebound) {
		this.rebound = rebound;
	}

	public float getAssist() {
		return assist;
	}

	public void setAssist(float assist) {
		this.assist = assist;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getEmail() {
		return email;
	}

 

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setEmail(String email) {
		this.email = email;
	}

 

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
