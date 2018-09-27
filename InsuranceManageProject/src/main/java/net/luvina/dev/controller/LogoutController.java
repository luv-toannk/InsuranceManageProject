package net.luvina.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
	
	@Autowired
	private HttpServletRequest req;
	
	@Autowired
	private HttpSession session;
	
	
	/**
	 * Xử lý khi click logout
	 * @return : điều hướng đến Controller của màn hình Login
	 */
	@RequestMapping(value = "/doLogout", method = RequestMethod.GET)
	public String doLogout() {
		session = req.getSession();
		session.removeAttribute("username");
		return "redirect:/login.htm";
	}
}
