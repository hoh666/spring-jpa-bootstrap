package com.hjq.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hjq.entity.User;

@Controller
@RequestMapping("/api/home")
public class HomeController extends BaseController {

	@RequestMapping("/test")
	public String test(Model model, HttpServletResponse response) {
		logger.info("this is a test..");
		User user = new User();
		user.setUserName("joe");
		model.addAttribute("flag", "test");
		model.addAttribute("user", user);
//		return "upload/upload";
		return "test";
	}

}
