package net.luvina.dev.logic;

import net.luvina.dev.dao.CompanyDao;
import net.luvina.dev.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TblCompanyLogic {
	
	@Autowired
	CompanyDao companyDao;
	
	
	public Company findByCompanyID(int companyID) {
		return  companyDao.findById(companyID);
	}

	public List<Company> findByCompanyName(String companyName){
		return  companyDao.findByCompanyName(companyName);
	}

}
