package com.hjq.service.activity;

import java.util.List;

import com.hjq.entity.activity.ActivityStatisticInfo;
import com.hjq.service.BaseIService;

public interface ActivityStatisticService extends BaseIService<ActivityStatisticInfo, Long> {

	public List<Long> getCountGourpByActId(long actId);
}
