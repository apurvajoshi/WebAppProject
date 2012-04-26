package edu.cmu.cs.webapp.whatsuptonight.databean;

import java.util.Date;

public class MyTickets {
	private int eventId;
	private String title;
	private Date date;
	private int ticketQty;
	private int amount;
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTicketQty() {
		return ticketQty;
	}
	public void setTicketQty(int ticketQty) {
		this.ticketQty = ticketQty;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
