package edu.neu.blackboard.service;
import java.util.Collection;

import edu.neu.blackboard.domain.*;

public interface LoginService {
	public Users validateUser(Users user);

	public Collection<Users> list();

	public void increasetotalcost(String totalcost, String email);
public long checkticket2(String email);
}
