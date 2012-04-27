package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.databean.MyTickets;
import edu.cmu.cs.webapp.whatsuptonight.databean.Ticket;
import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventRegistration;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.TicketDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventRegistrationDAO;

public class ShowMyTicketsAction extends Action {
	private EventDAO eventDAO;
	private UserEventRegistrationDAO uerDAO;
	private TicketDAO ticketDAO;
		
	public ShowMyTicketsAction(Model model) {
		eventDAO = model.getEventDAO();
		uerDAO = model.getUserEventRegistrationDAO();
		ticketDAO = model.getTicketDAO();
	}

	public String getName() { return "showMyTickets.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
    	
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        int userId = ((User)session.getAttribute("user")).getUserId();
        
		try 
		{
	        
			/* Get all the tickets for the user from the user event registration */
			UserEventRegistration[] userTickets = uerDAO.getTicketsByUserId(userId);
						
	        if(userTickets == null || userTickets.length == 0) 
	        {
	        	errors.add("No ticket has yet been purchased");
	        	return "mytickets.jsp";
	        }
	        
			/* Group by ticket type id - calculate sum total */
        	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        	for (UserEventRegistration ticket : userTickets) 
        	{
        	   int key = ticket.getTicketTypeId();
        	   if (map.get(key) == null) {
        	      map.put(key, 1);
        	   }
        	   else
        	   {
        		   map.put(key, map.get(key)+1);
        	   }
        	}

        	
            List<MyTickets> myTicketsList = new ArrayList<MyTickets>();
        	for (int key : map.keySet()) 
        	{
        	    Ticket ticket = ticketDAO.getTicketByTicketTypeId(key);
        	    Event event  = eventDAO.getEventsByEventId(ticket.getEventId());
        	    MyTickets myTickets = new MyTickets();
        	    myTickets.setEventId(event.getEventId());
        	    myTickets.setDate(event.getStartDate());
        	    myTickets.setTicketQty(map.get(key));
        	    myTickets.setTitle(event.getTitle());
        	    myTickets.setAmount(ticket.getTicketPrice() * map.get(key));
        	    myTicketsList.add(myTickets);
        	}
        	
            request.setAttribute("myTicketsList",myTicketsList);    
    		return "mytickets.jsp";       

	        
		} catch (RollbackException e) 
		{
			errors.add(e.getMessage());
        	return "getpaymentdetails.jsp";
		}
   }
}
