package edu.cmu.cs.webapp.whatsuptonight.formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
        
        if (action == null) errors.add("Button is required");
        
        if(action.equals("Register"))
        	if (emailId == null || emailId.length() == 0) errors.add("Emaild Id is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (firstName == null || firstName.length() == 0) errors.add("First Name is required");
        if (lastName == null || lastName.length() == 0) errors.add("Last Name is required");
        if (city == null || city.length() == 0) errors.add("City is required");
              

        if (errors.size() > 0) return errors;

        if (!action.equals("Register") && !action.equals("Edit Profile")) errors.add("Invalid button");
        if(action.equals("Register")) {
        	if (emailId.matches(".*[<>\"].*")) errors.add("User Name may not contain angle brackets or quotes");
        	boolean isvalid = isValidEmailId(emailId);
        	if(isvalid == false)
        		errors.add("Enter Valid Email Id.");
        }
        if (firstName.matches(".*[<>\"].*")) errors.add("First Name may not contain angle brackets or quotes");
        if (lastName.matches(".*[<>\"].*")) errors.add("Last Name may not contain angle brackets or quotes");
        if (city.matches(".*[<>\"].*")) errors.add("City may not contain angle brackets or quotes");
		
        return errors;
    }
  
    	public boolean isValidEmailId(String emailAddress){  
    	   String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
    	   CharSequence inputStr = emailAddress;  
    	   Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
    	   Matcher matcher = pattern.matcher(inputStr);  
    	   return matcher.matches();  
    	  
    	 }  
    
    	public static boolean isValidEmailAddress(String email) {
    	   boolean result = true;
    	   try {
    	      InternetAddress emailAddr = new InternetAddress(email);
    	      emailAddr.validate();
    	   } catch (AddressException ex) {
    	      result = false;
    	   }
    	   return result;
    	}
}
