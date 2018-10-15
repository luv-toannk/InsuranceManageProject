package net.luvina.dev.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tbl_insurance")
public class Insurance {
	
	@Id
	@Column(name = "insurance_internal_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "insurance_number")
	private String isrNumber;
	
	@Column(name = "insurance_start_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date isrStartDate;
	
	@Column(name = "insurance_end_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date isrEndDate;
	
	@Column(name = "place_of_register")
	private String isrPlaceReg;
	
	
	public Insurance() {
		
	}
	
	public Insurance(int id, String isrNumber, Date isrStartDate, Date isrEndDate, String isrPlaceReg) {
		super();
		this.id = id;
		this.isrNumber = isrNumber;
		this.isrStartDate = isrStartDate;
		this.isrEndDate = isrEndDate;
		this.isrPlaceReg = isrPlaceReg;
	}

	public Insurance(String isrNumber, Date isrStartDate, Date isrEndDate, String isrPlaceReg) {
		this.isrNumber = isrNumber;
		this.isrStartDate = isrStartDate;
		this.isrEndDate = isrEndDate;
		this.isrPlaceReg = isrPlaceReg;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIsrNumber() {
		return isrNumber;
	}
	
	public void setIsrNumber(String isrNumber) {
		this.isrNumber = isrNumber;
	}
	
	public Date getIsrStartDate() {
		return isrStartDate;
	}
	
	public void setIsrStartDate(Date isrStartDate) {
		this.isrStartDate = isrStartDate;
	}
	
	public Date getIsrEndDate() {
		return isrEndDate;
	}
	
	public void setIsrEndDate(Date isrEndDate) {
		this.isrEndDate = isrEndDate;
	}
	
	public String getIsrPlaceReg() {
		return isrPlaceReg;
	}
	
	public void setIsrPlaceReg(String isrPlaceReg) {
		this.isrPlaceReg = isrPlaceReg;
	}
	
}
