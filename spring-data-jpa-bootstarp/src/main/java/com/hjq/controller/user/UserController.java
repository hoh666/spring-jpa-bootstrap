package com.hjq.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjq.config.CommonResponseMessage;
import com.hjq.controller.BaseController;
import com.hjq.entity.ApiResponseModel;
import com.hjq.entity.Profession;
import com.hjq.entity.User;
import com.hjq.entity.enumType.SexType;
import com.hjq.service.ProfessionService;
import com.hjq.service.SimpleUserService;

@Controller
@RequestMapping("/api/user")
public class UserController extends BaseController {
	
	@Resource private SimpleUserService simpleUserService;
	@Inject private ProfessionService professionService;

	@RequestMapping(value="/goAdd", method=RequestMethod.GET)
	public String goAddUser(Model model) {

		model.addAttribute("professions", professionService.findAll(null, null));
		return "user/addUser";
	}

	@RequestMapping(value="/create", method={RequestMethod.GET, RequestMethod.POST})
	public String createBean(@Valid @ModelAttribute(value="createUserBean") UserForm userForm, BindingResult bindingResult, HttpServletRequest request, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errMsg", bindingResult.getFieldError());
			return "forward:/api/user/goAdd";
		}
		User user = mapping(userForm, User.class);
		user = simpleUserService.create(user);
		return redirectUrl("/api/user/query");
	}

	@RequestMapping(value="/query", method=RequestMethod.GET)
	public String getUsersByQueryItems(@RequestParam(value="username", required=false) String username, HttpServletRequest request, Model model) {
		User user = new User();
		user.setUserName(username);
		Page<User> users = simpleUserService.findAll(user, new Sort(Direction.DESC, "id", "userName"));
//		long count = simpleUserService.count(user);
		//TODO combine users and count =>Page(...)
		model.addAttribute("users", users);
		return "user/userList";
	}

	@ResponseBody
	@RequestMapping(value="/userinfo", method=RequestMethod.GET)
	public Object userinfo(HttpServletRequest request, HttpSession session) throws Exception {
		User user = getUser(session);
		if (user == null) {
			return ApiResponseModel.failure(CommonResponseMessage.UNLOGIN);
		} else {
			return ApiResponseModel.success(user);
		}
	}

	@ResponseBody
	@RequestMapping(value="/array", method=RequestMethod.GET)
	public Object paramArrayTest(@RequestParam MultiValueMap<String, String> paramMap) throws Exception {
		/**
		 *  lottery_type[]=82&select_type[]=2&pay[]=1&multiple[]=1&content[]=8200%3A8242%252014102201012(1)&upload_id[]=&
			lottery_type[]=85&select_type[]=2&pay[]=1&multiple[]=1&content[]=8500%3A8542%252014102201012(3)&upload_id[]=&
			lottery_type[]=81&select_type[]=2&pay[]=1&multiple[]=1&content[]=8100%3A8102%252014102201012(1)&upload_id[]=&
			lottery_type[]=83&select_type[]=2&pay[]=1&multiple[]=1&content[]=8300%3A8312%252014102201012(1)&upload_id[]=&create_type[]=508 
		*/

		List<String> keys = new ArrayList<String>();
		keys.add("lottery_type[]");
		keys.add("select_type[]");
		keys.add("pay[]");
		keys.add("multiple[]");
		keys.add("content[]");
		keys.add("upload_id[]");
		keys.add("create_type[]");
		// phase, paypwd

		for (String key : keys) {
			logger.info(key + ":" + key + ", value : " + paramMap.get(key));
		}

		return null;
	}

	public static class UserForm {

		@NotBlank(message="username ")
		private String userName;
		@NotBlank(message="password")
		private String password;
		@NotNull(message="sex")
		private SexType sex;

		@NotNull(message="profession")
		private Profession profession;

		private String description;

		private String[] testMultiple;

		@Max(value=100, message="age < 100")
		@Min(value=1, message="age > 0")
		private int age;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public SexType getSex() {
			return sex;
		}

		public void setSex(SexType sex) {
			this.sex = sex;
		}

		public Profession getProfession() {
			return profession;
		}

		public void setProfession(Profession profession) {
			this.profession = profession;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String[] getTestMultiple() {
			return testMultiple;
		}

		public void setTestMultiple(String[] testMultiple) {
			this.testMultiple = testMultiple;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
