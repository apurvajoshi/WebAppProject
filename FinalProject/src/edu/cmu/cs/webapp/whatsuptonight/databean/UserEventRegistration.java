package edu.cmu.cs.webapp.whatsuptonight.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("ticketId")
public class UserEventRegistration {
	private int ticketId;
	private int userId;
	private int ticketTypeId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
}
