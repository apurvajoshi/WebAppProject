package edu.cmu.cs.webapp.whatsuptonight.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventRegistration;

public class UserEventRegistrationDAO extends GenericDAO<UserEventRegistration> {
	public UserEventRegistrationDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(UserEventRegistration.class, tableName, connectionPool);
	}

	public UserEventRegistration[] getTicketsByUserId(int userId) throws RollbackException {
		UserEventRegistration[] userTickets = match(MatchArg.equals("userId", userId));
		if(userTickets.length > 0)
			return userTickets;
		return null;
	}
	
	public int getTicketCountByTicketTypeId(int ticketTypeId) throws RollbackException {
		UserEventRegistration[] userTickets = match(MatchArg.equals("ticketTypeId", ticketTypeId));
		return userTickets.length;
	}
	
	public UserEventRegistration[] getTicketsByTicketTypeId(int ticketTypeId) throws RollbackException {
		UserEventRegistration[] userTickets = match(MatchArg.equals("ticketTypeId", ticketTypeId));
		if (userTickets.length > 0)
			return userTickets;
		return null;
	}
}
