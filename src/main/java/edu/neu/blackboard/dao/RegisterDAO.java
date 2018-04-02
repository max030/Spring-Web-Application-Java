package edu.neu.blackboard.dao;

import java.util.Collection;

import edu.neu.blackboard.domain.*;

public interface RegisterDAO {
	public void addUsers(Users user);
	public Collection<Users> listUsers();
}
