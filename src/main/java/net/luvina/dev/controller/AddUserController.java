package net.luvina.dev.controller;

import net.luvina.dev.dao.TblCompanyDao;
import net.luvina.dev.model.Company;
import net.luvina.dev.model.Insurance;
import net.luvina.dev.model.User;
import net.luvina.dev.service.TblCompanyService;
import net.luvina.dev.service.TblInsuranceService;
import net.luvina.dev.service.TblUserService;
import net.luvina.dev.util.Common;
import net.luvina.dev.validate.ValidateCompany;
import net.luvina.dev.validate.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class AddUserController {
	
	@Autowired
	private TblCompanyService tblCompanyService;
	
	@Autowired
	private TblUserService tblUserService;
	
	
	/**
	 * Lần đầu vào màn hình MH04
	 * @param model : truyền đối tượng lên form
	 * @return : trang MH04.jsp
	 */
	@RequestMapping(value = "/doAdd", method = RequestMethod.GET)
	public String addUserHanlder(Model model) {
		model.addAttribute("user", new User());
		return "MH04";
	}
	
	/**
	 * Xử lý chức năng add
	 * @param user : đối tượng user
	 * @param bindingResult : tham số để bắt lỗi
	 * @param model : truyền dữ liệu lên form
	 * @return : trang MH04.jsp nếu có lỗi nhập liệu
	 * 			 trang MH02.jsp nếu thêm thành công
	 */
	@RequestMapping(value = "/addAction", method = RequestMethod.POST)
	public String addAction(@Valid User user, BindingResult bindingResult, Model model) {
		return tblUserService.addUserHandler(user, bindingResult, model);
	}
	
	/**
	 * Xử lý Ajax
	 * @param id : mã công ty
	 * @return : thông tin công ty được chọn
	 */
	@RequestMapping(value = "/ajax/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String ajax(@PathVariable(value = "id") int id) {
		Company company = tblCompanyService.getCompany(id);
		return company.toString();
	}
}
