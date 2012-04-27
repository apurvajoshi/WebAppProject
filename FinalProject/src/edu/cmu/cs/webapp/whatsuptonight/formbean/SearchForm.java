package edu.cmu.cs.webapp.whatsuptonight.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SearchForm extends FormBean{
    private String searchStr;
    private String action;
      
    public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr.trim();
	}
	
	public String getAction()    { return action; }    
    public void setAction(String s)    { this.action   = s;        }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (searchStr == null || searchStr.length() == 0) errors.add("Search String is required");
        if (action == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!action.equals("Search Events")) errors.add("Invalid button");
        if (searchStr.matches(".*[<>\"].*")) errors.add("Search String may not contain angle brackets or quotes");
		
        return errors;
    }
}
