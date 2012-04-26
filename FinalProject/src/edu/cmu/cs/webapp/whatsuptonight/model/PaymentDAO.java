package edu.cmu.cs.webapp.whatsuptonight.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import edu.cmu.cs.webapp.whatsuptonight.databean.Payment;;


public class PaymentDAO extends GenericDAO<Payment> {
	
	public PaymentDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(Payment.class, tableName, connectionPool);
	}

}