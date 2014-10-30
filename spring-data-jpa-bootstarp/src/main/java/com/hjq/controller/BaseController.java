package com.hjq.controller;

import javax.inject.Inject;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

	protected final static String SUCCESS_URL = "success";
	protected final static String FAILURE_URL = "fail";
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

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

}
