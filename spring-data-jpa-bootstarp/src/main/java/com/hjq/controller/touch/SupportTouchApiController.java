package com.hjq.controller.touch;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjq.config.CommonResponseMessage;
import com.hjq.controller.BaseController;
import com.hjq.entity.ApiResponseModel;
import com.hjq.entity.activity.ActivityRecord;
import com.hjq.service.activity.ActivityService;
import com.hjq.util.CommonUtils;

@Controller
@RequestMapping("/api/touch")
public class SupportTouchApiController extends BaseController {

	private @Inject ActivityService activityService;

	@ResponseBody
	@RequestMapping("/active/events")
	public Object activity4Touch(@RequestParam("timestamp") String timestamp, @RequestParam("sign") String sign) throws Exception {

		if (!generateSign(timestamp, this.getConfig().getTouchSignatureKey()).equals(sign)) {
			return ApiResponseModel.failure(CommonResponseMessage.SIGNATURE_ERROR);
		}

		List<ActivityRecord> activityList = activityService.activityListForTouch();
		return ApiResponseModel.success(activityList);
	}

	public String generateSign(String timestamp, String signatureKey) throws Exception {

		StringBuilder sb = new StringBuilder(timestamp);
		sb.append("&").append(signatureKey);

		return CommonUtils.Md5(sb.toString());
	}
}
