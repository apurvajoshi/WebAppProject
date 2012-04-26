package edu.cmu.cs.webapp.whatsuptonight.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class PaymentForm extends FormBean {
    private String stripeToken;
    
    public String getStripeToken()  { return stripeToken; }
   
    public void setStripeToken(String s)  { this.stripeToken = s.trim(); }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        if (stripeToken == null || stripeToken.length() == 0) errors.add("Token id is missing");
        if (errors.size() > 0) return errors;		
        return errors;
    }
}
