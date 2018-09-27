package net.luvina.dev.controller;

import net.luvina.dev.model.Admin;
import net.luvina.dev.service.LoginService;
import net.luvina.dev.util.Constant;
import net.luvina.dev.validate.ValidateLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private ValidateLogin validateLogin;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String viewPageLogin(Model model) {
		model.addAttribute("admin", new Admin());
		return "MH01";
	}
	
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String loginHanlder(@Valid Admin admin, BindingResult bindingResult, Model model) {
		session = request.getSession();
		validateLogin.validate(admin, bindingResult);
		if (bindingResult.hasErrors()) {
			return "MH01";
		} else {
			boolean checkLogin = loginService.checkLogin(admin.getUsername(), admin.getPassword());
			if (!checkLogin) {
				model.addAttribute("errorMessage", Constant.ERROR_LOGIN);
			} else {
				session.setAttribute("username", admin.getUsername());
				return "redirect:listUser?case=listAll";
			}
		}
		return "MH01";
	}
}
