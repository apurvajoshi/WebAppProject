package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.databean.Ticket;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.TicketDAO;

public class DisplayEventAction extends Action{
	private EventDAO eventDAO;
	private TicketDAO ticketDAO;
	
	public DisplayEventAction(Model model) {
		eventDAO = model.getEventDAO();
		ticketDAO = model.getTicketDAO();
	}

	public String getName() { return "displayEvent.do"; }
    
    public String perform(HttpServletRequest request) {
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        System.out.println("eventId from URI: " + request.getParameterValues("eventId")[0]);
        int eventId = Integer.parseInt(request.getParameterValues("eventId")[0].toString());
        Event event = null;
        try {
			event =eventDAO.getEventsByEventId(eventId);
		} catch (RollbackException e) {
			System.out.println("Error during edit event 1");
		}
        
        Ticket ticket = null;
        try {
			ticket = ticketDAO.getTicketByEventId(eventId);
		} catch (RollbackException e) {
			System.out.println("Error during edit event 2");
		}
                
        request.setAttribute("event", event);
        request.setAttribute("ticket", ticket);
       
		return "editEvent.jsp";       
    }
}
