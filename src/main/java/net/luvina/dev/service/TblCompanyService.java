package net.luvina.dev.service;

import net.luvina.dev.model.Company;

import java.util.List;

public interface TblCompanyService {
	
	/**
	 * Lấy danh sách công ty
	 * @return : danh sách công ty trong Database
	 */
	List<Company> getListCompany();
	
	/**
	 * Lấy thông tin chi tiết của công ty
	 * @param companyID : mã công ty
	 * @return : đối tượng Company
	 */
	Company getCompany(int companyID);
	
	/**
	 * Kiểm tra tên công ty đã tồn tại hay chưa
	 * @param companyName : tên công ty
	 * @return : true : nếu tên công ty đã tồn tại
	 *           false : nếu tên công ty chưa tồn tạis
	 */
	boolean checkExistCompany(String companyName);
}
