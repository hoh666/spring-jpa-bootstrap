package com.hjq.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hjq.config.CommonResponseMessage;

public class ApiResponseModel {

	private long code;
	private String message;
	private List<Object> content;

	public static ApiResponseModel success() {
		return new ApiResponseModel();
	}

	@SuppressWarnings("unchecked")
	public static ApiResponseModel success(Object content) {

		ApiResponseModel model = new ApiResponseModel();
		List<Object> list = new ArrayList<Object>();
		if (content instanceof Collection) {
			list.addAll((Collection<? extends Object>) content);
		} else {
			list.add(content);
		}

		model.setCode(0);
		model.setContent(list);
		model.setMessage("");
		return model;
	}

	public static ApiResponseModel failure(long code, String message) {
		return new ApiResponseModel(code, message);
	}

	public static ApiResponseModel failure(CommonResponseMessage responseMessage) {
		return new ApiResponseModel(responseMessage.getCode(), responseMessage.getMsg());
	}

	public ApiResponseModel() {
		this.code = 0;
		this.message = "";
	}

	public ApiResponseModel(List<Object> content) {
		this.code = 0;
		this.message = "";
		this.content = content;
	}

	public ApiResponseModel(long code, String message) {
		this.code = code;
		this.message = message;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object> getContent() {
		return content;
	}

	public void setContent(List<Object> content) {
		this.content = content;
	}
}
