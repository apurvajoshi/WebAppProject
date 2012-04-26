package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import edu.cmu.cs.webapp.whatsuptonight.formbean.PaymentForm;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;


public class PaymentAction extends Action {
private FormBeanFactory<PaymentForm> formBeanFactory = FormBeanFactory.getInstance(PaymentForm.class);
	
	public PaymentAction(Model model) {
		
	}

	public String getName() { return "payment.do"; }
    
	
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	PaymentForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
	        // set your secret key: remember to change this to your live secret key in production
	        // see your keys here https://manage.stripe.com/account
	        Stripe.apiKey = "1b7vhX6Ml4yRJD5TZw719zVJcaZTfsDJ";
	        
	        Map<String, Object> chargeParams = new HashMap<String, Object>();
	        chargeParams.put("currency", "usd");
	        chargeParams.put("card", form.getStripeToken()); // obtained with Stripe.js
	        chargeParams.put("amount", 400); // amount in cents
	        chargeParams.put("description", "Charge for apurvishere@gmail.com");
	        Charge.create(chargeParams);
	        	        
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "getpaymentdetails.jsp";
	        }
       		
			return "payment.jsp";
        } catch (StripeException e) {
        	errors.add(e.getMessage());
        	System.out.println("ERRORS IN API ");
        	return "getpaymentdetails.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "getpaymentdetails.jsp";
        }
    }
}
