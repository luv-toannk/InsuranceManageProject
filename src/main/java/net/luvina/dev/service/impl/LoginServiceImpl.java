package net.luvina.dev.service.impl;

import net.luvina.dev.dao.LoginDao;
import net.luvina.dev.model.Admin;
import net.luvina.dev.model.User;
import net.luvina.dev.service.LoginService;
import net.luvina.dev.util.Common;
import net.luvina.dev.util.Constant;
import net.luvina.dev.validate.ValidateLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ValidateLogin validateLogin;
	
	
	@Override
	public boolean checkLogin(String username, String password) {
		String encryptPassword = Common.encrytMD5(password);
		boolean isCheckLogin = loginDao.checkLogin(username, encryptPassword);
		return isCheckLogin;
	}
	
	@Override
	public String loginHandler(@Valid Admin admin, BindingResult bindingResult, Model model) {
		session = request.getSession();
		validateLogin.validate(admin, bindingResult);
		if (bindingResult.hasErrors()) {
			return "MH01";
		} else {
			boolean checkLogin = checkLogin(admin.getUsername(),admin.getPassword());
			System.out.println(checkLogin);
			if (checkLogin == false) {
				model.addAttribute("errorMessage", Constant.ERROR_LOGIN);
			} else {
				session.setAttribute("username", admin.getUsername());
				return "redirect:listUser?case=listAll";
			}
		}
		return "MH01";
	}
}
