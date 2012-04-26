package edu.cmu.cs.webapp.whatsuptonight.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.whatsuptonight.databean.Photo;
import edu.cmu.cs.webapp.whatsuptonight.model.Model;
import edu.cmu.cs.webapp.whatsuptonight.model.PhotoDAO;

/**
 * This action looks up the photo bean by "id" and then passes it
 * (via request attribute) to the ImageServlet.  See also the mapping
 * of /image in the web.xml file.
 * 
 * We need to use a servlet instead of a JSP for the "view" of the image
 * because we need to send back the image bytes and not HTML.
 */
public class ImageAction extends Action {
	
	private PhotoDAO photoDAO;

    public ImageAction(Model model) {
    	photoDAO = model.getPhotoDAO();
	}

    public String getName() { return "image.do"; }

    public String perform(HttpServletRequest request) {
    	    	
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	
        	int id = Integer.parseInt(request.getParameter("eventId").toString());
        	Photo p = photoDAO.getPhotos(id);
        	if(p == null)
        	{
        		//ServletContext context = request.getServletContext();
        		//String path = context.getRealPath("");
        		//FileInputStream fis = null;
        		//System.out.println(path+"\\photo1.jpg");
        		//File pic = new File(path+"\\photo1.jpg");
        		//try {
				//	fis = new FileInputStream(pic);
				//} catch (FileNotFoundException e) {
				//	System.out.println(e.getMessage());
				//	e.printStackTrace();
				//	System.out.println("file not found.");
				//}
        		//Photo image = new Photo();
        		//image.setUserId(id);
        		//try {
				//	image.setBytes(IOUtils.toByteArray((InputStream)fis));
				//} catch (IOException e) {
				//	
				//}
        		//request.setAttribute("photo", image);
        		//return "image";
        	}
    		if (p != null) request.setAttribute("photo",p);
    		
    		// Note: "/image" is mapped (in the web.xml file) to the ImageServlet
    		// which looks at the "photo" request attribute and sends back the bytes.
    		// If there is no "photo" attribute, it sends back HTTP Error 404 (resource not found).
    		return "image";
    	} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "home.jsp";
    	}
    }
}
