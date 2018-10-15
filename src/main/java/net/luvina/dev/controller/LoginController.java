package net.luvina.dev.controller;

import net.luvina.dev.model.Admin;
import net.luvina.dev.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * Lần đầu vào màn hình MH01
	 * @param model : truyền đối tượng lên form
	 * @return : trang MH01.jsp
	 */
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String viewPageLogin(Model model) {
		model.addAttribute("admin", new Admin());
		return "MH01";
	}

	/**
	 * Gọi hàm trong tầng Service để kiểm tra đăng nhập
	 * @param admin : đối tượng admin
	 * @param bindingResult : tham số để xử lý lỗi
	 * @param model : truyền dữ liệu lên view
	 * @return trang MH01.jsp nếu đăng nhập thất bại
	 * 		   trang MH02.jsp nêu đăng nhập thành công
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String loginHanlder(@Valid Admin admin, BindingResult bindingResult, Model model) {
		return loginService.loginHandler(admin, bindingResult, model);
	}
}
