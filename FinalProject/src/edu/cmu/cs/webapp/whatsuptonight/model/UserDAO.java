package edu.cmu.cs.webapp.whatsuptonight.model;
import java.util.Date;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserCategory;

public class UserDAO extends GenericDAO<User> {
	public UserDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(User.class, tableName, connectionPool);
	}
	
	public int insertUser(User user) throws RollbackException {
		createAutoIncrement(user);
		User temp = readByEmailId(user.getEmailId());
		return temp.getUserId();		
	}
		
	public User readByEmailId(String emailId) throws RollbackException 
	{
		User[] user = match(MatchArg.equals("emailId", emailId));
		if(user.length > 0)
			return user[0];
		return null;
	}

	public User[] searchByLastName(String searchString) {
		try {
			User[] users = match(MatchArg.equalsIgnoreCase("lastName", searchString));
			return users;
		} catch (RollbackException e) {
						
		} 
		return null;
	}
	
	public User getUserByUserId(int userId) throws RollbackException {
		User[] user = match(MatchArg.equals("userId", userId));
		if(user.length > 0)
			return user[0];
		return null;
	}
}
