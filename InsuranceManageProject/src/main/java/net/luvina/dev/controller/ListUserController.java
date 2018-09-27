package net.luvina.dev.controller;

import net.luvina.dev.model.Company;
import net.luvina.dev.model.UserInfo;
import net.luvina.dev.service.TblCompanyService;
import net.luvina.dev.service.TblUserService;
import net.luvina.dev.util.Common;
import net.luvina.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ListUserController {
	
	@Autowired
	private TblCompanyService tblCompanyService;
	
	@Autowired
	private TblUserService tblUserService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(value = "/listUser", method = {
		RequestMethod.GET,
		RequestMethod.POST
	})
	public String listUser(Model model) {
		// Gửi đối tượng UserInfo lên form
		// model.addAttribute("userInfo", new UserInfo());
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
		String sortStatus2 = "ASC";
		
		String type = request.getParameter("case");
		System.out.println(type);
		
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
				}
				offset = Common.getOffset(currentPage, Constant.LIMIT);
			}
			
		}
		List<Company> listCompany = new ArrayList<Company>();
		listCompany = tblCompanyService.getListCompany();
		
		Long totalUser = tblUserService.getTotalUser(companyID, userName, insuranceNumber, placeReg);
		int totalUsers = totalUser.intValue();
		int totalPage = Common.getTotalPage(totalUsers, Constant.LIMIT);
		int lastPage = Common.getLastPage(totalPage, Constant.LIMIT_PAGE, currentPage);
		int firstPage = Common.getFirstPage(totalPage, Constant.LIMIT_PAGE, currentPage, lastPage);
		List<Integer> listPaging = Common.getListPaging(totalPage, Constant.LIMIT_PAGE, currentPage);
		// Gửi ListCompany lên view
		model.addAttribute("listCompany", listCompany);
		// Gửi giá trị công ty đã chọn lên view
		model.addAttribute("valueSelected", companyID);
		
		// Gửi trạng thái Sort lên session
		session.setAttribute("sortStatus", sortStatus);
		session.setAttribute("sortStatus2", sortStatus2);
		
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
		
		// Gửi ListUserInfo lên view
		List<UserInfo> listUserInfo = new ArrayList<UserInfo>();
		listUserInfo = tblUserService.getListUser(companyID, userName, insuranceNumber, placeReg, sortStatus, offset,
				limit);
		// model.addAttribute("listUserInfo", listUserInfo);
		session.setAttribute("listUserInfo", listUserInfo);
		model.addAttribute("listNull", Constant.LIST_NULL);
		
		return "MH02";
	}
}
