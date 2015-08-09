package com.xiaba2.core;

public class JsonResult {

	public JsonResult() {
		this.code = 0;
		this.msg = null;
		this.data = null;
	}
	
	public JsonResult(String msg) {
		this.code = 0;
		this.msg = msg;
		this.data = null;
	}

	public static int SUCCESS = 1;
	public static int FAIL = 0;

	private int code;
	private String msg;
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
