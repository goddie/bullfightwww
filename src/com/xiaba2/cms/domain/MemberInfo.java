package com.xiaba2.cms.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

 


import com.xiaba2.core.BaseUUIDEntity;

/**
 * 会员信息
 * 
 * @author goddie
 *
 */
@Entity
@Table(name = "db_cms_member_info")
public class MemberInfo extends BaseUUIDEntity {

	/**
	 * 会员
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private Member member;

	/**
	 * 联系方式限制
	 */
	@Column
	private String onlynet;

	/**
	 * 性别
	 */
	@Column
	private String sex;

	/**
	 * 昵称
	 */
	@Column
	private String nickname;

	/**
	 * QQ 号
	 */
	@Column
	private String qq;

	/**
	 * msn
	 */
	@Column
	private String msn;

	/**
	 * 电话号
	 */
	@Column
	private String tel;

	/**
	 * 手机
	 */
	@Column
	private String mobile;

	/**
	 * 目前所在地
	 */
	@Column
	private String place;

	/**
	 * 家乡
	 */
	@Column
	private String homeLand;

	/**
	 * 生日
	 */
	@Column
	private String birthday;

	/**
	 * 星座
	 */
	@Column
	private String star;

	/**
	 * 收入
	 */
	@Column
	private String income;

	/**
	 * 教育程度
	 */
	@Column
	private String education;

	/**
	 * 身高
	 */
	@Column
	private int height;

	/**
	 * 体型
	 */
	@Column
	private int bodyType;

	/**
	 * 血型
	 */
	@Column
	private String blood;

	/**
	 * 行业
	 */
	@Column
	private String vocation;

	/**
	 * 是否抽烟
	 */
	@Column
	private String smoke;

	/**
	 * 婚姻
	 */
	@Column
	private String marital;

	/**
	 * 住房
	 */
	@Column
	private String house;

	/**
	 * 是否饮酒
	 */
	@Column
	private String drink;

	/**
	 * 交友
	 */
	@Column
	private String datingType;

	/**
	 * 语言
	 */
	@Column
	private String language;

	/**
	 * 性格
	 */
	@Column
	private String nature;

	/**
	 * 人生格言
	 */
	@Column
	private String sign;

	/**
	 * 家庭住址
	 */
	@Column
	private String address;

	/**
	 * 更新时间
	 */
	private Date upTime;

	public String getAddress() {
		return address;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBlood() {
		return blood;
	}

	public int getBodyType() {
		return bodyType;
	}

	public String getDatingType() {
		return datingType;
	}

	public String getDrink() {
		return drink;
	}

	public String getEducation() {
		return education;
	}

	public int getHeight() {
		return height;
	}

	public String getHomeLand() {
		return homeLand;
	}

	public String getHouse() {
		return house;
	}

	public String getIncome() {
		return income;
	}

	public String getLanguage() {
		return language;
	}

	public String getMarital() {
		return marital;
	}

	public Member getMember() {
		return member;
	}

	public String getMobile() {
		return mobile;
	}

	public String getMsn() {
		return msn;
	}

	public String getNature() {
		return nature;
	}

	public String getNickname() {
		return nickname;
	}

	public String getOnlynet() {
		return onlynet;
	}

	public String getPlace() {
		return place;
	}

	public String getQq() {
		return qq;
	}

	public String getSex() {
		return sex;
	}

	public String getSign() {
		return sign;
	}

	public String getSmoke() {
		return smoke;
	}

	public String getStar() {
		return star;
	}

	public String getTel() {
		return tel;
	}

	public Date getUpTime() {
		return upTime;
	}

	public String getVocation() {
		return vocation;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public void setBodyType(int bodyType) {
		this.bodyType = bodyType;
	}

	public void setDatingType(String datingType) {
		this.datingType = datingType;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setHomeLand(String homeLand) {
		this.homeLand = homeLand;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setOnlynet(String onlynet) {
		this.onlynet = onlynet;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

}
