package edu.cmu.cs.webapp.whatsuptonight.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("paymentId")
public class Payment {
	private int paymentId;
	private String emailId;
	private String ipAddress;
    private Date date; 

	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
