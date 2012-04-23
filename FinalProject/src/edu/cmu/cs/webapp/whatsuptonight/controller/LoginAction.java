package edu.cmu.cs.webapp.whatsuptonight.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.weapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.formbean.LoginForm;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.UserDAO;

public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private UserDAO userDAO;
		
	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
    	
        HttpSession session = request.getSession();
            	
    	if (session.getAttribute("user") != null) {
        	return "home.jsp";
        }
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);            
        
        try {
	    	LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "welcome.jsp";
	        }

	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "index.jsp";
	        }
	        	        
       		if(form.getAction().equals("Login")) {
	           	User user = userDAO.readByEmailId(form.getEmailId());
	                
	        	if (user == null) {
	        		errors.add("User Name not found");
	        		return "index.jsp";
	        	}

	        	if (!user.getPassword().equals(form.getPassword())) {
	        		errors.add("Incorrect password");
	        		return "index.jsp";
	        	}
	
	        	session.setAttribute("user",user);
	        		        	
	        	return "home.jsp";
       		}
	        return "index.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
