package com.hjq.controller.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjq.config.CommonResponseMessage;
import com.hjq.controller.BaseController;
import com.hjq.entity.ApiResponseModel;
import com.hjq.entity.activity.ActivityRecord;
import com.hjq.entity.enumType.ActivityStatus;
import com.hjq.service.activity.ActivityService;

@Controller
@RequestMapping("/api/activity")
public class ActivitySettingController extends BaseController {

	private @Inject ActivityService activityService;

	@RequestMapping("/goAdd")
	public String goAddActivity() throws Exception {
		return "activity/addActivity";
	}

	@ResponseBody
	@RequestMapping("/add")
	public Object addActivity(@Valid ActivityForm form, BindingResult bindingResult, HttpServletRequest request, Model model) throws Exception {

		if (bindingResult.hasFieldErrors()) {
			return ApiResponseModel.failure(-1, bindingResult.getFieldError().getDefaultMessage());
		}

		ActivityRecord record = mapping(form, ActivityRecord.class);
		record = activityService.create(record);

		if (record.getId() != 0) {
			return ApiResponseModel.success();
		} else {
			return ApiResponseModel.failure(CommonResponseMessage.ADD_ACTIVITY_FAILED);
		}
	}

	@RequestMapping("/list")
	public String activityList(HttpServletRequest request) throws Exception {

		return "activity/activityList";
	}

	@ResponseBody
	@RequestMapping("/query")
	public Object queryActivity(HttpServletRequest request, @RequestParam(value="page", defaultValue="1") String page, 
				@RequestParam(value="activityName", defaultValue="") String activityName, 
				@RequestParam(value="startDateBegin", defaultValue="") String startDateBegin, @RequestParam(value="startDateEnd", defaultValue="") String startDateEnd, 
				@RequestParam(value="endDateBegin", defaultValue="") String endDateBegin, @RequestParam(value="endDateEnd", defaultValue="") String endDateEnd) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("activityName", activityName);
		params.put("startDateBegin", startDateBegin);
		params.put("startDateEnd", startDateEnd);
		params.put("endDateBegin", endDateBegin);
		params.put("endDateEnd", endDateEnd);

		Page<ActivityRecord> activityPage = activityService.getActivitysBySpecification(params, new Sort(Direction.DESC, "createDate"));
		return ApiResponseModel.success(activityPage);
	}

	@RequestMapping("/detail")
	public String activityDetail(HttpServletRequest request, @RequestParam(value="id", defaultValue="0") String id, Model model) throws Exception {

		ActivityRecord record = activityService.findOne(Long.valueOf(id));
		model.addAttribute("record", record);
		return "activity/editActivity";
	}

	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Object editActivity(@Valid ActivityForm form, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasFieldErrors()) {
			return ApiResponseModel.failure(-1, bindingResult.getFieldError().getDefaultMessage());
		}

		ActivityRecord record = mapping(form, ActivityRecord.class);
		record = activityService.create(record);

		if (record.getId() != 0) {
			return ApiResponseModel.success();
		} else {
			return ApiResponseModel.failure(CommonResponseMessage.ADD_ACTIVITY_FAILED);
		}
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

	public static class ActivityForm {

		private String id;

		@NotBlank(message="活动名称不能为空")
		private String activityName;

		@NotNull(message="开始时间不能为空")
		@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
		private Date startDate;

		@NotNull(message="结束时间不能为空")
		@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
		private Date endDate;

		private ActivityStatus status = ActivityStatus.CLOSED;

		private String discription;

		@NotBlank(message="活动地址不能为空")
		private String href;

		@NotNull(message="请选择打开方式")
		private boolean redirect;

		@NotBlank(message="焦点图地址不能为空")
		private String img;

		@NotBlank(message="统计事件名称不能为空")
		private String countName;

		@NotBlank(message="分站支持情况不能为空")
		private String unSupport;

		public String getActivityName() {
			return activityName;
		}

		public void setActivityName(String activityName) {
			this.activityName = activityName;
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public String getDiscription() {
			return discription;
		}

		public void setDiscription(String discription) {
			this.discription = discription;
		}

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public boolean isRedirect() {
			return redirect;
		}

		public void setRedirect(boolean redirect) {
			this.redirect = redirect;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getCountName() {
			return countName;
		}

		public void setCountName(String countName) {
			this.countName = countName;
		}

		public String getUnSupport() {
			return unSupport;
		}

		public void setUnSupport(String unSupport) {
			this.unSupport = unSupport;
		}

		public ActivityStatus getStatus() {
			return status;
		}

		public void setStatus(ActivityStatus status) {
			this.status = status;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		
	}
}
