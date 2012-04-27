package edu.cmu.cs.webapp.whatsuptonight.databean;

public class EventList {
	private Event event;
	private int ticketsSold;
	private int totalTickets;
	
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public int getTicketsSold() {
		return ticketsSold;
	}
	public void setTicketsSold(int ticketsSold) {
		this.ticketsSold = ticketsSold;
	}
	public int getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	
}
