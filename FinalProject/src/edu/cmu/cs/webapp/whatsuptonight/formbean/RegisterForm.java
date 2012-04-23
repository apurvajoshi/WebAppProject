package edu.cmu.cs.webapp.whatsuptonight.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean{
	
	private String firstName;
    private String lastName;
	private String action;
	private String emailId;
    private String password;
    private String city;
    	
    public String getEmailId()  { return emailId; }
    public String getPassword()  { return password; }
    public String getAction()    { return action; }
    public String getFirstName() 	 { return firstName; }
    public String getLastName()	 { return lastName; }
    public String getCity()	 { return city; }
    
    public void setEmailId(String s)  { this.emailId = s.trim(); }
    public void setPassword(String s)  { this.password = s.trim(); }
    public void setAction(String s)    { this.action   = s;        }
    public void setFirstName(String s)	   { this.firstName 	  = s.trim(); }
    public void setLastName(String s)	   { this.lastName 	  = s.trim(); }
    public void setCity(String s)	   { this.city 	  = s.trim(); }

    public List<String> getRegisterValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (emailId == null || emailId.length() == 0) errors.add("Emaild Id is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (firstName == null || firstName.length() == 0) errors.add("First Name is required");
        if (lastName == null || lastName.length() == 0) errors.add("Last Name is required");
        if (city == null || city.length() == 0) errors.add("City is required");
        
        if (action == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!action.equals("Register")) errors.add("Invalid button");
        if (emailId.matches(".*[<>\"].*")) errors.add("User Name may not contain angle brackets or quotes");
        if (firstName.matches(".*[<>\"].*")) errors.add("First Name may not contain angle brackets or quotes");
        if (lastName.matches(".*[<>\"].*")) errors.add("Last Name may not contain angle brackets or quotes");
        if (city.matches(".*[<>\"].*")) errors.add("City may not contain angle brackets or quotes");
		
        return errors;
    }
}
