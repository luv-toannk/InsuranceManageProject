package net.luvina.dev.dao.impl;

import net.luvina.dev.dao.TblUserDao;
import net.luvina.dev.model.User;
import net.luvina.dev.model.UserInfo;
import net.luvina.dev.util.Common;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class TblUserDAOImpl implements TblUserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<UserInfo> getListUser(int companyID, String userName, String insuranceNumber, String placeReg,
			String sortStatus, int offset, int limit) {
		try {
			Session session = sessionFactory.getCurrentSession();
			List<User> listUser = new ArrayList<User>();
			List<UserInfo> listUserInfo = new ArrayList<UserInfo>();
			StringBuilder hql = new StringBuilder();
			hql.append("FROM User us INNER JOIN fetch us.insuranceID INNER JOIN fetch us.companyID");
			if (companyID > 0) {
				hql.append(" WHERE us.companyID.id = :companyID");
			}
			if (!"".equals(userName)) {
				hql.append(" AND us.userName LIKE :userName");
			}
			if (!"".equals(insuranceNumber)) {
				hql.append(" AND us.insuranceID.isrNumber LIKE :insuranceID");
			}
			if (!"".equals(placeReg)) {
				hql.append(" AND us.insuranceID.isrPlaceReg LIKE :insurancePlaceReg");
			}
			
			hql.append(" ORDER BY us.userName " + sortStatus);
			
			Query query = session.createQuery(hql.toString());
			
			if (companyID != 0) {
				query.setParameter("companyID", companyID);
			}
			if (!"".equals(userName)) {
				query.setParameter("userName", "%" + userName + "%");
			}
			if (!"".equals(insuranceNumber)) {
				query.setParameter("insuranceID", "" + insuranceNumber + "%");
			}
			
			if (!"".equals(placeReg)) {
				query.setParameter("insurancePlaceReg", "%" + placeReg + "%");
			}
			
			hql.append(" LIMIT offset,limit");
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			
			System.out.println(hql.toString());
			listUser = query.list();
			for (User user : listUser) {
				UserInfo userInfo = new UserInfo();
				userInfo = Common.setUserInfo(user);
				listUserInfo.add(userInfo);
			}
			return listUserInfo;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Long getTotalUser(int companyID, String userName, String insuranceNumber, String placeReg) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder hql = new StringBuilder();
		try {
			hql.append(" SELECT COUNT(*) FROM User us INNER JOIN us.insuranceID INNER JOIN us.companyID");
			if (companyID > 0) {
				hql.append(" WHERE us.companyID.id = :companyID");
			}
			if (!"".equals(userName)) {
				hql.append(" AND us.userName LIKE :userName");
			}
			if (!"".equals(insuranceNumber)) {
				hql.append(" AND us.insuranceID.isrNumber LIKE :insuranceID");
			}
			if (!"".equals(placeReg)) {
				hql.append(" AND us.insuranceID.isrPlaceReg LIKE :insurancePlaceReg");
			}
			
			Query query = session.createQuery(hql.toString());
			
			if (companyID != 0) {
				query.setParameter("companyID", companyID);
			}
			if (!"".equals(userName)) {
				query.setParameter("userName", "%" + userName + "%");
			}
			if (!"".equals(insuranceNumber)) {
				query.setParameter("insuranceID", "%" + insuranceNumber + "%");
			}
			
			if (!"".equals(placeReg)) {
				query.setParameter("insurancePlaceReg", "%" + placeReg + "%");
			}
			
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
