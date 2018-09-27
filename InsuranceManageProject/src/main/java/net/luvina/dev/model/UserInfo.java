package net.luvina.dev.model;

import net.luvina.dev.util.Common;

import java.util.Date;
public class UserInfo {
	
	private String userName;
	
	private String userSex;
	
	private Date userBirth;
	
	private String insuranceID;
	
	private Date insuranceStartDate;
	
	private Date insuranceEndDate;
	
	private String insurancePlaceReg;
	
	
	public UserInfo() {
		
	}
	
	public UserInfo(String userName, String userSex, Date userBirth, String insuranceID, Date insuranceStartDate,
			Date insuranceEndDate, String insurancePlaceReg) {
		super();
		this.userName = userName;
		this.userSex = userSex;
		this.userBirth = userBirth;
		this.insuranceID = insuranceID;
		this.insuranceStartDate = insuranceStartDate;
		this.insuranceEndDate = insuranceEndDate;
		this.insurancePlaceReg = insurancePlaceReg;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserSex() {
		return userSex;
	}
	
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	
	public Date getUserBirth() {
		return userBirth;
	}
	
	public String getUserBirthFormat() {
		return Common.changeFormatDate(userBirth);
	}
	
	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
	
	public String getInsuranceID() {
		return insuranceID;
	}
	
	public void setInsuranceID(String insuranceID) {
		this.insuranceID = insuranceID;
	}
	
	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}
	
	public String getInsuranceStartDateFormat() {
		return Common.changeFormatDate(insuranceStartDate);
	}
	
	public void setInsuranceStartDate(Date insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}
	
	public Date getInsuranceEndDate() {
		return insuranceEndDate;
	}
	
	public String getInsuranceEndDateFormat() {
		return Common.changeFormatDate(insuranceEndDate);
	}
	
	public void setInsuranceEndDate(Date insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}
	
	public String getInsurancePlaceReg() {
		return insurancePlaceReg;
	}
	
	public void setInsurancePlaceReg(String insurancePlaceReg) {
		this.insurancePlaceReg = insurancePlaceReg;
	}
	
}
