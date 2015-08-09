package com.xiaba2.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author goddie 实体基类
 */
@MappedSuperclass
public abstract class BaseEntity<ID> {

	/** 乐观锁版本,初始设置为0 */
	@Version
	@Column
	private int version = 0;

	@Column
	protected String aclCode;

	/** 数据访问控制类型 */
	protected String aclType;

	@Column
	protected String createdBy;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	@Column
	protected String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	protected Date lastModifiedDate;

	public abstract void setId(final ID id);

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(final String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(final Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getAclCode() {
		return aclCode;
	}

	public void setAclCode(String aclCode) {
		this.aclCode = aclCode;
	}

	public String getAclType() {
		return aclType;
	}

	public void setAclType(String aclType) {
		this.aclType = aclType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void resetCommonProperties() {
		setId(null);
		version = 0;
		lastModifiedBy = null;
		lastModifiedDate = null;
		createdBy = null;
		createdDate = null;
		aclCode = null;
		aclType = null;
	}

	private static final String[] PROPERTY_LIST = new String[] { "id",
			"version", "lastModifiedBy", "lastModifiedDate", "createdBy",
			"createdDate", "aclCode", "aclType" };

	public String[] retriveCommonProperties() {
		return PROPERTY_LIST;
	}

}
