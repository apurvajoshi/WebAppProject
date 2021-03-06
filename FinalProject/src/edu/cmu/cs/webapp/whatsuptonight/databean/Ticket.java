package edu.cmu.cs.webapp.whatsuptonight.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("ticketTypeId")
public class Ticket {
	private int ticketTypeId;
	private String ticketName;
	private int ticketQty;
	private int ticketPrice;
	private int eventId;
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public int getTicketQty() {
		return ticketQty;
	}
	public void setTicketQty(int ticketQty) {
		this.ticketQty = ticketQty;
	}
	public int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	
}
