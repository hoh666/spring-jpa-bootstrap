package com.hjq.service.impl.activity;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hjq.entity.activity.ActivityRecord;
import com.hjq.repository.activity.ActivitySettingRepository;
import com.hjq.repository.specification.activity.ActivitySpecification;
import com.hjq.service.activity.ActivityService;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	private static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);
	private @Inject ActivitySettingRepository activitySettingRepository;

	@Override
	public ActivityRecord create(ActivityRecord entity) {
		try {
			entity = activitySettingRepository.save(entity);
		} catch (Exception e) {
			logger.error("ex = {}", e);
		}
		return entity;
	}

	@Override
	public Page<ActivityRecord> findAll(ActivityRecord item, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityRecord findOne(Long id) {

		return activitySettingRepository.findOne(id);
	}

	@Override
	public ActivityRecord deleteById(Long id) {
		// TODO Auto-generated method stub
		ActivityRecord entity = activitySettingRepository.findOne(id);
		try {
			activitySettingRepository.delete(entity);
		} catch (Exception e) {
			logger.error("删除 {} 失败", entity);
			logger.error("ex : {}", e);
		}
		return entity;
	}

	@Override
	public ActivityRecord update(ActivityRecord entity) {

		return activitySettingRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(List<ActivityRecord> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ActivityRecord> saveInBatch(List<ActivityRecord> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(ActivityRecord entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page<ActivityRecord> getActivitysBySpecification(Map<String, Object> param, Sort sort) {

		Pageable pageable = new PageRequest(Integer.valueOf((String) param.get("page")) - 1, DEFAULT_PAGE_SIZE, sort);
		return activitySettingRepository.findAll(ActivitySpecification.activityListQuery((String) param.get("activityName"), (String) param.get("startDateBegin"), (String) param.get("startDateEnd"), (String) param.get("endDateBegin"), (String) param.get("endDateEnd")), pageable);
	}

	@Override
	public List<ActivityRecord> activityListForTouch() {

		return activitySettingRepository.findAll(ActivitySpecification.activityListForTouch());
	}

}
