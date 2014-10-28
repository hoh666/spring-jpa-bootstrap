package com.hjq.service.impl;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjq.entity.User;
import com.hjq.repository.SimpleUserRepository;
import com.hjq.service.SimpleUserService;

@Service("simpleUserService")
public class SimpleUserServiceImpl implements SimpleUserService {

	private final static Logger logger = LoggerFactory.getLogger(SimpleUserServiceImpl.class);

	@Autowired private SimpleUserRepository simpleUserRepository;

	@Transactional(rollbackFor=Exception.class)
	public User create(User entity) {

		logger.info("entity = {}", ToStringBuilder.reflectionToString(entity, ToStringStyle.SHORT_PREFIX_STYLE));
		try {
			entity = simpleUserRepository.save(entity);
		} catch (Exception e) {
			logger.error("create entity occurs problem : {}", e);
		}
		return entity;
	}

	@Transactional
	public Page<User> findAll(User item, Sort sort) {

		Pageable pageable = new PageRequest(DEFAULT_PAGE, DEFAULT_PAGE_SIZE, sort);
//		Page<User> users = simpleUserRepository.findAll(page);
		return simpleUserRepository.findAll(pageable);
	}

	@Transactional
	public User findOne(Long id) {

		return simpleUserRepository.findOne(id);
	}

	@Transactional(rollbackFor=Exception.class)
	public User deleteById(Long id) {

		User user = null;
		try {
			user = findOne(id);
			simpleUserRepository.delete(user);
		} catch (Exception e) {
			logger.error("deleteById failed,user = {}, problem = {}", new Object[]{ToStringBuilder.reflectionToString(user, ToStringStyle.SHORT_PREFIX_STYLE), e});
			user = null;
		}
		return user;
	}

	@Transactional(rollbackFor=Exception.class)
	public User update(User entity) {

		return simpleUserRepository.save(entity);
	}

	@Transactional(rollbackFor=Exception.class)
	public void deleteInBatch(List<User> entities) {

		try {
			simpleUserRepository.delete(entities);
		} catch (Exception e) {
			logger.error("deleteById failed, problem = {}", e);
		}
	}

	@Transactional(rollbackFor=Exception.class)
	public List<User> saveInBatch(List<User> entities) {

		return simpleUserRepository.save(entities);
	}

	public long count(User entity) {

		return simpleUserRepository.getUserCountByUserName(entity.getUserName());
	}

}
