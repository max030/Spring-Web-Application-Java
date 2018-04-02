package edu.neu.blackboard.dao;


import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.blackboard.domain.*;
@Repository	
public class LoginHibernateDAOImpl implements LoginDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Users validateUser(Users user) {
		
		Query query=sessionFactory.getCurrentSession().createQuery("from Users as r where r.email='" + user.getEmail() + "' and r.password='" + user.getPassword() + "'");
		/*query.setParameter(0,user.getEmail());
		query.setParameter(1,user.getPassword());
		*/
	return (Users)query.uniqueResult();
	}

	@Override
	public Collection<Users> list() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Users").list();
	}

	@Override
	public long checkticket2(String email) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Criteria criteria = session.createCriteria(Users.class);
	    criteria.add(Restrictions.eq("email", email));
	    criteria.setProjection(Projections.rowCount());
	    long count = (Long) criteria.uniqueResult();
	    session.getTransaction().commit();
session.close();
	    return count;
		
	}

	@Override
	public void increasetotalcost(String totalcost, String email) {
		// TODO Auto-generated method stub
		
	}
}

