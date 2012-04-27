package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.cmu.cs.webapp.whatsuptonight.databean.User;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
        Model model = new Model(getServletConfig());
        
        Action.add(new RegisterAction(model));
        Action.add(new LoginAction(model));
        Action.add(new CreateEventAction(model));
        Action.add(new PaymentAction(model));
        Action.add(new UserEventRegistrationAction(model));
        Action.add(new PaymentConfirmationAction(model));
        Action.add(new HomeAction(model));
        Action.add(new GetCardDetailsAction(model));
        Action.add(new ShowMyEventsAction(model));
        Action.add(new DisplayEventAction(model));
        Action.add(new UpdateEventAction(model));
        Action.add(new ImageAction(model));
        Action.add(new ShowMyTicketsAction(model));
        Action.add(new UpdateProfileAction(model));
        Action.add(new LogOutAction(model));
        Action.add(new SearchAction(model));
        Action.add(new GoogleCalendar(model));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        User        user = (User) session.getAttribute("user");
        String      action = getActionName(servletPath);
               
        System.out.println("action is "+action);
        
        if(action.equals("addCalendar.do")) {
        	return Action.perform("addCalendar.do", request);
        }
        
        if (user == null && action.equals("register.do")) {
			return Action.perform("register.do",request);
        }
        
        if (user == null) {
        	// If the user hasn't logged in, so login is the only option
			return Action.perform("login.do",request);
        }
        
        if (action.equals("welcome")) {
        	// User is logged in, but at the root of our web app
			return Action.perform("home.do",request);
        }
        
      	// Let the logged in user run his chosen action
		return Action.perform(action,request);
		
    }
    
    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.equals("showMyTickets.do")) {
    		RequestDispatcher d = request.getRequestDispatcher(nextPage);
	   		d.forward(request,response);
			return;
    	}
    	
    	if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
    	}
    	
    	if (nextPage.endsWith(".jsp") || nextPage.equals("image")) {
	   		RequestDispatcher d = request.getRequestDispatcher(nextPage);
	   		d.forward(request,response);
	   		return;
    	}

    	throw new ServletException(Controller.class.getName()+".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
