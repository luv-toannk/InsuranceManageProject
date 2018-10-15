package net.luvina.dev.controller;

import net.luvina.dev.model.Company;
import net.luvina.dev.model.UserInfo;
import net.luvina.dev.service.TblCompanyService;
import net.luvina.dev.service.TblUserService;
import net.luvina.dev.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ExportCSVController {
	
	@Autowired
	private TblCompanyService tblCompanyService;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	
	@ResponseBody
	@RequestMapping(value = "/exportCSV", method = RequestMethod.GET)
	public String genFileCSV() throws IOException {
		
		session = request.getSession();
		StringBuilder stringBuilder = new StringBuilder();
		String fileName = "listuser.csv";
		// lấy ID công ty truyền sang từ lisuser
		int companyID = (Integer) session.getAttribute("companyID");
		// lấy thông tin company đê ghi ra csv
		Company company = new Company();
		company = tblCompanyService.getCompany(companyID);
		List<UserInfo> listUserInfo = (List<UserInfo>) session.getAttribute("listUserInfo");
		// đưa thông tin cần lưu vào stringBuilder
		stringBuilder = stringBuilder.append(Common.parseToCSVFormat(company, listUserInfo));
		// thiết định file csv
		response.setContentType("text/csv;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		try {
			response.getOutputStream().write(stringBuilder.toString().getBytes());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			
			e.getMessage();
		}
		return "";
	}
}
