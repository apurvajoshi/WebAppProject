package edu.cmu.cs.webapp.whatsuptonight.databean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.genericdao.PrimaryKey;

@PrimaryKey("photoId")
public class Photo implements Comparable<Photo> {
	public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
			".jpg", ".gif", ".JPG"
	} ));

	private int    photoId          = -1;
	private int eventId;
	private byte[] bytes       = null;
	private String contentType = null;
	
	public int compareTo(Photo other) {
		return 0;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Photo) {
			Photo other = (Photo) obj;
			return compareTo(other) == 0;
		}
		return false;
	}
	
    public byte[] getBytes()       { return bytes;       }
    public String getContentType() { return contentType; }
    public int    getPhotoId()          { return photoId;          }
    public int    getEventId()          { return eventId;          }
    
    public void setBytes(byte[] a)        { bytes = a;        }
    public void setContentType(String s)  { contentType = s;  }
    public void setPhotoId(int x)              { photoId = x;           }
    public void setEventId(int x)              { eventId = x;           }
    
}
