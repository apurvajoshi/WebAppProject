package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventRegistration;
import edu.cmu.cs.webapp.whatsuptonight.formbean.EventForm;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.PhotoDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.TicketDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventCreationDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventRegistrationDAO;

public class UpdateEventAction extends Action{
private FormBeanFactory<EventForm> formBeanFactory = FormBeanFactory.getInstance(EventForm.class);
	
	private EventDAO eventDAO;
	private TicketDAO ticketDAO;
	private PhotoDAO photoDAO;
	private UserEventRegistrationDAO userEventRegDAO;
	private UserDAO userDAO;

	public UpdateEventAction(Model model) {
		eventDAO = model.getEventDAO();
		ticketDAO = model.getTicketDAO();
		photoDAO = model.getPhotoDAO();
		userDAO = model.getUserDAO();
		userEventRegDAO = model.getUserEventRegistrationDAO();
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
	       		sendEmailToRegisteredUsers(oldEvent, oldTicketId, errors);
	       		return "showMyEvents.do";
       		} 
	        
	        return "event.jsp";
        } catch (RollbackException e) {        	
        	errors.add(e.getMessage());
        	return "event.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "event.jsp";
        }
    }
    
    public void sendEmailToRegisteredUsers(Event event, int ticketTypeId, List<String> errors)
    {
    	System.out.println("Ticket type id " + ticketTypeId);
    	try {
			UserEventRegistration[] userEventsReg = userEventRegDAO.getTicketsByTicketTypeId(ticketTypeId);
        	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

			for(UserEventRegistration userEventReg : userEventsReg)
			{	
				if(map.get(userEventReg.getUserId()) == null)
				{
					int userId = userEventReg.getUserId();
					//Send email
					User user = userDAO.getUserByUserId(userId);
					System.out.println("Email id : " + user.getEmailId() );
					MailSender mailSender=new MailSender(user.getEmailId(),"Your registered event '" + event.getTitle() +"' got updated", createEmailContent(event));
					map.put(userEventReg.getUserId(), 1);
				}
			}
		} catch (RollbackException e) {
			errors.add(e.getMessage());
		}
    }
    
    public String createEmailContent(Event event)
    {
    	String ret;
    	ret = "Event Title : " + event.getTitle() + "\n" +
    		  "Description : " + event.getDescription() + "\n" +
    		  "Location : " + event.getLocation() + "\n" +
    		  "City : " + event.getCity() + "\n" +
    		  "Start date : " + event.getStartDate() + "\n" +
    		  "End date : " + event.getEndDate() + "\n" + 
    		  "Host : " + event.getOrganization() + "\n";
    	
    	return ret;
    }
    
    
}
