package model;

import java.io.IOException;



/**
 * Handles the encapsulation and representation of <code>Database</code> model.
 * 
 * @author Darren Garcia
 */

public class Database {

	private String ip;
	private String dbu;
	private String dbp;
	
	/**
	 * Creates a blank representation for <code>Database</code> model.
	 */
	public Database() {
		
	}
	
	/**
	 * Creates a representation that contains the information for <code>Database</code> model.
	 * @param ip IP Address
	 * @param dbu Database Username
	 * @param dbp Database Password
	 */
	public Database(String ip, String dbu, String dbp) {
		this.ip = ip;
		this.dbu = dbu;
		this.dbp = dbp;
	}

	public String getIP() {
		return ip;
	}

	public void setIP(String ip) {
		this.ip = ip;
	}
	
	public String getDbu() {
		return dbu;
	}

	public void setDbu(String dbu) {
		this.dbu = dbu;
	}
	
	public String getDbp() {
		return dbp;
	}

	public void setDbp(String dbp) {
		this.dbp = dbp;
	}
}
