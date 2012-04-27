package edu.cmu.cs.webapp.whatsuptonight.controller;

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
import edu.cmu.cs.webapp.whatsuptonight.formbean.EventForm;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.PhotoDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.TicketDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventCreationDAO;

public class UpdateEventAction extends Action{
private FormBeanFactory<EventForm> formBeanFactory = FormBeanFactory.getInstance(EventForm.class);
	
	private EventDAO eventDAO;
	private TicketDAO ticketDAO;
	private UserEventCreationDAO ueDAO;
	private PhotoDAO photoDAO;

	public UpdateEventAction(Model model) {
		eventDAO = model.getEventDAO();
		ticketDAO = model.getTicketDAO();
		ueDAO = model.getUserEventCreationDAO();
		photoDAO = model.getPhotoDAO();
	}

	public String getName() { return "updateEvent.do"; }
    
    public String perform(HttpServletRequest request) {
                 
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	EventForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "editEvent.jsp";
	        }

	        errors.addAll(form.getValidationErrors());
	                
	        if (errors.size() != 0) {
	            return "editEvent.jsp";
	        }
	        
       		if (form.getAction().equals("Save Event")) {
       		
       			Event oldEvent = new Event();
       			int oldEventId = Integer.parseInt(request.getParameterValues("eventId")[0]);
       			oldEvent.setEventId(oldEventId);
       			oldEvent.setTitle(form.getTitle());
       			oldEvent.setDescription(form.getDesc());
       			oldEvent.setLocation(form.getLocation());
       			oldEvent.setCity(form.getCity());       			
       			Date temp1 = new Date(form.getStartDate());
       			temp1.setHours(Integer.parseInt(form.getStartHour()));
       			temp1.setMinutes(Integer.parseInt(form.getStartMins()));
       			oldEvent.setStartDate(temp1);     
       			Date temp2 = new Date(form.getStartDate());
       			temp2.setHours(Integer.parseInt(form.getEndHour()));
       			temp2.setMinutes(Integer.parseInt(form.getEndMins()));
       			oldEvent.setEndDate(temp2);    			
       			oldEvent.setCategory(form.getCategory());       			
       			oldEvent.setOrganization(form.getHost());       			
       			oldEvent.setInsertTime(new Date());
       			       			       		
				eventDAO.update(oldEvent);
				
				if(!form.getFile().getFileName().equals("")) {
					Photo oldPhoto = photoDAO.getPhotoByEventId(oldEventId);
					FileProperty fileProp = form.getFile();
					Photo photo = new Photo();
					photo.setPhotoId(oldPhoto.getPhotoId());
	    			photo.setEventId(oldEventId);
	    			photo.setBytes(fileProp.getBytes());
	    			photoDAO.update(photo);
				}
	       		
	       		Ticket oldTicket = new Ticket();
	       		int oldTicketId = (ticketDAO.getTicketByEventId(oldEventId)).getTicketTypeId();
	       		oldTicket.setTicketTypeId(oldTicketId);
       			oldTicket.setTicketName(form.getTicketName());
       			oldTicket.setTicketQty(Integer.parseInt(form.getTicketQty()));
       			oldTicket.setTicketPrice(Integer.parseInt(form.getTicketPrice()));
	       		oldTicket.setEventId(oldEventId);
       			
	       		ticketDAO.update(oldTicket);
	       			       		
	       		/* Send email to all users who are registered for the event */
	       		
	       		
	       		/*int userId = ((User)session.getAttribute("user")).getUserId();
	       			
	       		if(oldEventId != -1) {
	       			UserEventCreation createEvent = new UserEventCreation();
	       			createEvent.setUserId(userId);
	       			createEvent.setEventId(oldEventId);
	       			
	       			ueDAO.createAutoIncrement(createEvent);
	       			
	       			return "showMyEvents.do";
	       		}*/
	       		
	       		return "showMyEvents.do";
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
