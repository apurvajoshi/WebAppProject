package edu.cmu.cs.webapp.whatsuptonight.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("createId")
public class UserEventCreation {
	private int createId;
	private int userId;
	private int eventId;
	public int getCreateId() {
		return createId;
	}
	public void setCreateId(int createId) {
		this.createId = createId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}	
}
