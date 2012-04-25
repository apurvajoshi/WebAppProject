package edu.cmu.cs.webapp.whatsuptonight.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.formbean.RegisterForm;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.UserDAO;


public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
	
	private UserDAO userDAO;

	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "register.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
            	
        if (session.getAttribute("user") != null) {
        	return "home.jsp";
        }
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "register.jsp";
	        }

	        errors.addAll(form.getRegisterValidationErrors());
	                
	        if (errors.size() != 0) {
	            return "register.jsp";
	        }
	        
       		if (form.getAction().equals("Register")) {
       		
       			User newUser = new User();       			
       			newUser.setFirstName(form.getFirstName());
       			newUser.setLastName(form.getLastName());
       			newUser.setEmailId(form.getEmailId());
       			newUser.setPassword(form.getPassword());
       			newUser.setCity(form.getCity());    			
       			
       			try {
       				/* TO DO - check for duplicate email Id here */
       				
	       			userDAO.createAutoIncrement(newUser);	       			
	       			session.setAttribute("user", newUser);
	       			
	       			return "home.jsp";
       	        } catch (DuplicateKeyException e) {
       	        	errors.add("A user with this name already exists");
       	        	return "register.jsp";
       			} 
       		} 
	        
	        return "register.jsp";
        } catch (RollbackException e) {        	
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
