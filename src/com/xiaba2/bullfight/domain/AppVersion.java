package com.xiaba2.bullfight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaba2.core.BaseUUIDEntity;


@Entity
@Table(name = "db_bullfight_appversion")
public class AppVersion extends BaseUUIDEntity {
	
	
	@Column
	private String updateMessage;
	
	@Column
    private String apkUrl;
	
	@Column
    private int versionCode;
	
	@Column
    private float size;
	
	
	public String getUpdateMessage() {
		return updateMessage;
	}
	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}
	public String getApkUrl() {
		return apkUrl;
	}
	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
    
    
}
