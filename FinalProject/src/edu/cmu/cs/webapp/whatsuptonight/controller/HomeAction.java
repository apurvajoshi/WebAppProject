package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;

public class HomeAction extends Action{
	
	private EventDAO eventDAO;
		
	public HomeAction(Model model) {
		eventDAO = model.getEventDAO();
	}

	public String getName() { return "home.do"; }
    
    public String perform(HttpServletRequest request) {
    	
        HttpSession session = request.getSession();
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        Event[] eventsList = null;
        try {
			eventsList =eventDAO.getAllEvents();
		} catch (RollbackException e) {
			
		}
        
        request.setAttribute("eventsList", eventsList);
       
		return "home.jsp";       
    }
}
