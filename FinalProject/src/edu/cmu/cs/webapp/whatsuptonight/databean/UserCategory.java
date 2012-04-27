package edu.cmu.cs.webapp.whatsuptonight.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("ucId")
public class UserCategory {
	private int ucId;
	private int userId;
	private int categoryName;
	
	public int getUcId() {
		return ucId;
	}
	public void setUcId(int ucId) {
		this.ucId = ucId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(int categoryName) {
		this.categoryName = categoryName;
	}
}
