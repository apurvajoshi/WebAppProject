package edu.cmu.cs.webapp.whatsuptonight.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventCreation;

public class UserEventCreationDAO extends GenericDAO<UserEventCreation> {
	public UserEventCreationDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(UserEventCreation.class, tableName, connectionPool);
	}

	public UserEventCreation[] getEventsByUserId(int userId) throws RollbackException {
		UserEventCreation[] user_events = match(MatchArg.equals("userId", userId));
		if(user_events.length > 0)
			return user_events;
		return null;
	}
}
