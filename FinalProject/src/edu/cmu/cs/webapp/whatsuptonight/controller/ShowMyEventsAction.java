package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventCreation;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventCreationDAO;

public class ShowMyEventsAction extends Action {
	private EventDAO eventDAO;
	private UserEventCreationDAO uecDAO;
		
	public ShowMyEventsAction(Model model) {
		eventDAO = model.getEventDAO();
		uecDAO = model.getUserEventCreationDAO();
	}

	public String getName() { return "showMyEvents.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
    	
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        int userId = ((User)session.getAttribute("user")).getUserId();
        
        UserEventCreation[] user_events = null;
        try {
			user_events = uecDAO.getEventsByUserId(userId);
		} catch (RollbackException e1) {
			System.out.println("Error while getting events for a User 1");
		}
        
        if(user_events.length > 0) {
	        ArrayList<Event> eventsList = new ArrayList<Event>();
	        Event temp;
	        for(int i=0; i<user_events.length; i++) {
		        try {
					temp = eventDAO.getEventsByEventId(user_events[i].getEventId());
					if(temp != null)
						eventsList.add(i, temp);
				} catch (RollbackException e) {
					System.out.println("Error while getting events for a User 2");
				}
	        }
	        request.setAttribute("eventsList", eventsList);
        }
		return "myevents.jsp";       
    }
}
