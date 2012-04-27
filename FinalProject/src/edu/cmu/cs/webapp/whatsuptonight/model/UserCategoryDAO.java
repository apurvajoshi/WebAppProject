package edu.cmu.cs.webapp.whatsuptonight.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.whatsuptonight.databean.UserCategory;

public class UserCategoryDAO extends GenericDAO<UserCategory> {
	public UserCategoryDAO(String tableName, ConnectionPool connectionPool) throws DAOException {
		super(UserCategory.class, tableName, connectionPool);
	}
	
	public UserCategory[] getCategoryByUserId(int userId) throws RollbackException {
		UserCategory[] uc = match(MatchArg.equals("userId", userId));
		if(uc.length > 0)
			return uc;
		return null;
	}

	public void deleteAllCategory(int userId) throws RollbackException {
		UserCategory[] uc = match(MatchArg.equals("userId", userId));	
		
		if(uc.length > 0) {
			for(int i=0; i<uc.length; i++) {
				delete(uc[i].getUcId());
			}
		}		
	}
}
