package net.luvina.dev.dao.impl;

import net.luvina.dev.dao.LoginDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public boolean checkLogin(String username, String password) {
		boolean checkLogin = false;
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Admin WHERE username = :username AND password = :password";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List list = query.list();
		if (list.size() == 1) {
			checkLogin = true;
		}
		return checkLogin;
	}
}
