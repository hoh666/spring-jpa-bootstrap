package com.hjq.service;

import com.hjq.entity.User;

public interface SimpleUserService extends BaseIService<User, Long> {

	public User findByUsernameAndPassword(String username, String password);
}
