package net.luvina.dev.service.impl;

import net.luvina.dev.dao.TblCompanyDao;
import net.luvina.dev.model.Company;
import net.luvina.dev.service.TblCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TblCompanyServiceImpl implements TblCompanyService {
	
	@Autowired
	private TblCompanyDao tblCompanyDao;
	
	
	@Override
	public List<Company> getListCompany() {
		List<Company> listCompany = tblCompanyDao.getListCompany();
		return listCompany;
	}
	
	@Override
	public Company getCompany(int companyID) {
		Company company = tblCompanyDao.getCompany(companyID);
		return company;
	}
	
	@Override
	public boolean checkExistCompany(String companyName) {
		return tblCompanyDao.checkExistCompany(companyName);
	}
}
