package edu.cmu.cs.webapp.whatsuptonight.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.whatsuptonight.databean.Event;

public class EventDAO extends GenericDAO<Event> {
	public EventDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(Event.class, tableName, connectionPool);
	}
	
	public int insertEventinDB(Event event) throws RollbackException, ClassNotFoundException, SQLException {
		//Transaction.begin();
		createAutoIncrement(event);
		int eventId = lastInsertEventId();
		//Transaction.commit();
		return eventId;
	}
	
	public int lastInsertEventId() throws ClassNotFoundException, SQLException {
		int eventId = -1;
		
		Class.forName("com.mysql.jdbc.Driver");					
		Connection conn = DriverManager.getConnection("jdbc:mysql:///whatsuptonight");
		
		PreparedStatement query_string = conn.prepareStatement("SELECT max(eventId) FROM events");
		ResultSet rs = query_string.executeQuery();

		if(rs.next()) {
			eventId = rs.getInt(1);
		}
		
		rs.close();
		query_string.close();
		
		return eventId;
	}
}
