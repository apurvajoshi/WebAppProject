package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.databean.Payment;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.PaymentDAO;


public class PaymentConfirmationAction extends Action {
	
	private PaymentDAO paymentDAO;

	public PaymentConfirmationAction(Model model) {
		paymentDAO = model.getPaymentDAO();
	}

	public String getName() { return "paymentConfirmation.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
         
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	/* Payment Successful */        	
	        User user = (User)session.getAttribute("user");

        	/* Save the details in the database */
	        Payment payment = new Payment();
	        payment.setIpAddress(request.getRemoteAddr());
	        payment.setDate(new Date());
	        payment.setEmailId(user.getEmailId());
        	
	        paymentDAO.createAutoIncrement(payment);
	        List<String> msgs = new ArrayList<String>();
	        msgs.add("Payment Successful!");
	        request.setAttribute("msgs",msgs);
	       
	        
	        /* Add tickets to the user event registration page */
	        
	        
	        
			return "showTickets.do";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "welcome.jsp";
        } 
    }
}