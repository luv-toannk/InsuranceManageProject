package net.luvina.dev.service.impl;

import net.luvina.dev.dao.TblInsuranceDao;
import net.luvina.dev.model.Insurance;
import net.luvina.dev.service.TblInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TblInsuranceServiceImpl implements TblInsuranceService {
	
	@Autowired
	private TblInsuranceDao tblInsuranceDao;
	
	
	@Override
	public boolean checkExistInsuranceNumber(String insuranceNumber) {
		return tblInsuranceDao.checkExistInsuranceNumber(insuranceNumber);
	}

	@Override
	public void addInsurance(Insurance insurance) {
		tblInsuranceDao.addInsurance(insurance);
	}

}
