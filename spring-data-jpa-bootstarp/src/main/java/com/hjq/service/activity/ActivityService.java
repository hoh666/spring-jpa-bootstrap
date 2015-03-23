package com.hjq.service.activity;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.hjq.entity.activity.ActivityRecord;
import com.hjq.service.BaseIService;

public interface ActivityService extends BaseIService<ActivityRecord, Long> {

	public Page<ActivityRecord> getActivitysBySpecification(Map<String, Object> param, Sort sort);

	public List<ActivityRecord> activityListForTouch();
}
