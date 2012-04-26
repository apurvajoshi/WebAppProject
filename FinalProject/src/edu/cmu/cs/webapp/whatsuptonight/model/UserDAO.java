package edu.cmu.cs.webapp.whatsuptonight.model;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.User;

public class UserDAO extends GenericDAO<User> {
	public UserDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(User.class, tableName, connectionPool);
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
}
