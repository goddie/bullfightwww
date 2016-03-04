package com.xiaba2.bullfight.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 联赛
 * @author goddie
 *
 */

/**
 * @author goddie
 *
 */
@Entity
@Table(name = "db_bullfight_league")
public class League extends BaseUUIDEntity {

	/**
	 * 名称
	 */
	@Column
	private String name;
	
	/**
	 * 状态
	 * 0 未开始 1未结束 2已结束 
	 */
	@Column
	private int status;
	
	/**
	 * 排序数字
	 */
	@Column
	private int orderNum;
	
	/**
	 * 队伍总数
	 */
	@Column
	private int teamCount;
	
	/**
	 * 创办人
	 */
	@Column
	private String founder;
	
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
	 * 比赛场馆
	 */
	@ManyToOne
	private Arena arena;
	
	/**
	 * 头像
	 */
	@Column
	private String avatar;
	
	
	/**
	 * 是否公开报名
	 */
	@Column
	private int isOpen;
	
	/**
	 * 报名费
	 */
	@Column
	private float fee;
	
	
	
	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

 

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getTeamCount() {
		return teamCount;
	}

	public void setTeamCount(int teamCount) {
		this.teamCount = teamCount;
	}

	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
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
	
	
	
	
}
