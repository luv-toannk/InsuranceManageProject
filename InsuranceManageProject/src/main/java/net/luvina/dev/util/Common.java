package net.luvina.dev.util;

import net.luvina.dev.model.Company;
import net.luvina.dev.model.User;
import net.luvina.dev.model.UserInfo;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Common {
	
	/**
	 * Hàm dùng để mã hóa mật khẩu theo phương thức MD5
	 *
	 * @param password : chuỗi password cần được mã hóa
	 *
	 * @return : password sau khi mã hóa MD5
	 */
	public static String encrytMD5(String password) {
		String result = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes());
			BigInteger bigInteger = new BigInteger(1, digest.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Hàm dùng để lấy ra giới tính của user
	 *
	 * @param status
	 *            : trạng thái giới tính đang lưu trong DB. 01-Nam ; 02-Nữ
	 *
	 * @return : nếu status = "01" thì trả về giới tính Nam nếu status = "02" thì
	 *         trả về giới tính Nữ
	 */
	public static String changeSexDevision(String status) {
		String sexDevision = "";
		if ("01".equals(status)) {
			sexDevision = "Nam";
		} else {
			sexDevision = "Nữ";
		}
		return sexDevision;
	}
	
	/**
	 * Hàm format định dạng ngày tháng
	 *
	 * @param dateInput
	 *            : : Ngày tháng năm cần định dạng lại format
	 *
	 * @return : chuỗi String theo định dạng ngày tháng mong muốn
	 */
	public static String changeFormatDate(Date dateInput) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateFormat = simpleDateFormat.format(dateInput);
		return dateFormat;
	}
	
	/**
	 * Hàm set các thuộc tính cho đối tượng UserInfo
	 *
	 * @param user
	 *            : đối tượng user để lấy ra các thuộc tính
	 *
	 * @return : đối tượng UserInfo được set đầy đủ các thuộc tính
	 */
	public static UserInfo setUserInfo(User user) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(user.getUserName());
		userInfo.setUserSex(Common.changeSexDevision(user.getUserSex()));
		userInfo.setUserBirth(user.getUserBirth());
		userInfo.setInsuranceID(user.getInsuranceID().getIsrNumber());
		userInfo.setInsuranceStartDate(user.getInsuranceID().getIsrStartDate());
		userInfo.setInsuranceEndDate(user.getInsuranceID().getIsrEndDate());
		userInfo.setInsurancePlaceReg(user.getInsuranceID().getIsrPlaceReg());
		return userInfo;
	}
	
	/**
	 * Gen header của file csv
	 *
	 * @param company
	 *            : thông tin của đối tượng company được export
	 * @param listUserInfo
	 *            : danh sách UserInfo
	 * @return : chuỗi header
	 */
	public static String parseToCSVFormat(Company company, List<UserInfo> listUserInfo) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Danh sách thông tin thẻ bảo hiểm \n\n");
		stringBuilder.append("Tên công ty \t,\"" + company.getCompanyName() + "\"\n");
		stringBuilder.append("Địa chỉ \t,\"" + company.getCompanyAdd() + "\"\n");
		stringBuilder.append("Email \t,\"" + company.getCompanyEmail() + "\"\n");
		stringBuilder.append("Số điện thoại \t\"" + company.getCompanyTel() + "\"\n\n");
		
		stringBuilder.append(
				"Họ và tên \t,Giới tính \t,Ngày sinh \t,Mã số thẻ bảo hiểm \t,Ngày bắt đầu \t,Ngày kết thúc \t,Nơi đăng ký KCB \n");
		
		for (UserInfo userInfo : listUserInfo) {
			stringBuilder.append("\"" + userInfo.getUserName() + "\",");
			stringBuilder.append("\"" + userInfo.getUserSex() + "\",");
			stringBuilder.append("\"" + userInfo.getUserBirthFormat() + "\",");
			stringBuilder.append("\"" + userInfo.getInsuranceID() + "\",");
			stringBuilder.append("\"" + userInfo.getInsuranceStartDateFormat() + "\",");
			stringBuilder.append("\"" + userInfo.getInsuranceEndDateFormat() + "\",");
			stringBuilder.append("\"" + userInfo.getInsurancePlaceReg() + "\"\n");
		}
		
		return stringBuilder.toString();
	}
	
	/**
	 * Lấy ra trang cuối của khoảng trang
	 *
	 * @param totalPage
	 *            : tổng số trang
	 * @param limitPage
	 *            : số trang tối đa trên khoảng trang
	 * @param currentPage
	 *            : trang hiện tại
	 * @return :trang cuối cùng của khoảng trang
	 */
	public static int getLastPage(int totalPage, int limitPage, int currentPage) {
		int lastPage = 0;
		if (totalPage <= limitPage || currentPage >= totalPage - 1) {
			lastPage = totalPage;
		} else if (currentPage <= 2) {
			lastPage = limitPage;
		} else {
			lastPage = currentPage + 2;
		}
		return lastPage;
	}
	
	/**
	 * Lấy ra trang đầu tiên của khoảng trang
	 *
	 * @param totalPage
	 *            : tổng số trang
	 * @param limitPage
	 *            : số trang tối đa trên khoảng trang
	 * @param currentPage
	 *            : trang hiện tại
	 * @param lastPage
	 *            : trang cuối
	 * @return trang đầu tiên
	 */
	public static int getFirstPage(int totalPage, int limitPage, int currentPage, int lastPage) {
		int firstPage = 0;
		if (totalPage <= limitPage || currentPage <= 2) {
			firstPage = 1;
		} else {
			firstPage = lastPage - limitPage + 1;
		}
		return firstPage;
	}
	
	/**
	 * Lấy ra chuối Paging
	 * @param totalPage : tổng số trang
	 * @param limitPage : số trang tối đa trên khoảng trang
	 * @param currentPage : trang hiện tại
	 * @return : chuỗi Paging
	 */
	public static List<Integer> getListPaging(int totalPage, int limitPage, int currentPage) {
		List<Integer> pageList = new ArrayList<Integer>();
		int lastPage = getLastPage(totalPage, limitPage, currentPage);
		int firstPage = getFirstPage(totalPage, limitPage, currentPage, lastPage);
		for (int i = firstPage; i <= lastPage; i++) {
			pageList.add(i);
		}
		return pageList;
	}
	
	/**
	 * Lấy tổng số trang
	 * @param totalUser : tổng số bản ghi
	 * @param limit : số bản ghi tối đa trên 1 trang
	 * @return : tổng số trang
	 */
	public static int getTotalPage(int totalUser, int limit) {
		return (totalUser - 1) / limit + 1;
		
	}
	
	public static int getOffset(int currentPage, int limit) {
		return (currentPage - 1) * limit;
	}
}
