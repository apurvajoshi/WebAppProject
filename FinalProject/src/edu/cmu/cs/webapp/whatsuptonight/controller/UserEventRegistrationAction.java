package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.databean.Ticket;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventRegistration;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.TicketDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventRegistrationDAO;

public class UserEventRegistrationAction extends Action {
	
	private EventDAO eventDAO;
	private TicketDAO ticketDAO;
	private UserEventRegistrationDAO userEventRegistrationDAO;
	
	public UserEventRegistrationAction(Model model) {
    	eventDAO  = model.getEventDAO();
    	ticketDAO = model.getTicketDAO();
	}

	public String getName() { return "userEventRegistration.do"; }

    public String perform(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	
    	System.out.println(request.getParameterValues("id")[0]);
    	int eventId = Integer.parseInt(request.getParameterValues("id")[0]);
    	       
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			
			Event[] events = eventDAO.match(MatchArg.equals("eventId", eventId));
			if(events.length != 1)
			{
				errors.add("0 or more than one row returned for the given event id in the database");
				return "home.jsp";
			}
			
			Ticket[] tickets = ticketDAO.match(MatchArg.equals("eventId", eventId));
			if(tickets.length != 1)
			{
				errors.add("0 or more than one row returned for the given ticket id in the database");
				return "home.jsp";
			}
			
			/* Success case */
       		session.setAttribute("eventId", events[0].getEventId());

       		
			/* Calculate the number of tickets already sold */
			UserEventRegistration[] ticketsSold = userEventRegistrationDAO.match(MatchArg.equals("ticketTypeId", tickets[0].getTicketId()));
			request.setAttribute("ticketsSold", ticketsSold.length);
       		request.setAttribute("event", events[0]);       		
       		request.setAttribute("ticket", tickets[0]);       		
	        return "eventdetails.jsp";
	 	} catch (NumberFormatException e) {
	 		errors.add(e.getMessage());
	 		return "home.jsp";
	 	} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "home.jsp";
	 	}
    }


}
