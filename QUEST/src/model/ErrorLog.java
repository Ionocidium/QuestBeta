package model;

/**
 * Handles the encapsulation and representation of <code>ErrorLog</code> model.
 * 
 * @author Darren Garcia
 */

public class ErrorLog {

	private String elog;
	
	/**
	 * Creates a blank representation for <code>ErrorLog</code> model.
	 */
	public ErrorLog() {
		
	}
	
	/**
	 * Creates a representation that contains the information for <code>ErrorLog</code> model.
	 * @param elog Error Log
	 */
	public ErrorLog(String elog) {
		this.elog = elog;
	}

	public String getErrorLog() {
		return elog;
	}

	public void setErrorLog(String elog) {
		this.elog = elog;
	}
}