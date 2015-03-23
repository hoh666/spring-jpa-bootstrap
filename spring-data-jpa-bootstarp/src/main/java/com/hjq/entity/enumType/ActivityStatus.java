package com.hjq.entity.enumType;

import java.util.HashMap;
import java.util.Map;

public enum ActivityStatus {

	OPEN(0, "开启"),
	CLOSED(1, "关闭"),
	OVERDUE(2, "过期");

	private ActivityStatus(int code, String value) {
		this.code = code;
		this.value = value;
	}
	
	private int code;
	private String value;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	private static Map<Integer, ActivityStatus> map = new HashMap<Integer, ActivityStatus>();

	static {
		for (ActivityStatus status : values()) {
			map.put(status.getCode(), status);
		}
	}

	public static ActivityStatus getActivityStatusFromCode(int code) {
		return map.get(code) == null ? ActivityStatus.CLOSED : map.get(code);
	}
}
