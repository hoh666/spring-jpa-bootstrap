package com.hjq.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hjq.config.Configuration;
import com.hjq.entity.User;

public class BaseController {

	public static final String LOGIN_TAB = "login";// 指向login tab
	public static final String REGISTER_TAB = "register";// 指向 register tab
	public static final String TAB_FOCUS = "focus";// 默认选定tab
	public static final String ERR_MSG = "error_message";
	public static final String USER = "user";
	public static final String START_TIME = " 00:00:00";
	public static final String END_TIME = " 23:59:59";

	protected final static String SUCCESS_URL = "success";
	protected final static String FAILURE_URL = "fail";

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected Configuration config;

	private @Inject DozerBeanMapper dozerBeanMapper;

	protected <T> T mapping(Object source, Class<T> destinationClass) {
		try {
			return dozerBeanMapper.map(source, destinationClass);
		} catch (Exception e) {
			return null;
		}
	}

	protected void mapping(Object source, Object destination) {
		try {
			dozerBeanMapper.map(source, destination);
		} catch (Exception e) {
			return ;
		}
	}

	protected String redirectUrl(String uri) {
		return new StringBuilder().append("redirect:").append(uri).toString();
	}

	protected User getUser(HttpSession session) {
		return (User) session.getAttribute(USER);
	}

	/**
	 * 拼接查询开始时间 param + " 00:00:00";
	 * @param startDate
	 * @return
	 */
	protected String getStartDateTime(String startDate) {
		return startDate + START_TIME;
	}

	/**
	 * 拼接查询结束时间param + " 23:59:59";
	 * @param endDate
	 * @return
	 */
	protected String getEndDateTime(String endDate) {
		return endDate + END_TIME;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}
}
