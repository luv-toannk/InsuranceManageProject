package net.luvina.dev.service;

import net.luvina.dev.model.Company;
import net.luvina.dev.model.Insurance;
import net.luvina.dev.model.User;
import net.luvina.dev.model.UserInfo;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

public interface TblUserService {
	
	/**
	 * Lấy danh sách user
	 * @param companyID : mã công ty
	 * @param userName : tên của user
	 * @param insuranceNumber : mã số thẻ bảo hiểm
	 * @param placeReg : nơi đăng kí thẻ bảo hiểm
	 * @param sortStatus : trạng thái sắp xếp
	 * @param offset : vị trí bản ghi cần lấy ra
	 * @param limit : số bản ghi tối đa
	 * @return : danh sách User
	 */
	List<UserInfo> getListUser(int companyID, String userName, String insuranceNumber, String placeReg,
			String sortStatus, int offset, int limit);
	
	/**
	 * Lấy tổng số bản ghi
	 * @param companyID : mã công ty
	 * @param userName : tên của User
	 * @param insuranceNumber : mã số thẻ bảo hiểm
	 * @param placeReg : nơi đăng ký thẻ bảo hiểm
	 * @return : tổng số bản ghi
	 */
	Long getTotalUser(int companyID, String userName, String insuranceNumber, String placeReg);
	
	/**
	 * Thêm User và Công ty
	 * @param user : đối tượng User
	 * @param company : đối tượng Compnay
	 * @param statusCompany : biến đánh dấu vể trạng thái của công ty
	 */
	void addUser(User user, Company company, String statusCompany);
	
	/**
	 * Xử lý cho màn hình MH02
	 * @param model : truyền dữ liệu lên forms
	 * @return : trang MH02.jsp
	 */
	String listUser(Model model);

	/**
	 * Xử lý cho chức năng add
	 * @param user : đối tượng user
	 * @param bindingResult : tham số để bắt lỗi
	 * @param model : truyền dữ liệu lên view
	 * @return : trang MH04.jsp nếu có lỗi nhập dữ liệu
	 * 			 trang MH02.jsp nếu thêm thành công
	 */
	String addUserHandler(@Valid User user, BindingResult bindingResult, Model model);
}
