package com.hjq.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjq.entity.Profession;
import com.hjq.service.ProfessionService;

@Controller
@RequestMapping("/api/profession")
public class ProfessionController extends BaseController {

	private final static String ERR_MESSAGE = "err_msg";
	private final static String SAVE_ENTITY = "entity";
	@Inject ProfessionService professionService;

	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createProfession(@Valid ProfessionForm form, BindingResult bindingResult, Model model, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(ERR_MESSAGE, bindingResult.getFieldError().getDefaultMessage());
			logger.info("error : {}", bindingResult.getFieldError().getDefaultMessage());
			return FAILURE_URL;
		}

		Profession profession = mapping(form, Profession.class);
		professionService.create(profession);

		model.addAttribute(SAVE_ENTITY, profession);
		return SUCCESS_URL;
	}

	@ResponseBody
	@RequestMapping(value="/queryForSelect", method=RequestMethod.GET)
	public Object getProfessionList() {
		return professionService.findAll(null, null);
	}

	public static class ProfessionForm {

		@NotBlank(message="professionName 不能为空")
		private String professionName;

		public String getProfessionName() {
			return professionName;
		}

		public void setProfessionName(String professionName) {
			this.professionName = professionName;
		}
	}
}
