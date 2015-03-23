package com.hjq.service.impl.activity;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hjq.entity.activity.ActivityStatisticInfo;
import com.hjq.repository.activity.ActivityStatisticRepository;
import com.hjq.service.activity.ActivityStatisticService;

@Service("activityStatisticService")
public class ActivityStatisticServiceImpl implements ActivityStatisticService {

	private static Logger logger = LoggerFactory.getLogger(ActivityStatisticServiceImpl.class);

	private @Inject ActivityStatisticRepository activityStatisticRepository;

	@Override
	public ActivityStatisticInfo create(ActivityStatisticInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ActivityStatisticInfo> findAll(ActivityStatisticInfo item,
			Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityStatisticInfo findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityStatisticInfo deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityStatisticInfo update(ActivityStatisticInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(List<ActivityStatisticInfo> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ActivityStatisticInfo> saveInBatch(
			List<ActivityStatisticInfo> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(ActivityStatisticInfo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Long> getCountGourpByActId(long actId) {

		List<Long> countList = null;

		try {
			countList = activityStatisticRepository.getCountGourpByActId(actId);
		} catch (Exception e) {
			logger.error("ex : {}", e);
		}

		return countList;
	}

}
