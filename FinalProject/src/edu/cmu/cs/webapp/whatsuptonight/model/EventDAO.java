package edu.cmu.cs.webapp.whatsuptonight.model;

import java.sql.SQLException;
import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import edu.cmu.cs.webapp.whatsuptonight.databean.Event;

public class EventDAO extends GenericDAO<Event> {
	public EventDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(Event.class, tableName, connectionPool);
	}
	
	public int insertEventinDB(Event event) throws RollbackException, ClassNotFoundException, SQLException {
		createAutoIncrement(event);
		int eventId = lastInsertEvent(event.getInsertTime());
		return eventId;
	}

	private int lastInsertEvent(Date insertTime) throws RollbackException {
		Event[] events = match(MatchArg.equals("insertTime", insertTime));
		if(events.length > 0) 
			return events[0].getEventId();
		return -1;
	}
	
	public Event[] getAllEvents() throws RollbackException {
		Event[] events = match();	
		if(events.length > 0)
			return events;
		return null;
	}

	public Event getEventsByEventId(int eventId) throws RollbackException {
		Event[] events = match(MatchArg.equals("eventId", eventId));
		if(events.length > 0)
			return events[0];
		return null;
	}
}
