package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.databean.Ticket;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.TicketDAO;

public class UserEventRegistrationAction extends Action {
	
	private EventDAO eventDAO;
	private TicketDAO ticketDAO;
	
	public UserEventRegistrationAction(Model model) {
    	eventDAO  = model.getEventDAO();
    	ticketDAO = model.getTicketDAO();
    }

	public String getName() { return "userEventRegistration.do"; }

    public String perform(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	
    	       
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
	    	System.out.println(request.getParameterValues("eventId")[0]);
	    	int eventId = Integer.parseInt(request.getParameterValues("eventId")[0]);
			
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
