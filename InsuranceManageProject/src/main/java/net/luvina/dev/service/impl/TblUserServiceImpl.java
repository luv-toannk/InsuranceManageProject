package net.luvina.dev.service.impl;

import net.luvina.dev.dao.TblUserDao;
import net.luvina.dev.model.UserInfo;
import net.luvina.dev.service.TblUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TblUserServiceImpl implements TblUserService {
	
	@Autowired
	private TblUserDao tblUserDAO;
	
	
	@Override
	public List<UserInfo> getListUser(int companyID, String userName, String insuranceNumber, String placeReg,
			String sortStatus, int offset, int limit) {
		List<UserInfo> listUser = new ArrayList<UserInfo>();
		listUser = tblUserDAO.getListUser(companyID, userName, insuranceNumber, placeReg, sortStatus, offset, limit);
		return listUser;
	}
	
	@Override
	public Long getTotalUser(int companyID, String userName, String insuranceNumber, String placeReg) {
		Long totalRecord = tblUserDAO.getTotalUser(companyID, userName, insuranceNumber, placeReg);
		return totalRecord;
	}
}
