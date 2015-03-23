package com.hjq.controller.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjq.config.CommonResponseMessage;
import com.hjq.controller.BaseController;
import com.hjq.entity.ApiResponseModel;
import com.hjq.entity.FeedBackContent;
import com.hjq.entity.User;
import com.hjq.entity.activity.ActivityRecord;
import com.hjq.entity.enumType.ReportType;
import com.hjq.service.ReportService;

@Controller
@RequestMapping("/api/report")
public class ReportController extends BaseController {

	private @Inject ReportService reportService;

	@RequestMapping("/goReport")
	public String goReport() {
		return "report/sendReport";
	}

	@RequestMapping("/list")
	public String showReportList(@RequestParam(value="username", defaultValue="") String username, @RequestParam(value="starttime", defaultValue="") String starttime, 
			@RequestParam(value="endtime", defaultValue="") String endtime, @RequestParam(value="page", defaultValue="1") String page, Model model) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", username);
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		param.put("page", page);

		Page<FeedBackContent> reportPage = reportService.getReportBySpecification(param, new Sort(Direction.DESC, "reportDate"));

		model.addAttribute("reportList", reportPage);
		return "report/reportList";
	}

	@ResponseBody
	@RequestMapping("/list/query")
	public Object queryReportList(@RequestParam(value="username", defaultValue="") String username, @RequestParam(value="starttime", defaultValue="") String starttime, 
			@RequestParam(value="endtime", defaultValue="") String endtime, @RequestParam(value="page", defaultValue="1") String page) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", username);
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		param.put("page", page);

		Page<FeedBackContent> reportPage = reportService.getReportBySpecification(param, new Sort(Direction.DESC, "reportDate"));

		return ApiResponseModel.success(reportPage);
	}

	@RequestMapping("/detail")
	public String showDetail(@RequestParam(value="uid") String uid, Model model) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("uid", uid);
		List<FeedBackContent> reportDetail = reportService.getReportDetail(param, new Sort(Direction.ASC, "reportDate"));
		model.addAttribute("reportDetail", reportDetail);
		return "report/reportDetail";
	}

	@ResponseBody
	@RequestMapping("/sendReport")
	public Object sendReport(HttpServletRequest request, HttpServletResponse response, @Valid ReportForm form, BindingResult bingingResult) throws Exception {

		if (bingingResult.hasFieldErrors()) {
			return ApiResponseModel.failure(-1, bingingResult.getFieldError().getDefaultMessage());
		}

		request.getParameter("reportType");

		FeedBackContent feedBack = mapping(form, FeedBackContent.class);
		logger.info("feedback report = {}", feedBack);

		//TODO 增加platform和source 
		feedBack.setPlatform("308");
		feedBack.setSource("5811");
		feedBack = reportService.create(feedBack);
		if (feedBack.getId() != 0) {
			return ApiResponseModel.success();
		} else {
			return ApiResponseModel.failure(CommonResponseMessage.ADD_REPORT_FAILED);
		}
	}

	@ResponseBody
	@RequestMapping("/test")
	public Object test() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", "");
		param.put("starttime", "");
		param.put("endtime", "");
		return reportService.getReportCount(param);
	}

	@ResponseBody
	@RequestMapping(value="/reply")
	public Object replyReport(HttpServletRequest request, @RequestParam("replyToUid") String replyToUid, @RequestParam("content") String content) throws Exception {

		User user = getUser(request.getSession());
		FeedBackContent feedBack = new FeedBackContent();
		feedBack.setContent(content);
		feedBack.setReplyToUid(Long.valueOf(replyToUid));
		feedBack.setUserId(String.valueOf(user.getId()));
		feedBack.setUsername(user.getUserName());
		feedBack.setReportType("");

		feedBack = reportService.create(feedBack);
		if (feedBack.getId() != 0) {
			return ApiResponseModel.success();
		} else {
			return ApiResponseModel.failure(CommonResponseMessage.ADD_REPORT_FAILED);
		}
	}

	@ResponseBody
	@RequestMapping(value="/deleteReply")
	public Object deleteReply(HttpServletRequest request, @RequestParam(value="id", required=false) String id) throws Exception {

		if (StringUtils.isBlank(id)) {
			return ApiResponseModel.failure(CommonResponseMessage.PARAM_ERROR);
		}

		FeedBackContent content = reportService.deleteById(Long.valueOf(id));

		if (content == null) {
			return ApiResponseModel.failure(-1, "删除反馈回复失败");
		}
		return ApiResponseModel.success();
	}

	public static void main(String[] args) {
		ReportForm form = new ReportForm();
		form.setContent("1234555");
		form.setMail("123@163.com");
		form.setPhone("13566965585");
		form.setReportType(new ReportType[]{ReportType.SLOW, ReportType.SERVER_ERROR});

		DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
		ActivityRecord feedBack = dozerBeanMapper.map(form, ActivityRecord.class);
		System.out.println(feedBack);
	}

	public static class ReportForm {

		@NotBlank(message="请输入反馈内容")
		private String content;

		private ReportType[] reportType = new ReportType[]{ReportType.DEFAULT};

		@Pattern(regexp="1[3-8][0-9]{9}$", message="请输入正确的手机号")
		private String phone;

		@Pattern(regexp="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", message="请输入正确的邮箱")
		private String mail;

		private String replyToUid;

		@NotBlank(message="用户id不能为空")
		private String uid;

		@NotBlank(message="username不能为空")
		private String username;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public ReportType[] getReportType() {
			return reportType;
		}

		public void setReportType(ReportType[] reportType) {
			this.reportType = reportType;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public String getReplyToUid() {
			return replyToUid;
		}

		public void setReplyToUid(String replyToUid) {
			this.replyToUid = replyToUid;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		
	}
}
