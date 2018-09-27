package net.luvina.dev.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User {
	@Id
	@Column(name = "user_internal_id")
	private int userID;
	
	@ManyToOne()
	@JoinColumn(name = "company_internal_id")
	private Company companyID;
	
	@ManyToOne
	@JoinColumn(name = "insurance_internal_id")
	private Insurance insuranceID;
	
	@Column(name = "user_full_name")
	private String userName;
	
	@Column(name = "user_sex_division")
	private String userSex;
	
	@Column(name = "birthdate")
	private Date userBirth;
	
	
	public User() {
		
	}
	
	public User(int userID, Company companyID, Insurance insuranceID, String userName, String userSex, Date userBirth) {
		super();
		this.userID = userID;
		this.companyID = companyID;
		this.insuranceID = insuranceID;
		this.userName = userName;
		this.userSex = userSex;
		this.userBirth = userBirth;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public Company getCompanyID() {
		return companyID;
	}
	
	public void setCompanyID(Company companyID) {
		this.companyID = companyID;
	}
	
	public Insurance getInsuranceID() {
		return insuranceID;
	}
	
	public void setInsuranceID(Insurance insuranceID) {
		this.insuranceID = insuranceID;
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
	
	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
}
