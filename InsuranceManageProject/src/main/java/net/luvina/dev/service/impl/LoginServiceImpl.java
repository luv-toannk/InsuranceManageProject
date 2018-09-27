package net.luvina.dev.service.impl;

import net.luvina.dev.dao.LoginDao;
import net.luvina.dev.service.LoginService;
import net.luvina.dev.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	
	@Override
	public boolean checkLogin(String username, String password) {
		String encryptPassword = Common.encrytMD5(password);
		boolean isCheckLogin = loginDao.checkLogin(username, encryptPassword);
		return isCheckLogin;
	}
}
