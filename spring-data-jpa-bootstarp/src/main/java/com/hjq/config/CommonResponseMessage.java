package com.hjq.config;

public enum CommonResponseMessage {

	UNLOGIN(-2, "请登录"),
	ADD_REPORT_FAILED(-3, "保存反馈失败"),
	REPLY_REPORT_FAILED(-4, "回复反馈失败"),

	ADD_ACTIVITY_FAILED(-5, "新增活动失败"),

	PARAM_ERROR(-6, "请检查参数"),

	SIGNATURE_ERROR(-7, "签名验证失败");

	private long code;
	private String msg;

	private CommonResponseMessage(long code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
