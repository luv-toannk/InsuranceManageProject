package net.luvina.dev.service.impl;

import net.luvina.dev.dao.TblUserDao;
import net.luvina.dev.model.Company;
import net.luvina.dev.model.User;
import net.luvina.dev.model.UserInfo;
import net.luvina.dev.service.TblCompanyService;
import net.luvina.dev.service.TblInsuranceService;
import net.luvina.dev.service.TblUserService;
import net.luvina.dev.util.Common;
import net.luvina.dev.util.Constant;
import net.luvina.dev.validate.ValidateCompany;
import net.luvina.dev.validate.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class TblUserServiceImpl implements TblUserService {
	
	@Autowired
	private TblUserDao tblUserDAO;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private TblCompanyService tblCompanyService;
	
	@Autowired
	private ValidateUser validateUser;
	
	@Autowired
	private TblInsuranceService tblInsuranceService;
	
	@Autowired
	private TblUserService tblUserService;
	
	@Autowired
	private ValidateCompany validateCompany;
	
	
	@Override
	public List<UserInfo> getListUser(int companyID, String userName, String insuranceNumber, String placeReg,
			String sortStatus, int offset, int limit) {
		List<UserInfo> listUser =
				tblUserDAO.getListUser(companyID, userName, insuranceNumber, placeReg, sortStatus, offset, limit);
		return listUser;
	}
	
	@Override
	public Long getTotalUser(int companyID, String userName, String insuranceNumber, String placeReg) {
		Long totalRecord = tblUserDAO.getTotalUser(companyID, userName, insuranceNumber, placeReg);
		return totalRecord;
	}
	
	@Override
	public void addUser(User user, Company company, String statusCompany) {
		tblUserDAO.addUser(user, company, statusCompany);
	}
	
	@Override
	public String listUser(Model model) {
		session = request.getSession();
		// Default lần đầu tiên vào màn hình
		int companyID = 0;
		String userName = "";
		String insuranceNumber = "";
		String placeReg = "";
		String sortStatus = "ASC";
		int offset = 0;
		int limit = 3;
		int currentPage = 1;
		
		String type = request.getParameter("case");
		
		if ("listAll".equals(type)) {
			companyID = 0;
			userName = "";
			insuranceNumber = "";
			placeReg = "";
		} else if (Constant.CASE_SEARCH.equals(type)) { // Trường hợp Search
			companyID = Integer.parseInt(request.getParameter("companyID"));
			userName = request.getParameter("userName");
			insuranceNumber = request.getParameter("insuranceID");
			placeReg = request.getParameter("insurancePlaceReg");
		} else {// Trường hợp sort,back,paging
			// Lấy các giá trị tìm kiếm đã được lưu trên session
			companyID = (Integer) session.getAttribute("companyID");
			userName = (String) session.getAttribute("userName");
			insuranceNumber = (String) session.getAttribute("insuranceNumber");
			placeReg = (String) session.getAttribute("placeReg");
			// Trường hợp sort
			if (Constant.CASE_SORT.equals(type)) {
				sortStatus = (String) session.getAttribute("sortStatus");
				if ("DESC".equals(sortStatus)) {
					sortStatus = "ASC";
				} else {
					sortStatus = "DESC";
				}
			} else {// Trường hợp back,paging
				sortStatus = (String) session.getAttribute("sortStatus");
				if (Constant.CASE_PAGING.equals(type)) {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				} else {//Truong hop back
					currentPage = (int) session.getAttribute("currentPage");
				}
				offset = Common.getOffset(currentPage, Constant.LIMIT);
			}
		}
		List<Company> listCompany = tblCompanyService.getListCompany();
		
		Long totalUser = getTotalUser(companyID, userName, insuranceNumber, placeReg);
		int totalUsers = totalUser.intValue();
		int totalPage = Common.getTotalPage(totalUsers, Constant.LIMIT);
		int lastPage = Common.getLastPage(totalPage, Constant.LIMIT_PAGE, currentPage);
		int firstPage = Common.getFirstPage(totalPage, Constant.LIMIT_PAGE, currentPage, lastPage);
		List<Integer> listPaging = Common.getListPaging(totalPage, Constant.LIMIT_PAGE, currentPage);
		// Gửi ListCompany lên view
		model.addAttribute("listCompany", listCompany);
		// Gửi giá trị công ty đã chọn lên view
		model.addAttribute("valueSelected", companyID);
		session.setAttribute("valueSelected", companyID);
		
		// Gửi trạng thái Sort lên session
		session.setAttribute("sortStatus", sortStatus);
		
		request.setAttribute("firstPage", firstPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("listPaging", listPaging);
		
		// Gửi điều kiện tìm kiếm lên session
		session.setAttribute("companyID", companyID);
		session.setAttribute("userName", userName);
		session.setAttribute("insuranceNumber", insuranceNumber);
		session.setAttribute("placeReg", placeReg);
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("listCompany", listCompany);
		
		// Gửi ListUserInfo lên view
		List<UserInfo> listUserInfo =
				tblUserDAO.getListUser(companyID, userName, insuranceNumber, placeReg, sortStatus, offset, limit);
		// model.addAttribute("listUserInfo", listUserInfo);
		session.setAttribute("listUserInfo", listUserInfo);
		model.addAttribute("listNull", Constant.LIST_NULL);
		
		return "MH02";
	}
	
	@Override
	public String addUserHandler(@Valid User user, BindingResult bindingResult, Model model) {
		validateUser.validate(user, bindingResult);
		String status = request.getParameter("radioCheckCompany");
		int companyID = user.getCompanyID().getId();
		model.addAttribute("companyID", companyID);
		session.setAttribute("status", status);
		//Trường hợp chọn công ty cũ
		if (status.equals("have")) {
			if (bindingResult.hasErrors()) {
				return "MH04";
			} else {
				String companyName = request.getParameter("cName");
				String companyAdd = request.getParameter("cAdd");
				String companyEmail = request.getParameter("cEmail");
				String companyTel = request.getParameter("cTel");
				Company company = new Company(companyName, companyAdd, companyEmail, companyTel);
				tblInsuranceService.addInsurance(user.getInsuranceID());
				tblUserService.addUser(Common.formatUser(user), company, status);
				return "redirect:listUser?case=listAll";
			}
		}
		//Trường hợp chọn công ty mới
		if (status.equals("nothave")) {
			String companyName = request.getParameter("cName");
			String companyAdd = request.getParameter("cAdd");
			String companyEmail = request.getParameter("cEmail");
			String companyTel = request.getParameter("cTel");
			Company company = new Company(companyName, companyAdd, companyEmail, companyTel);
			List<String> errorList = validateCompany.errorList(company);
			if (bindingResult.hasErrors() || !errorList.isEmpty()) {
				model.addAttribute("errorList", errorList);
				return "MH04";
			} else {
				tblInsuranceService.addInsurance(user.getInsuranceID());
				tblUserService.addUser(Common.formatUser(user), company, status);
				return "redirect:listUser?case=listAll";
			}
		}
		return "MH04";
	}
}
