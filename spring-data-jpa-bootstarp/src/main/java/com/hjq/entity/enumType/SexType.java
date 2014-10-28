package com.hjq.entity.enumType;

import java.util.HashMap;
import java.util.Map;

public enum SexType {

	MALE(0, "男"),
	FEMALE(1, "女");

	private int key;
	private String displayName;

	SexType(int key, String displayName) {
		this.key = key;
		this.displayName = displayName;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	private static Map<Integer, SexType> map = new HashMap<Integer, SexType>();

	static {
		for (SexType type : values()) {
			map.put(type.getKey(), type);
		}
	}

	public static SexType getSexTypeByKey(int key) {
		return map.get(key) == null ? SexType.MALE : SexType.FEMALE; 
	}
}
