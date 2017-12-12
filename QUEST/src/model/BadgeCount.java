package model;

import java.io.IOException;

/**
 * Handles the encapsulation and representation of <code>BadgeCount</code> model.
 * 
 * @author Darren Garcia
 */

public class BadgeCount {

	private int usernumber;
	private String username;
	private int achievementcount;
	
	/**
	 * Creates a blank representation for <code>BadgeCount</code> model.
	 */
	public BadgeCount() {
		
	}
	
	/**
	 * Creates a representation that contains the information for <code>Badgecount</code> model.
	 * @param usernumber User Number
	 * @param username Username
	 * @param achievementcount Achievement Count
	 */
	public BadgeCount(int usernumber, String username, int achievementcount) {
		this.usernumber = usernumber;
		this.username = username;
		this.achievementcount = achievementcount;
	}

	public int getUserNumber() {
		return usernumber;
	}

	public void setUserNumber(int usernumber) {
		this.usernumber = usernumber;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getAchievementCount() {
		return achievementcount;
	}

	public void setAchievementCount(int achievementcount) {
		this.achievementcount = achievementcount;
	}
}
