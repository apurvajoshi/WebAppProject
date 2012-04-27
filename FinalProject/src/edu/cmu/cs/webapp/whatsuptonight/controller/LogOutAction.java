package edu.cmu.cs.webapp.whatsuptonight.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.webapp.whatsuptonight.model.Model;


public class LogOutAction extends Action {
	public LogOutAction(Model model) { }

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user",null);

        return "login.do";
    }
}
