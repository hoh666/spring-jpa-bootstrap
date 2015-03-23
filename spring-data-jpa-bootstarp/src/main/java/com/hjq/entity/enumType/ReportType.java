package com.hjq.entity.enumType;

import java.util.HashMap;
import java.util.Map;


public enum ReportType {

	DEFAULT(0, "默认"),
	UI(1, "样式弱到爆"),
	SLOW(2, "打开速度慢"),
	SERVER_ERROR(3, "服务异常多"),
	MANY_BUGS(4, "bug多"),
	OTHER(5, "其他");
	

	//TODO add other types

	private int code;
	private String value;

	private ReportType(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	private static Map<Integer, ReportType> map = new HashMap<Integer, ReportType>();
	static {
		ReportType[] types = ReportType.values();
		for (ReportType reportType : types) {
			map.put(reportType.getCode(), reportType);
		}
	}
	public static ReportType getReportTypeByCode(int code) {
		return map.get(code) == null ? ReportType.DEFAULT : map.get(code);
	}
}
