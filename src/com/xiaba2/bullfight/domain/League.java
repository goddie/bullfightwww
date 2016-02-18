package com.xiaba2.bullfight.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 联赛
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
	@Column
	private Arena arean;
	
	public Arena getArean() {
		return arean;
	}

	public void setArean(Arena arean) {
		this.arean = arean;
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
