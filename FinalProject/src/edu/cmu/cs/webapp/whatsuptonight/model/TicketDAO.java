package edu.cmu.cs.webapp.whatsuptonight.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.Ticket;

public class TicketDAO extends GenericDAO<Ticket> {
	public TicketDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(Ticket.class, tableName, connectionPool);
	}

	public int lastInsertTicketId() throws ClassNotFoundException, SQLException {
		int ticketId = -1;
		
		Class.forName("com.mysql.jdbc.Driver");					
		Connection conn = DriverManager.getConnection("jdbc:mysql:///whatsuptonight");
		
		PreparedStatement query_string = conn.prepareStatement("SELECT LAST_INSERT_ID() FROM tickets");
		ResultSet rs = query_string.executeQuery();

		if(rs.next()) {
			ticketId = rs.getInt(1);
		}
		
		rs.close();
		query_string.close();
		
		return ticketId;
	}

	public Ticket getTicketByEventId(int eventId) throws RollbackException {
		Ticket[] tickets = match(MatchArg.equals("eventId", eventId));
		if(tickets.length > 0)
			return tickets[0];
		return null;
	}
}
