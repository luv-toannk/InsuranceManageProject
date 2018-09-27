package net.luvina.dev.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_company")
public class Company {
	@Id
	@Column(name = "company_internal_id")
	private int id;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "address")
	private String companyAdd;
	
	@Column(name = "email")
	private String companyEmail;
	
	@Column(name = "telephone")
	private String companyTel;
	
	
	public Company() {
		
	}
	
	public Company(int id, String companyName, String companyAdd, String companyEmail, String companyTel) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.companyAdd = companyAdd;
		this.companyEmail = companyEmail;
		this.companyTel = companyTel;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyAdd() {
		return companyAdd;
	}
	
	public void setCompanyAdd(String companyAdd) {
		this.companyAdd = companyAdd;
	}
	
	public String getCompanyEmail() {
		return companyEmail;
	}
	
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	
	public String getCompanyTel() {
		return companyTel;
	}
	
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
}
