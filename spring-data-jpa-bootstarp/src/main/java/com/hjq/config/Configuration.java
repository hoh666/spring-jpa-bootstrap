package com.hjq.config;

import java.util.ResourceBundle;

public class Configuration {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("jpa-bootstrap");

	private String touchSignatureKey = BUNDLE.getString("touch.signature.key");// touch调用接口查询签名key

	public String getTouchSignatureKey() {
		return touchSignatureKey;
	}

	public void setTouchSignatureKey(String touchSignatureKey) {
		this.touchSignatureKey = touchSignatureKey;
	}
}
