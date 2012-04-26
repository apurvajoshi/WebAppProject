package edu.cmu.cs.webapp.whatsuptonight.controller;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.genericdao.RollbackException;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.databean.Photo;
import edu.cmu.cs.webapp.whatsuptonight.databean.Ticket;
import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventCreation;
import edu.cmu.cs.webapp.whatsuptonight.formbean.EventForm;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.PhotoDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.TicketDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventCreationDAO;


public class CreateEventAction extends Action {
	private FormBeanFactory<EventForm> formBeanFactory = FormBeanFactory.getInstance(EventForm.class);
	
	private EventDAO eventDAO;
	private TicketDAO ticketDAO;
	private UserEventCreationDAO ueDAO;
	private PhotoDAO photoDAO;

	public CreateEventAction(Model model) {
		eventDAO = model.getEventDAO();
		ticketDAO = model.getTicketDAO();
		ueDAO = model.getUserEventCreationDAO();
		photoDAO = model.getPhotoDAO();
	}

	public String getName() { return "createEvent.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
         
        if(session.getAttribute("user") == null)
        	return "index.jsp";
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	EventForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "event.jsp";
	        }

	        errors.addAll(form.getValidationErrors());
	                
	        if (errors.size() != 0) {
	            return "event.jsp";
	        }
	        
       		if (form.getAction().equals("Create Event")) {
       		
       			Event newEvent = new Event();
       			newEvent.setTitle(form.getTitle());
       			newEvent.setDescription(form.getDesc());
       			newEvent.setLocation(form.getLocation());
       			newEvent.setCity(form.getCity());
       			newEvent.setStartDate(new Date(form.getStartDate()));
       			newEvent.setStartTime(form.getStartTime());
       			newEvent.setEndDate(new Date(form.getEndDate()));
       			newEvent.setEndTime(form.getEndTime());
       			newEvent.setCategory(form.getCategory());
       			newEvent.setPrivacy(form.getPrivacy());
       			newEvent.setOrganization(form.getHost());
       			String privacy = request.getParameter("privacy");
       			newEvent.setPrivacy(privacy);
       			newEvent.setInsertTime(new Date());
       			       			       		
	       		int eventId = -1;
				try {
					eventId = eventDAO.insertEventinDB(newEvent);
				} catch (ClassNotFoundException e) {
				
				} catch (SQLException e) {
					
				}
				
				if(eventId == -1) {
					errors.add("Error while creating the Event.");
					return "event.jsp";
				}
				
				FileProperty fileProp = form.getFile();
    			Photo photo = new Photo();  
    			photo.setEventId(eventId);
    			photo.setBytes(fileProp.getBytes());
    			photoDAO.create(photo);
	       		
	       		Ticket newTicket = new Ticket();
       			newTicket.setTicketName(form.getTicketName());
       			newTicket.setTicketQty(Integer.parseInt(form.getTicketQty()));
       			newTicket.setTicketPrice(Integer.parseInt(form.getTicketPrice()));
	       		newTicket.setEventId(eventId);
       			
	       		ticketDAO.createAutoIncrement(newTicket);
	       			       		
	       		int userId = ((User)session.getAttribute("user")).getUserId();
	       			
	       		if(eventId != -1) {
	       			UserEventCreation createEvent = new UserEventCreation();
	       			createEvent.setUserId(userId);
	       			createEvent.setEventId(eventId);
	       			
	       			ueDAO.createAutoIncrement(createEvent);
	       			
	       			return "myevents.jsp";
	       		}
	       		
	       		return "myevents.jsp";
       		} 
	        
	        return "event.jsp";
        } catch (RollbackException e) {        	
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
