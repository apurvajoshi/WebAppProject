package edu.cmu.cs.webapp.whatsuptonight.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserCategory;
import edu.cmu.cs.webapp.whatsuptonight.formbean.RegisterForm;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.UserCategoryDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserDAO;


public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
	
	private UserDAO userDAO;
	private UserCategoryDAO ucDAO;

	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
		ucDAO = model.getUserCategoryDAO();
	}

	public String getName() { return "register.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        // If user is already logged in, redirect to home.do
        if (session.getAttribute("user") != null) {
        	return "home.do";
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
	        
   			User newUser = new User();       			
   			newUser.setFirstName(form.getFirstName());
   			newUser.setLastName(form.getLastName());
   			newUser.setEmailId(form.getEmailId());
   			newUser.setPassword(form.getPassword());
   			newUser.setCity(form.getCity());    			
   			
       		if(userDAO.readByEmailId(form.getEmailId()) != null)
       		{
       			errors.add("A user with this email id already exists");
        	    return "register.jsp";	
       		}
   				
       		int userId = userDAO.insertUser(newUser);
       		
       		HashMap< String, Integer> hashMap = new HashMap<String, Integer>();  
       		String[] selCat = request.getParameterValues("category");
       		if (selCat != null && selCat.length != 0) {
       			for(int j=0; j<selCat.length; j++) {
       				UserCategory cat = new UserCategory();
       				cat.setUserId(userId);
       				cat.setCategoryName(selCat[j]);
       				ucDAO.createAutoIncrement(cat);       				
       				hashMap.put(cat.getCategoryName(), 1);
       			}       			
       		}	
       		
       		session.setAttribute("user", newUser);
       		session.setAttribute("userCategoryList", hashMap);
       			
       		return "home.do";	        
        } catch (RollbackException e) {        	
        	errors.add(e.getMessage());
        	return "register.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        }
    }
}
