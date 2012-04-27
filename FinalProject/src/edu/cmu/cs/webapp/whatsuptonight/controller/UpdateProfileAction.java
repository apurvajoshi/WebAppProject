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

public class UpdateProfileAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
	
	private UserDAO userDAO;
	private UserCategoryDAO ucDAO;

	public UpdateProfileAction(Model model) {
		userDAO = model.getUserDAO();
		ucDAO = model.getUserCategoryDAO();
	}

	public String getName() { return "updateProfile.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
                
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "editprofile.jsp";
	        }

	        errors.addAll(form.getRegisterValidationErrors());
	                
	        if (errors.size() != 0) {
	            return "editprofile.jsp";
	        }
	        
	        User sesUser = (User)session.getAttribute("user");
	        
   			User oldUser = new User();       			
   			oldUser.setUserId(sesUser.getUserId());
   			oldUser.setFirstName(form.getFirstName());
   			oldUser.setLastName(form.getLastName());
   			oldUser.setEmailId(sesUser.getEmailId());
   			oldUser.setPassword(form.getPassword());
   			oldUser.setCity(form.getCity());    			
   					
   				
       		userDAO.update(oldUser);
       		
       		ucDAO.deleteAllCategory(sesUser.getUserId());
       		       		
       		HashMap< String, Integer> hashMap = new HashMap<String, Integer>();  
       		String[] selCat = request.getParameterValues("category");
       		if (selCat != null && selCat.length != 0) {
       			for(int j=0; j<selCat.length; j++) {
       				UserCategory cat = new UserCategory();
       				cat.setUserId(sesUser.getUserId());
       				cat.setCategoryName(selCat[j]);
       				ucDAO.createAutoIncrement(cat);       				
       				hashMap.put(cat.getCategoryName(), 1);
       			}       			
       		}	
       		
       		session.setAttribute("user", oldUser);
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
