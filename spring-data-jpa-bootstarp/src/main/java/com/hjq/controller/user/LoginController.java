package com.hjq.controller.user;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjq.controller.BaseController;
import com.hjq.entity.ApiResponseModel;
import com.hjq.entity.User;
import com.hjq.service.SimpleUserService;

@Controller
@RequestMapping("/api")
public class LoginController extends BaseController {

	private static final String LOGIN_ERROR_MESSAGE = "用户名或密码错误，请重新输入！";

	@Inject private SimpleUserService simpleUserService;

	@RequestMapping("/goLogin")
	public String goLogin() {
		return "login";
	}

	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Object login(@Valid LoginForm loginForm, BindingResult bingingResult, Model model, HttpSession session) throws Exception {

		if (bingingResult.hasFieldErrors()) {

			return ApiResponseModel.failure(-1, bingingResult.getFieldError().getDefaultMessage());
		}

		User user = simpleUserService.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
		if (user == null) {
			return ApiResponseModel.failure(-1, LOGIN_ERROR_MESSAGE);
		}
		session.setAttribute(USER, user);
//		return redirectUrl(PageUriConstants.USER_MANAGE_URI);
		return ApiResponseModel.success();
	}

	@ResponseBody
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public Object logout(HttpSession session) throws Exception {

		session.removeAttribute(USER);
		return ApiResponseModel.success();
	}

	public static class LoginForm {

		@NotBlank(message="用户名不能为空!")
		private String username;

		@NotBlank(message="密码不能为空!")
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
