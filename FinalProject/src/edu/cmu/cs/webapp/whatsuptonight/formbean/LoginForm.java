package edu.cmu.cs.webapp.whatsuptonight.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean{
    private String emailId;
    private String password;
    private String action;
	
    public String getEmailId()  { return emailId; }
    public String getPassword()  { return password; }
    public String getAction()    { return action; }
	
    public void setEmailId(String s)  { this.emailId = s.trim(); }
    public void setPassword(String s)  { this.password = s.trim(); }
    public void setAction(String s)    { this.action   = s;        }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (emailId == null || emailId.length() == 0) errors.add("Email Id is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (action == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!action.equals("Login")) errors.add("Invalid button");
        if (emailId.matches(".*[<>\"].*")) errors.add("User Name may not contain angle brackets or quotes");
		
        return errors;
    }
}
