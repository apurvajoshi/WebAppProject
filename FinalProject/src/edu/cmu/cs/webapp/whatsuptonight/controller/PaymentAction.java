package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import edu.cmu.cs.webapp.whatsuptonight.databean.Ticket;
import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.databean.UserEventRegistration;
import edu.cmu.cs.webapp.whatsuptonight.formbean.PaymentForm;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.TicketDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.UserEventRegistrationDAO;


public class PaymentAction extends Action {
private FormBeanFactory<PaymentForm> formBeanFactory = FormBeanFactory.getInstance(PaymentForm.class);
	
	private UserEventRegistrationDAO userEventRegistrationDAO;	
	private TicketDAO ticketDAO;

	public PaymentAction(Model model) {
		ticketDAO = model.getTicketDAO();
    	userEventRegistrationDAO = model.getUserEventRegistrationDAO();
	}

	public String getName() { return "payment.do"; }
    
	
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
                
    	// If eventId is not maintained in session then redirect to home page
        if (session.getAttribute("eventId") == null) {
        	return "home.do";
        }
        
        if (session.getAttribute("quantity") == null) {
        	return "home.do";
        }
        
        if (session.getAttribute("user") == null) {
        	return "home.do";
        }        
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	PaymentForm form = formBeanFactory.create(request);	        
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "getpaymentdetails.jsp";
	        }
	        
	        int eventId = Integer.parseInt(session.getAttribute("eventId").toString());
	        int quantity =  Integer.parseInt(session.getAttribute("quantity").toString());
	        User user = (User)session.getAttribute("user");
	        Ticket[] tickets = ticketDAO.match(MatchArg.equals("eventId", eventId));

	        
	        // set your secret key: remember to change this to your live secret key in production
	        // see your keys here https://manage.stripe.com/account
	       
	        /* Test key */
	        //Stripe.apiKey = "1b7vhX6Ml4yRJD5TZw719zVJcaZTfsDJ";	        
	        
	        /* Live Key */
	        Stripe.apiKey = "ZkARoB5du00PkYO80qVxUukEQlQQy0E5";
	        
	        
	        Transaction.begin();
	        Map<String, Object> chargeParams = new HashMap<String, Object>();
	        chargeParams.put("currency", "usd");
	        chargeParams.put("card", form.getStripeToken()); // obtained with Stripe.js
	        chargeParams.put("amount", quantity * tickets[0].getTicketPrice() * 100); // amount in cents
	        chargeParams.put("description", user.getEmailId());
	        Charge.create(chargeParams);
	        	      
	        int j = 0;
   	        /* Add ticket */
	        while( j < quantity )
	        {
	        	UserEventRegistration userEventRegistration = new UserEventRegistration();
	        	userEventRegistration.setUserId(user.getUserId());
	        	userEventRegistration.setTicketTypeId(tickets[0].getTicketTypeId());
	        	userEventRegistrationDAO.createAutoIncrement(userEventRegistration);
	        	j++;
	        }	        
	        
	        Transaction.commit();
	        
	        /* Set session to null*/
	        session.setAttribute("eventId", null);
	        session.setAttribute("quantity", null);
	        
			return "paymentConfirmation.do";
        } catch (NumberFormatException e) {
        	errors.add(e.getMessage());
        	return "getpaymentdetails.jsp";
        } catch (StripeException e) {
        	errors.add(e.getMessage());
        	System.out.println("ERRORS IN API ");
        	return "getpaymentdetails.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "getpaymentdetails.jsp";
        }  catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "getpaymentdetails.jsp";
        }  
    }
}
