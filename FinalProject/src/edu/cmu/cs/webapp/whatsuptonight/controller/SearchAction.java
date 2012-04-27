package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.whatsuptonight.databean.Event;
import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.formbean.LoginForm;
import edu.cmu.cs.webapp.whatsuptonight.formbean.SearchForm;
import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;

public class SearchAction extends Action{
	private FormBeanFactory<SearchForm> formBeanFactory = FormBeanFactory.getInstance(SearchForm.class);
	private EventDAO eventDAO;
		
	public SearchAction(Model model) {
		eventDAO = model.getEventDAO();
	}

	public String getName() { return "search.do"; }
    
    public String perform(HttpServletRequest request) {
    	
    	List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);            
        
        try {
	    	SearchForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "index.jsp";
	        }

	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "home.jsp";
	        }
	        	        
       		if(form.getAction().equals("Search Events")) {
	     
       			Event[] eventsList = null;
       	        eventsList = eventDAO.getEventsOnSearch(form.getSearchStr());
       	        
       	        request.setAttribute("eventsList", eventsList);
       	       
       			return "home.jsp";
       		}
	        return "index.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "home.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "home.jsp";
		}               
    }
}
