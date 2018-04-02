package edu.neu.blackboard.dao;
import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.blackboard.domain.*;

@Repository	



public class RegisterHibernateDAOImpl implements RegisterDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addUsers(Users user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public Collection<Users> listUsers() {
		return sessionFactory.getCurrentSession().createQuery("from Users").list();
	}

}
