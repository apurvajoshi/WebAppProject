package edu.cmu.cs.webapp.whatsuptonight.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	
	private UserDAO userDAO;
	private EventDAO eventDAO;
	private TicketDAO ticketDAO;
	private UserEventCreationDAO ueDAO;
	private PaymentDAO paymentDAO;
	private UserEventRegistrationDAO userEventRegistrationDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
						
			userDAO  = new UserDAO("users", pool);
			eventDAO = new EventDAO("events", pool);
			ticketDAO = new TicketDAO("tickets", pool);
			ueDAO = new UserEventCreationDAO("user_event_creation", pool);
			paymentDAO = new PaymentDAO("payment_details", pool);
			userEventRegistrationDAO = new UserEventRegistrationDAO("user_event_registration", pool);
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
		
	public UserDAO getUserDAO()  { return userDAO; }
	public EventDAO getEventDAO()  { return eventDAO; }
	public TicketDAO getTicketDAO()  { return ticketDAO; }
	public UserEventCreationDAO getUserEventCreationDAO()  { return ueDAO; }
	public PaymentDAO getPaymentDAO() { return paymentDAO; }
	public UserEventRegistrationDAO getUserEventRegistrationDAO() { return userEventRegistrationDAO; }

}