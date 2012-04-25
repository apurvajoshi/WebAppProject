package edu.cmu.cs.webapp.whatsuptonight.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventCreation;

public class UserEventCreationDAO extends GenericDAO<UserEventCreation> {
	public UserEventCreationDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(UserEventCreation.class, tableName, connectionPool);
	}
}
