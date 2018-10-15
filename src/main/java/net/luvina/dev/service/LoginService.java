package net.luvina.dev.service;

import net.luvina.dev.model.Admin;
import net.luvina.dev.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface LoginService {
	
	/**
	 * Kiểm tra đăng nhập
	 * @param username : tên đăng nhập
	 * @param password : mật khẩu
	 * @return : true : nếu đăng nhập thành công
	 *           falese : nếu đăng nhập thất bại
	 */
	boolean checkLogin(String username, String password);
	
	/**
	 * Xử lý đăng nhập
	 * @param admin : đối tượng admin
	 * @param bindingResult : tham số để bắt lỗi
	 * @param model : truyền dữ liệu lên view
	 * @return : trang MH01.jsp nếu có lỗi khi đăng nhập
	 *           trang MH02.jsp nếu đăng nhập thành công
	 */
	String loginHandler(@Valid Admin admin, BindingResult bindingResult, Model model);
}
