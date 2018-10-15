package net.luvina.dev.dao.impl;

import net.luvina.dev.dao.TblCompanyDao;
import net.luvina.dev.model.Company;
import org.hibernate.HibernateException;
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
public class TblCompanyDaoImpl implements TblCompanyDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Company> getListCompany() {
		Session session = sessionFactory.getCurrentSession();
		List<Company> listCompany = new ArrayList<Company>();
		String hql = "FROM Company ORDER BY companyName";
		Query query = session.createQuery(hql);
		listCompany = query.list();
		return listCompany;
	}
	
	@Override
	public Company getCompany(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM Company WHERE id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			Company company = (Company) query.uniqueResult();
			return company;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean checkExistCompany(String companyName) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM Company WHERE companyName = :companyName";
			Query query = session.createQuery(hql);
			query.setParameter("companyName", companyName);
			Company company = (Company) query.uniqueResult();
			if (company != null) {
				return true;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
