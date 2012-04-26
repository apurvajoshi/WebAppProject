package edu.cmu.cs.webapp.whatsuptonight.formbean;


import org.mybeans.form.FormBean;

public class BuyTicketForm extends FormBean {
    private int quantity;
    
    public int getQuantity()  { return quantity; }
   
    public void setQuantity(int quantity)  { this.quantity = quantity; }

}
