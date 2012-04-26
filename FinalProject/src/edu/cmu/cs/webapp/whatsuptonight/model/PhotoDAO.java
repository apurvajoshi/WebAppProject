package edu.cmu.cs.webapp.whatsuptonight.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import edu.cmu.cs.webapp.whatsuptonight.databean.Photo;

public class PhotoDAO extends GenericDAO<Photo> {
	public PhotoDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Photo.class, tableName, pool);
	}

	public void create(Photo newPhoto) throws RollbackException {
		try {
			Transaction.begin();
			Photo ph = getPhotos(newPhoto.getEventId());
			if(ph == null)
			{
				createAutoIncrement(newPhoto);
			}
			else
			{
				Photo[] all = match(MatchArg.equals("eventId", newPhoto.getEventId()));
				for(int i=0; i<all.length; i++)
				{
					System.out.println("PhotoId:" + all[i].getPhotoId());
					delete(all[i].getPhotoId());
				}
				createAutoIncrement(newPhoto);
			}
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public Photo getPhotos(int eventId) throws RollbackException {
		Photo[] photo = match(MatchArg.equals("eventId", eventId));
		if(photo.length > 0)
			return photo[0];
		return null;
	}
}

