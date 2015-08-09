package com.xiaba2.cms.domain;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 文章
 * 
 * @author goddie
 *
 */
@Entity
@Table(name = "db_cms_article")
public class Article extends BaseUUIDEntity {

	/**
	 * 栏目 ID
	 */
	@ManyToOne
	private ArticleType type;

	/**
	 * 副栏目 ID
	 */
	@ManyToOne
	private ArticleType subType;

	/**
	 * 文档排序
	 */
	@Column
	private int sortRank;

	/**
	 * 属性
	 */
	@Column
	private String flag;

	/**
	 * 是否生成 HTML
	 */
	@Column
	private int genHTML;

	/**
	 * 频道模型
	 */
	@Column
	private int channel;

	/**
	 * 浏览权限
	 */
	@Column
	private int accessRank;

	/**
	 * 点击次数
	 */
	@Column
	private int visits;

	/**
	 * 需要消耗金币
	 */
	@Column
	private int money;

	/**
	 * 文档标题
	 */
	@Column
	private String title;

	/**
	 * 短标题
	 */
	@Column
	private String shortTitle;

	/**
	 * 标题颜色
	 */
	@Column
	private String titleColor;

	/**
	 * 作者
	 */
	@Column
	private String writer;

	/**
	 * 来源
	 */
	@Column
	private String source;

	/**
	 * 缩略图
	 */
	@Column
	private String thumb;

	/**
	 * 发布日期
	 */
	@Column
	private Date pubDate;

	/**
	 * 投稿日期
	 */
	@Column
	private Date sendDate;

	/**
	 * 作者ID
	 */
	@ManyToOne
	private Member author;

	/**
	 * 文档关键词
	 */
	@Column
	private String keywords;

	/**
	 * 最后回复
	 */
	@ManyToOne
	private Member lastPost;

	/**
	 * 消耗积分
	 */
	@Column
	private int scores;

	/**
	 * 好评
	 */
	@Column
	private int goodPost;

	/**
	 * 差评
	 */
	@Column
	private int badPost;

	/**
	 * 不允许回复
	 */
	@Column
	private int notPost;

	/**
	 * 描述
	 */
	@Column(length = 1000)
	private String description;

	/**
	 * 自定义文件名
	 */
	@Column
	private String filename;

	/**
	 * 负责审核管理员的 ID
	 */
	@ManyToOne
	private Member check;

	/**
	 * 自定义类别
	 */
	@Column
	private int mType;

	/**
	 * 权重
	 */
	@Column
	private int weight;
	
	
	/**
	 * 跳转URL
	 */
	@Column
	private String redirectUrl;
	
	
	/**
	 * 用户IP
	 */
	@Column
	private String userIp;

	public int getAccessRank() {
		return accessRank;
	}

	public Member getAuthor() {
		return author;
	}

	public int getBadPost() {
		return badPost;
	}

	public int getChannel() {
		return channel;
	}

	public Member getCheck() {
		return check;
	}

	public String getDescription() {
		return description;
	}

	public String getFilename() {
		return filename;
	}

	public String getFlag() {
		return flag;
	}

	public int getGenHTML() {
		return genHTML;
	}

	public int getGoodPost() {
		return goodPost;
	}

	public String getKeywords() {
		return keywords;
	}

	public Member getLastPost() {
		return lastPost;
	}

	public int getMoney() {
		return money;
	}

	public int getmType() {
		return mType;
	}

	public int getNotPost() {
		return notPost;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public int getScores() {
		return scores;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public int getSortRank() {
		return sortRank;
	}

	public String getSource() {
		return source;
	}

	public ArticleType getSubType() {
		return subType;
	}

	public String getThumb() {
		return thumb;
	}

	public String getTitle() {
		return title;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public ArticleType getType() {
		return type;
	}

	public String getUserIp() {
		return userIp;
	}

	public int getVisits() {
		return visits;
	}

	public int getWeight() {
		return weight;
	}

	public String getWriter() {
		return writer;
	}

	public void setAccessRank(int accessRank) {
		this.accessRank = accessRank;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

	public void setBadPost(int badPost) {
		this.badPost = badPost;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public void setCheck(Member check) {
		this.check = check;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setGenHTML(int genHTML) {
		this.genHTML = genHTML;
	}

	public void setGoodPost(int goodPost) {
		this.goodPost = goodPost;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setLastPost(Member lastPost) {
		this.lastPost = lastPost;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public void setNotPost(int notPost) {
		this.notPost = notPost;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public void setSortRank(int sortRank) {
		this.sortRank = sortRank;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setSubType(ArticleType subType) {
		this.subType = subType;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public void setType(ArticleType type) {
		this.type = type;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

}
