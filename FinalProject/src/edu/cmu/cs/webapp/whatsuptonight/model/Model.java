package edu.cmu.cs.webapp.whatsuptonight.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	
	private UserDAO userDAO;
//	private AddFriendDAO addFriendDAO;
//	private PhotoDAO photoDAO;
//	private StoryDAO storyDAO;
//	private CommentDAO commentDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
						
			userDAO  = new UserDAO("users", pool);			
//			addFriendDAO = new AddFriendDAO("sdevalok_friends", pool);			
//			photoDAO = new PhotoDAO("sdevalok_photos", pool);			
//			storyDAO = new StoryDAO("sdevalok_story", pool);			
//			commentDAO = new CommentDAO("sdevalok_comments", pool);
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
		
	public UserDAO getUserDAO()  { return userDAO; }
//	public AddFriendDAO getAddFriendDAO()  { return addFriendDAO; }
//	public PhotoDAO getPhotoDAO() { return photoDAO;	}
//	public StoryDAO getStoryDAO() { return storyDAO;	}
//	public CommentDAO getCommentDAO() { return commentDAO;	}
}