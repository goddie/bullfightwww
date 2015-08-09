package com.xiaba2.invest.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 投资产品
 * @author goddie
 *
 */
@Entity
@Table(name = "db_invest_product")
public class Product extends BaseUUIDEntity {

	
	/**
	 * 名称
	 */
	@Column
	private String name;
	
	/**
	 * 完成度
	 */
	@Column
	private float finished;
	
	/**
	 * 年化收益
	 */
	@Column
	private float yearRate;
	
	
	/**
	 * 7日收益
	 */
	@Column
	private float weekRate;
	
	/**
	 * 投资周期
	 */
	@Column
	private float cycle;
	
	/**
	 * 产品状态
	 */
	@Column
	private int status;
	
	
	
	/**
	 * 担保方
	 */
	@Column
	private String ensure;
	
	
	/**
	 * 募集资金
	 */
	@Column
	private float goalCapital;
	
	/**
	 * 风险等级
	 */
	@Column
	private String risk;
	
	
	
	/**
	 * 回款方式
	 */
	@Column
	private String payType;
	
	/**
	 * 手续费率
	 */
	@Column
	private float fee;
	
	
	/**
	 * 介绍网页
	 */
	@Column
	private String url;
	
	
	/**
	 * 产品类型
	 */
	@Column
	private int productType;
	
	
	/**
	 * 万份收益
	 */
	@Column
	private float tenThousand;
	
	/**
	 * 起购金额
	 */
	@Column
	private float minBuy;
	
	/**
	 * 理财期限
	 */
	@Column
	private String timeLimit;
	
	
	/**
	 * 取现到账
	 */
	@Column
	private String cashTime;
	
	/**
	 * 购买须知
	 */
	@Column(length=1000)
	private String notice;
	
	
	/**
	 * 产品详情
	 */
	@Column(length=1000)
	private String info;
	
	
	/**
	 * 发布日期
	 */
	@Column
	private Date pubDate;
	
	
	/**
	 * 基金编码
	 */
	@Column
	private String code;
	
	
	/**
	 * 气息时间
	 */
	private Date interestStart;


	/**
	 * 结息时间
	 */
	private Date interestEnd;


	/**
	 * 到账日期
	 */
	private Date arriveDate;


	public Date getArriveDate() {
		return arriveDate;
	}


	public String getCashTime() {
		return cashTime;
	}


	public String getCode() {
		return code;
	}


	public float getCycle() {
		return cycle;
	}


	public String getEnsure() {
		return ensure;
	}


	public float getFee() {
		return fee;
	}


	public float getFinished() {
		return finished;
	}


	public float getGoalCapital() {
		return goalCapital;
	}


	public String getInfo() {
		return info;
	}


	public Date getInterestEnd() {
		return interestEnd;
	}


	public Date getInterestStart() {
		return interestStart;
	}


	public float getMinBuy() {
		return minBuy;
	}


	public String getName() {
		return name;
	}


	public String getNotice() {
		return notice;
	}


	public String getPayType() {
		return payType;
	}


	public int getProductType() {
		return productType;
	}


	public Date getPubDate() {
		return pubDate;
	}


	public String getRisk() {
		return risk;
	}


	public int getStatus() {
		return status;
	}


	public float getTenThousand() {
		return tenThousand;
	}


	public String getTimeLimit() {
		return timeLimit;
	}


	public String getUrl() {
		return url;
	}


	public float getWeekRate() {
		return weekRate;
	}


	public float getYearRate() {
		return yearRate;
	}


	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}


	public void setCashTime(String cashTime) {
		this.cashTime = cashTime;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public void setCycle(float cycle) {
		this.cycle = cycle;
	}


	public void setEnsure(String ensure) {
		this.ensure = ensure;
	}


	public void setFee(float fee) {
		this.fee = fee;
	}


	public void setFinished(float finished) {
		this.finished = finished;
	}


	public void setGoalCapital(float goalCapital) {
		this.goalCapital = goalCapital;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public void setInterestEnd(Date interestEnd) {
		this.interestEnd = interestEnd;
	}


	public void setInterestStart(Date interestStart) {
		this.interestStart = interestStart;
	}


	public void setMinBuy(float minBuy) {
		this.minBuy = minBuy;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setNotice(String notice) {
		this.notice = notice;
	}


	public void setPayType(String payType) {
		this.payType = payType;
	}


	public void setProductType(int productType) {
		this.productType = productType;
	}


	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}


	public void setRisk(String risk) {
		this.risk = risk;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public void setTenThousand(float tenThousand) {
		this.tenThousand = tenThousand;
	}


	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setWeekRate(float weekRate) {
		this.weekRate = weekRate;
	}
	
	
	public void setYearRate(float yearRate) {
		this.yearRate = yearRate;
	}
	
	
	
}
