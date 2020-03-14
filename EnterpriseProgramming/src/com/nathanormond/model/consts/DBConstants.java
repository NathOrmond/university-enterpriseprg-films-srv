package com.nathanormond.model.consts;
/**
 * 
 * @author Nathan Ormond
 *
 */
public final class DBConstants {

	/**
	 * 
	 * 
	 * 
	 * BEFORE PROCEEDING PLEASE ANSWER BELOW SECURITY QUESTION...
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Security Question : 
	 * 
	 * Are you a hacker? 
	 * 
	 * If you are a hacker there is nothing to see here, please close this file.
	 * 
	 * If not go ahead! :)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * IM WARNING YOU
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * STOP!
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * LAST CHANCE .....
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * My Secrets :
	 * 
	 * 
	 * 
	 */
	
	/**
	 * AWS_DB
	 */
	private static final String AWS_DB_NETWORK_ADDRESS = "epdbinstance.caopszb6zahi.us-east-2.rds.amazonaws.com";
	private static final int AWS_DB_PORT_NUMBER = 6603;
	private static final String AWS_DB_PASSWORD = "passw0rd";
	private static final String AWS_DB_USERNAME = "master";
	private static final String AWS_DB_NAME = "epdbinstance";

	/**
	 * GETTERS
	 */
	
	public static String getAwsDbNetworkAddress() {
		return AWS_DB_NETWORK_ADDRESS;
	}

	public static int getAwsDbPortNumber() {
		return AWS_DB_PORT_NUMBER;
	}

	public static String getAwsDbPassword() {
		return AWS_DB_PASSWORD;
	}

	public static String getAwsDbUsername() {
		return AWS_DB_USERNAME;
	}

	public static String getAwsDbName() {
		return AWS_DB_NAME;
	}

	/**
	 * MudFoot MySQL
	 */
	private static final String FILM_DB_NETWORK_ADDRESS = "mudfoot.doc.stu.mmu.ac.uk";
	private static final int FILM_DB_PORT_NUMBER = 6306;
	private static final String FILM_DB_PASSWORD = "F3houwtle";
	private static final String FILM_DB_USERNAME = "ormondn";
	private static final String FILM_DB_NAME = "ormondn";

	/**
	 * GETTERS
	 */
	
	public static String getFilmDbNetworkAddress() {
		return FILM_DB_NETWORK_ADDRESS;
	}

	public static int getFilmDbPortNumber() {
		return FILM_DB_PORT_NUMBER;
	}

	public static String getFilmDbPassword() {
		return FILM_DB_PASSWORD;
	}

	public static String getFilmDbUsername() {
		return FILM_DB_USERNAME;
	}

	public static String getFilmDbName() {
		return FILM_DB_NAME;
	}

	/**
	 * Local Dev DB
	 */
	private static final String LOCAL_DEV_FILM_DB_NETWORK_ADDRESS = "127.0.0.1"; // Secret loop back address
	private static final int LOCAL_DEV_FILM_DB_PORT_NUMBER = 3306; // Classified port
	private static final String LOCAL_DEV_FILM_DB_PASSWORD = "passw0rd"; // Super Top Secret Passw0rd
	private static final String LOCAL_DEV_FILM_DB_USERNAME = "root"; // Internal only user name
	private static final String LOCAL_DEV_DB_NAME = "";

	/**
	 * GETTERS
	 */
	
	public static String getLocalDevFilmDbNetworkAddress() {
		return LOCAL_DEV_FILM_DB_NETWORK_ADDRESS;
	}

	public static int getLocalDevFilmDbPortNumber() {
		return LOCAL_DEV_FILM_DB_PORT_NUMBER;
	}

	public static String getLocalDevFilmDbPassword() {
		return LOCAL_DEV_FILM_DB_PASSWORD;
	}

	public static String getLocalDevFilmDbUsername() {
		return LOCAL_DEV_FILM_DB_USERNAME;
	}

	public static String getLocalDevDbName() {
		return LOCAL_DEV_DB_NAME;
	}
	
	
}
