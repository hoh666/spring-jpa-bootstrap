package com.hjq.entity.enumType;

import java.util.HashMap;
import java.util.Map;

public enum ActivityChannel {

	UN_KNOWN(-1, "unkonwn"),
	TOUCH(0,"touch"),
	IOS(1, "ios"),
	ANDOIRD(2, "andoird");

	private int code;
	private String value;

	private ActivityChannel(int code, String value) {
		this.code = code;
		this.value = value;
	}

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

	private static Map<Integer, ActivityChannel> map = new HashMap<Integer, ActivityChannel>();

	static {
		for (ActivityChannel channel : values()) {
			map.put(channel.getCode(), channel);
		}
	}

	public static ActivityChannel getChannelByCode(int code) {
		return map.get(code) == null ? ActivityChannel.UN_KNOWN : map.get(code);
	}
}
