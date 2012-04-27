package edu.cmu.cs.webapp.whatsuptonight.formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;

public class EventForm extends FormBean{
	
	private String action;
	private String title;
	private String desc;
	private FileProperty file = null;
	private String location;
	private String city;	
	private String category;
	private String host;

	private String startDate; 
	private String startHour;
	private String startMins;
	private String endDate; 
	private String endHour;
	private String endMins;
		
	private String ticketName;
	private String ticketQty;
	private String ticketPrice;

	public FileProperty getFile()           { return file;           }
	public void setFile(FileProperty file)  { this.file   = file;     }
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMins() {
		return startMins;
	}
	public void setStartMins(String startMins) {
		this.startMins = startMins;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endtHour) {
		this.endHour = endtHour;
	}
	public String getEndMins() {
		return endMins;
	}
	public void setEndMins(String endMins) {
		this.endMins = endMins;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public String getTicketQty() {
		return ticketQty;
	}
	public void setTicketQty(String ticketQty) {
		this.ticketQty = ticketQty;
	}
	public String getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title.trim();
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc.trim();
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location.trim();
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city.trim();
	}
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host.trim();
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (title == null || title.length() == 0) errors.add("Title is required");
        if (desc == null || desc.length() == 0) errors.add("Desc is required");
        if (location == null || location.length() == 0) errors.add("Location is required");
        if (city == null || city.length() == 0) errors.add("City is required");        
        if (startDate == null || startDate.toString().length() == 0) errors.add("Start Date is required");
        if (endDate == null || endDate.toString().length() == 0) errors.add("End Date is required");
        if (host == null || host.length() == 0) errors.add("Host is required");
        if (ticketName == null || ticketName.length() == 0) errors.add("Ticket Name is required");
        if (ticketQty == null || ticketQty.length() == 0) errors.add("Ticket Quantity is required");
        if (ticketPrice == null || ticketPrice.length() == 0) errors.add("Ticket Price is required");
        
        int istartHour = -1, istartMins = -1;
        int iendHour = -1, iendMins = -1;
        try {
        	istartHour = Integer.parseInt(startHour);  
        	istartMins = Integer.parseInt(startMins);
        } catch (IllegalArgumentException e) {
        	errors.add("Proper Start time is required.");
        }
        
        try {
        	iendHour = Integer.parseInt(endHour);  
        	iendMins = Integer.parseInt(endMins);
        } catch (IllegalArgumentException e) {
        	errors.add("Proper End time is required.");
        }
        
        if((istartHour < 0) && (istartHour >= 24))
        	errors.add("Proper Start time is required.");
        if((istartMins < 0) && (istartMins > 59))
        	errors.add("Proper Start time is required.");
        
        if((iendHour < 0) && (iendHour >= 24))
        	errors.add("Proper End time is required.");
        if((iendMins < 0) && (iendMins > 59))
        	errors.add("Proper End time is required.");
        
        try {
        	int qty = Integer.parseInt(ticketQty);
        } catch(NumberFormatException e) {
        	errors.add("Ticket Quantity should be a number.");
        }

        try {
        	int amt = Integer.parseInt(ticketPrice);
        } catch(NumberFormatException e) {
        	errors.add("Ticket Price should be a number.");
        }
        
        if (action == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!action.equals("Create Event") && !action.equals("Save Event")) errors.add("Invalid button");
        if (title.matches(".*[<>\"].*")) errors.add("Title may not contain angle brackets or quotes");
        if (desc.matches(".*[<>\"].*")) errors.add("Description may not contain angle brackets or quotes");
        if (location.matches(".*[<>\"].*")) errors.add("Location may not contain angle brackets or quotes");
        if (city.matches(".*[<>\"].*")) errors.add("City may not contain angle brackets or quotes");
        
        if (host.matches(".*[<>\"].*")) errors.add("Host may not contain angle brackets or quotes");
        if (ticketName.matches(".*[<>\"].*")) errors.add("Ticket Name may not contain angle brackets or quotes");
        if (ticketQty.matches(".*[<>\"].*")) errors.add("Ticket Quantity may not contain angle brackets or quotes");
        if (ticketPrice.matches(".*[<>\"].*")) errors.add("Ticket Price may not contain angle brackets or quotes");
        
        try {
        Date end = new Date(endDate);
        Date start = new Date(startDate);
        
        if(end.before(start))
        	errors.add("Event End Date should be after Event Start Date.");
        
        } catch (IllegalArgumentException e) {
        	errors.add("Date should be in MM/DD/YYYY format.");
        }
        
        String[] categories = {"Sports", "Business", "Entertainment" , "Food"};
        int flag = 0;
        for(int x=0; x<categories.length; x++) {
        	if(categories[x].equals(category)) {
        		flag = 1;
        		break;
        	}
        }
        if(flag == 0)
        	errors.add("Category is required.");
        	
        if(action.equals("Create event")) {
	        if (file == null || file.getFileName().length() == 0) {
				errors.add("You must provide a file");
				return errors;
			}
	
			if (file.getBytes().length == 0) {
				errors.add("Zero length file");
			}
			
			if (file.getBytes().length > 102400) {
				errors.add("Image file size should be less than 100KB.");
			}
        }
        
        return errors;
    }
	
}
