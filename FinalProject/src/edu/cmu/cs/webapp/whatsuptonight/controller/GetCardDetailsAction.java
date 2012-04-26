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


public class GetCardDetailsAction extends Action {
	
	private UserEventRegistrationDAO userEventRegistrationDAO;	
	private TicketDAO ticketDAO;
	private EventDAO eventDAO;

	public GetCardDetailsAction(Model model) {
		eventDAO = model.getEventDAO();
		ticketDAO = model.getTicketDAO();
    	userEventRegistrationDAO = model.getUserEventRegistrationDAO();
	}

	public String getName() { return "getCardDetails.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
    	// If eventId is not maintained in session then redirect to home page
        if (session.getAttribute("eventId") == null) {
        	return "home.do";
        }
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
        	int eventId = Integer.parseInt(session.getAttribute("eventId").toString());

			Event[] events = eventDAO.match(MatchArg.equals("eventId", eventId));
        	Ticket[] tickets = ticketDAO.match(MatchArg.equals("eventId", eventId));
            
    		int ticketsOrdered = Integer.parseInt((String)request.getParameterValues("quantity")[0]);
    		System.out.println("tickets ordered : " +  ticketsOrdered);

    		/* Calculate the number of tickets already sold */
    		UserEventRegistration[] ticketsSold = userEventRegistrationDAO.match(MatchArg.equals("ticketTypeId", tickets[0].getTicketTypeId()));
    		
    		if(ticketsSold.length + ticketsOrdered > tickets[0].getTicketQty())
    		{
    			errors.add("Not enough tickets available");
    			request.setAttribute("event", events[0]);       		
           		request.setAttribute("ticket", tickets[0]);       		
    	        return "eventdetails.jsp";
    		}
    		
            session.setAttribute("quantity", ticketsOrdered);
    		return "getpaymentdetails.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "welcome.jsp";
        } 
    }
}