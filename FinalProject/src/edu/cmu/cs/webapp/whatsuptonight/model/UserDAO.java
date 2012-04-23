package edu.cmu.cs.webapp.whatsuptonight.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.cs.weapp.whatsuptonight.databean.User;

public class UserDAO extends GenericDAO<User> {
	public UserDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(User.class, tableName, connectionPool);
	}
	
	public User readByUserId(int id)
	{
		User[] user;
		try {
			user = match(MatchArg.equals("userId", id));
			return user[0];
		} catch (RollbackException e) {
			
		}
		return null;
	}
	
	public User readByEmailId(String emailId)
	{
		try {
			User[] user = match(MatchArg.equals("emailId", emailId));
			if(user.length > 0)
				return user[0];
			
		} catch (RollbackException e) {
			
		}
		return null;
	}

	public User[] searchByLastName(
			String searchString) {
		try {
			User[] users = match(MatchArg.equalsIgnoreCase("lastName", searchString));
			return users;
		} catch (RollbackException e) {
						
		} 
		return null;
	}
	
	public List<User> getAllUsers()
	{		
		try { 
			Class.forName("com.mysql.jdbc.Driver");					
			Connection conn = DriverManager.getConnection("jdbc:mysql:///webapp");
			
			PreparedStatement query_string = conn.prepareStatement("SELECT userId, description, fName, lName, password, place, timeZone, userName FROM sdevalok_user");
			ResultSet rs = query_string.executeQuery();
			
			List<User> users = new ArrayList<User>();
			while(rs.next())
			{
//				User user = new User();
//				user.setUserId(rs.getInt("userId"));
//				user.setDescription(rs.getString("description"));
//				user.setFirstName(rs.getString("firstName"));
//				user.setLastName(rs.getString("lastName"));
//				user.setUserName(rs.getString("userName"));
//				user.setPassword(rs.getString("password"));
//				user.setPlace(rs.getString("place"));
//				user.setTimeZone(rs.getString("timeZone"));
//				users.add(user);
			}
			
			rs.close();
			query_string.close();
			conn.close();
			return users;
		}
		catch(Exception e) {
			
		}
		return null;
	}
}
