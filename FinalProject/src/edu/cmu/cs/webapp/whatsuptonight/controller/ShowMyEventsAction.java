package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.databean.EventList;
import edu.cmu.cs.webapp.whatsuptonight.databean.Ticket;
import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventCreation;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventRegistration;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.TicketDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventCreationDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventRegistrationDAO;

public class ShowMyEventsAction extends Action {
	private EventDAO eventDAO;
	private UserEventCreationDAO uecDAO;
	private TicketDAO ticketDAO;
	private UserEventRegistrationDAO uerDAO;
		
	public ShowMyEventsAction(Model model) {
		eventDAO = model.getEventDAO();
		uecDAO = model.getUserEventCreationDAO();
		ticketDAO = model.getTicketDAO();
		uerDAO = model.getUserEventRegistrationDAO();
	}

	public String getName() { return "showMyEvents.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
    	
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try
        {
        	int userId = ((User)session.getAttribute("user")).getUserId();
        	UserEventCreation[] user_events = null;
			user_events = uecDAO.getEventsByUserId(userId);
		
			if(user_events == null || user_events.length == 0) 
	        {
	        	errors.add("No event has yet been created by you.");
	        	return "mytickets.jsp";
	        }
        
			if(user_events.length > 0) 
			{

				ArrayList<EventList> eventsList = new ArrayList<EventList>();
				for(int i=0; i<user_events.length; i++) 
				{
					EventList temp = new EventList();
					temp.setEvent(eventDAO.getEventsByEventId(user_events[i].getEventId()));
					
					/* Find tickets sold for the event */
					Ticket  ticket = ticketDAO.getTicketByEventId(user_events[i].getEventId());
					int totalTickets = ticket.getTicketQty();
					int ticketsSold = uerDAO.getTicketCountByTicketTypeId(ticket.getTicketTypeId());					
					temp.setTicketsSold(ticketsSold);
					temp.setTotalTickets(totalTickets);					
					
					eventsList.add(i, temp);					
				}
				request.setAttribute("eventsList", eventsList);
			}
			return "myevents.jsp";       
        } catch (RollbackException e) 
        {
        	errors.add(e.getMessage());
        	return "getpaymentdetails.jsp";
        }
    }
}
