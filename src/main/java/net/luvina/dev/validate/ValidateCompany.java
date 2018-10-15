package net.luvina.dev.validate;

import net.luvina.dev.model.Company;
import net.luvina.dev.service.TblCompanyService;
import net.luvina.dev.util.Common;
import net.luvina.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateCompany {
	
	@Autowired
	private TblCompanyService tblCompanyService;
	
	
	public List<String> errorList(Company company) {
		List<String> errorList = new ArrayList<String>();
		//Validate Company Name
		if (company.getCompanyName().isEmpty()) {
			errorList.add(Constant.ERROR01_COMPANYNAME);
		} else if (tblCompanyService.checkExistCompany(company.getCompanyName()) == true) {
			errorList.add(Constant.ERROR02_COMPANYNAME);
		}
		//Validate Company Address
		if (company.getCompanyAdd().isEmpty()) {
			errorList.add(Constant.ERROR01_COMPANYADD);
		}
		
		//Validate Company Email
		if (company.getCompanyEmail().isEmpty()) {
			errorList.add(Constant.ERROR01_COMPANYEMAIL);
		} else if (Common.checkFormatEmail(company.getCompanyEmail()) == false) {
			errorList.add(Constant.ERROR02_COMPANYEMAIL);
		}
		
		//Validate Tel
		if (company.getCompanyTel().isEmpty()) {
			errorList.add(Constant.ERROR01_COMPANYTEL);
		} else if (Common.checkFormat(company.getCompanyTel()) == false) {
			errorList.add(Constant.ERROR02_COMPANYTEL);
		}
		return errorList;
	}
	
}
