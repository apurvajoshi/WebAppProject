package edu.cmu.cs.webapp.whatsuptonight.controller;

import javax.servlet.http.HttpServletRequest;

import com.google.gdata.client.*;
import com.google.gdata.data.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.cmu.cs.webapp.whatsuptonight.model.EventDAO;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;

public class GoogleCalendar extends Action {
	
	private EventDAO eventDAO;
		
	public GoogleCalendar(Model model) {
		
	}

	public String getName() { return "addCalendar.do"; }
    
    public String perform(HttpServletRequest request) {
    	GoogleService myService = new GoogleService("cl", "exampleCo-exampleApp-1");
    	try {
			myService.setUserCredentials("watzuptonight@gmail.com", "watzuptonight");
		} catch (AuthenticationException e1) {
			e1.printStackTrace();
		}

    	URL postUrl = null;
    	try {
			postUrl = new URL("http://www.google.com/calendar/feeds/sunilkumardm@gmail.com/private/full");
		} catch (MalformedURLException e) {
			
		}
    	EventEntry myEntry = new EventEntry();
    	
    	myEntry.setTitle(new PlainTextConstruct("Tennis with Darcy"));
    	myEntry.setContent(new PlainTextConstruct("Meet for a quick lesson."));
    	
    	Person author = new Person("watzuptonight", null, "watzuptonight@gmail.com");
    	myEntry.getAuthors().add(author);
    	
    	DateTime startTime = DateTime.parseDateTime("2012-04-27T15:00:00-08:00");
    	DateTime endTime = DateTime.parseDateTime("2012-04-77T17:00:00-08:00");
    	When eventTimes = new When();
    	eventTimes.setStartTime(startTime);
    	eventTimes.setEndTime(endTime);
    	myEntry.addTime(eventTimes);
    	
    	try {
			EventEntry insertedEntry = myService.insert(postUrl, myEntry);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;    	
    }
}
