package com.xiaba2.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;

/**
 * 图集
 * 
 * @author goddie
 *
 */
@Entity
@Table(name = "db_cms_album")
public class Album extends BaseUUIDEntity {

	/**
	 * 栏目 ID 
	 */
	@Column
	private int typeId;

	/**
	 * 显示列表样式
	 */
	@Column
	private int pageStyle;

	/**
	 * 最大宽度
	 */
	@Column
	private int maxWidth;

	/**
	 * 图片地址
	 */
	@Column(length = 1000)
	private String imgUrls;

	/**
	 * 行数
	 */
	@Column
	private int row;

	/**
	 * 列数
	 */
	@Column
	private int col;

	/**
	 * 特殊选项（下载远程图片、从 ZIP 压 缩包中解压图片、网上复制图片 ) 
	 */
	@Column
	private int isRm;

	/**
	 * 缩略图最大宽度
	 */
	@Column
	private int thumbMaxWidth;

	/**
	 * 每页显示图片数量
	 */
	@Column
	private int pageSize;

	/**
	 * 自定义模板
	 */
	@Column
	private String template;

	/**
	 * 用户 IP 
	 */
	@Column
	private String userIp;

	/**
	 * 跳转地址
	 */
	@Column
	private String redirectUrl;

	/**
	 * 图集详细说明
	 */
	@Column(length = 1000)
	private String body;

	public String getBody() {
		return body;
	}

	public int getCol() {
		return col;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public int getIsRm() {
		return isRm;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageStyle() {
		return pageStyle;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public int getRow() {
		return row;
	}

	public String getTemplate() {
		return template;
	}

	public int getThumbMaxWidth() {
		return thumbMaxWidth;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public void setIsRm(int isRm) {
		this.isRm = isRm;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageStyle(int pageStyle) {
		this.pageStyle = pageStyle;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public void setThumbMaxWidth(int thumbMaxWidth) {
		this.thumbMaxWidth = thumbMaxWidth;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
 

}
