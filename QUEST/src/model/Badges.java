package model;

import javax.swing.JButton;

/**
 * Handles the encapsulation and representation of <code>Badges</code> model.
 * 
 * @author Ramon Arca
 * @author Darren Garcia
 */

public class Badges {
	
	private int badgeNum;
	private String badgeTitle = "";
	private String badgeDisc = "";
	private int badgeType;
	private String badgeIcon = "";
	private JButton badgeBtn;
	private boolean isAcquired = false;
	
	/**
	 * Creates a blank representation for <code>Badges</code> model.
	 */
	public Badges() {
		
	}
	
	/**
	 * Creates a representation that contains the information for <code>Badges</code> model.
	 * @param badgeNum Badge Number
	 * @param badgeTitle Badge Title
	 * @param badgeDisc Badge Description
	 * @param badgeType Badge Type
	 * @param badgeIcon Badge Icon
	 * @param badgeBtn Badge Button
	 * @param isAcquired Badge Acquired
	 */
	public Badges(int badgeNum, String badgeTitle, String badgeDisc, int badgeType, String badgeIcon, JButton badgeBtn, boolean isAcquired) {
		this.badgeNum = badgeNum;
		this.badgeTitle = badgeTitle;
		this.badgeDisc = badgeDisc;
		this.badgeType = badgeType;
		this.badgeIcon = badgeIcon;
		this.badgeBtn = badgeBtn;
		this.isAcquired = isAcquired;
	}
	
	public int getBadgeNum() {
		return badgeNum;
	}
	public void setBadgeNum(int badgeNum) {
		this.badgeNum = badgeNum;
	}
	public String getBadgeTitle() {
		return badgeTitle;
	}
	public void setBadgeTitle(String badgeTitle) {
		this.badgeTitle = badgeTitle;
	}
	public String getBadgeDisc() {
		return badgeDisc;
	}
	public void setBadgeDisc(String badgeDisc) {
		this.badgeDisc = badgeDisc;
	}
	public int getBadgeType() {
		return badgeType;
	}
	public void setBadgeType(int badgeType) {
		this.badgeType = badgeType;
	}
	public String getBadgeIcon() {
		return badgeIcon;
	}
	public void setBadgeIcon(String badgeIcon) {
		this.badgeIcon = badgeIcon;
	}
	public JButton getBadgeBtn() {
		return badgeBtn;
	}
	public void setBadgeBtn(JButton badgeBtn) {
		this.badgeBtn = badgeBtn;
	}
	public boolean isAcquired() {
		return isAcquired;
	}
	public void setAcquired(boolean isAcquired) {
		this.isAcquired = isAcquired;
	}

}
