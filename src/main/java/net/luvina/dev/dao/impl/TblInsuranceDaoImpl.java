package net.luvina.dev.dao.impl;

import net.luvina.dev.dao.TblInsuranceDao;
import net.luvina.dev.model.Insurance;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class TblInsuranceDaoImpl implements TblInsuranceDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public boolean checkExistInsuranceNumber(String insuranceNumber) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM Insurance WHERE isrNumber = :isrNumber";
			Query query = session.createQuery(hql);
			query.setParameter("isrNumber", insuranceNumber);
			Insurance insurance = (Insurance) query.uniqueResult();
			if (insurance != null) {
				return true;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void addInsurance(Insurance insurance) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(insurance);
			transaction.commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			transaction.rollback();
			
		}
		
	}
	
}
