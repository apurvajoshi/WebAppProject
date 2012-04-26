package edu.cmu.cs.webapp.whatsuptonight.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventRegistration;

public class UserEventRegistrationDAO extends GenericDAO<UserEventRegistration> {
	public UserEventRegistrationDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(UserEventRegistration.class, tableName, connectionPool);
	}
}
