package com.hjq.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjq.entity.User;
import com.hjq.entity.enumType.SexType;
import com.hjq.service.SimpleUserService;

@Controller
@RequestMapping("/api/user")
public class UserController extends BaseController {
	
	@Resource private SimpleUserService simpleUserService;

	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public Object createBean(@Valid @ModelAttribute(value="createUserBean") User user, HttpServletRequest request, Model model) {
		if (user == null) {
			user = new User();
		}
		user.setUserName("joe");
		user.setPassword("123456");
		user.setAge(22);
		user.setSex(SexType.MALE);
		user = simpleUserService.create(user);
		model.addAttribute("user", user);
		return user;
	}

	@RequestMapping(value="/query", method=RequestMethod.GET)
	public String getUsersByQueryItems(@RequestParam("username") String username, HttpServletRequest request, Model model) {
		User user = new User();
		user.setUserName(username);
		Page<User> users = simpleUserService.findAll(user, new Sort(Direction.DESC, "userName"));
//		long count = simpleUserService.count(user);
		//TODO combine users and count =>Page(...)
		model.addAttribute("users", users);
		return "user/userList";
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

	public Object paramArrayTest1(@ModelAttribute("bet") Bet bet) throws Exception {
		
		return null;
	}

	public static class Bet {
		private String lotteryType[];
		private String selectType[];
	}
}
