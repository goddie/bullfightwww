package com.xiaba2.bullfight.domain;

import com.xiaba2.core.BaseUUIDEntity;

public class AppVersion extends BaseUUIDEntity {
	
	
	private String updateMessage;
    private String apkUrl;
    private int versionCode;
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
