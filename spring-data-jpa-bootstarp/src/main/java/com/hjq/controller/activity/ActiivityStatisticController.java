package com.hjq.controller.activity;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjq.controller.BaseController;
import com.hjq.service.activity.ActivityStatisticService;

@Controller
@RequestMapping("/api/activity/statistic")
public class ActiivityStatisticController extends BaseController {

	private @Inject ActivityStatisticService activityStatisticService;

	@ResponseBody
	@RequestMapping("/test")
	public Object statistic() throws Exception {
		return activityStatisticService.getCountGourpByActId(1);
	}
}
