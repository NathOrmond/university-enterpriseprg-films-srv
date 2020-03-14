package com.nathanormond.model.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nathanormond.model.consts.DBQueryBuilder;

/**
 * 
 * @author Nathan Ormond
 *
 * @param <T> Data Object for DAO to format into
 */
public abstract class AbstractDAO<T> {

	/****************************
	 * INITIALISATION
	 */

	private Connection conn = null;
	private Statement stmt;

	private String user;
	private String password;
	private String networkAddress;
	private int portNum;
	private String dbName;
	private String url;

	/**
	 * Constructor : sets necessary variables for DB Connection. 
	 * 
	 * updates connection URL
	 * 
	 * @param networkAddress
	 * @param portNum
	 * @param user
	 * @param password
	 */
	public AbstractDAO(String networkAddress, int portNum, String user, String password, String dbName) {
		this.networkAddress = networkAddress;
		this.portNum = portNum;
		this.user = user;
		this.password = password;
		this.dbName = dbName;
		updateURL();
	}

	/**
	 * updates the connection URL for most recent variables
	 */
	public void updateURL() {
		this.url = String.format("jdbc:mysql://%s:%s/%s", this.networkAddress, this.portNum, this.dbName);
	}

	/****************************
	 * Manage DB Connection
	 */

	/**
	 * Opens a connection to MySQL database
	 */
	private void openConnection() {
		try {
			// Current driver com.mysql.cj.jdbc.Driver
			// Deprecated driver com.mysql.jdbc.Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("CONNECTED TO DRIVER");
		} catch (Exception e) {
			System.out.println("ERROR WITH JDBC DRIVER");
			System.out.println(e);
		}
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			System.out.println("CONNECTED TO " + url + "\n AS USER : " + user);
		} catch (SQLException e) {
			System.out.println("ERROR CONNECTING TO : " + url + "\n AS USER : " + user);
			System.out.println(e);
		}

	}

	/**
	 * Closes connection to MySQL database
	 */
	private void closeConnection() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR CLOSING CONNECTION");
			e.printStackTrace();
		}
	}
	
	/****************************
	 * DB Operations
	 */
	
	/**
	 * Executes and SQL query injected as String
	 * Returns a list of results  (List Generic Type defined by implementation of DAO)
	 * 
	 * @param query
	 * @return List<T>
	 * @throws SQLException
	 */
	public List<T> executeTypeQuery(String query) throws SQLException {
		List<T> returnList = new ArrayList<T>();
		openConnection();
		try {
			ResultSet rs = getResults(query);
			while (rs.next()) {
				returnList.add(getNextTypeData(rs));
			}
			closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return returnList;
	}
	
	public int getCountInTable() {
		return 0;
	}
	
	/**
	 * Inserts value to SQL Data 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @throws SQLException
	 */
	public void insertToTable(String tableName, String[] columns, String[] values, String significantValueColumn, String significantValue) throws SQLException { 
		String[] sanitisedValues = sanitisedInsertValues(columns, values);
		
		if((!isValueInDB(tableName, significantValueColumn, significantValue)) && (sanitisedValues != null)) {
			openConnection();
			String query = DBQueryBuilder.INSERT(tableName, columns, sanitisedValues);
			System.out.println("EXECUTE QUERY : " + query);
			stmt.execute(query);
			closeConnection();
		}
	}
	
	/**
	 * @param tableName
	 * @param columnName
	 * @param value
	 * @return TRUE if value exists in DB
	 * @throws SQLException
	 */
	public boolean isValueInDB(String tableName, String columnName, String value) throws SQLException {
		int returnValue = 0;
		openConnection();
		ResultSet rs = getResults(DBQueryBuilder.GET_COUNT(tableName, columnName, value));
		while(rs.next()) {
		returnValue = rs.getInt("count");
		}
		System.out.println(returnValue);
		closeConnection();
		return (returnValue != 0);
	}
	
	/**
	 * 
	 * @param tableName
	 * @param columnName
	 * @return highest value for given column in given table
	 * @throws SQLException
	 */
	public int getHighestValueInDB(String tableName, String columnName) throws SQLException { 
		int returnValue = 0;
		openConnection();
		ResultSet rs = getResults(DBQueryBuilder.MAX(tableName, columnName));
		while(rs.next()) {
			returnValue = rs.getInt("MAX( id )");
		}
		closeConnection();
		System.out.print(returnValue);
		return returnValue;
	}
	
	/**
	 * Really high level complex l337 cryptography (pHD)
	 * 
	 * ("The ultimate security is not letting the user do anything" - Aristotle or John McAfee)
	 * Lets hope nobody makes a film containing any of these keywords!
	 * 
	 * If you manage to beat this then GCHQ automatically offer you a job.
	 */
	public boolean valueContainsSQL(String value) {
		String[] naughtyWords = {"INSERT", "DROP", "SELECT", "JOIN"}; 
		for(String badWord : naughtyWords) { 
			if(value.toUpperCase().contains(badWord)) { 
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param query
	 * @return query result
	 * @throws SQLException
	 */
	private ResultSet getResults(String query) throws SQLException { 
		System.out.println(String.format("ATTEMPTING QUERY IN DATABASE : %s", query));
		return stmt.executeQuery(query);
	}
	
	//----------------------------------------------------------------------------------------

	/**
	 * 
	 * Attempts to delete a value from SQL database, 
	 * Returns outcome.
	 * Prints debug to console.
	 * 
	 * @param tableName
	 * @param columnName
	 * @param value
	 * @return true if completed successfully
	 * @throws SQLException
	 */
	public boolean deleteData(String tableName, String columnName, String value) throws SQLException {
		String query = DBQueryBuilder.DELETE(tableName, columnName, value);
		System.out.println(String.format("Attempting Query : %s", query));
		openConnection();
		PreparedStatement preparedStmt = conn.prepareStatement(query);
//		preparedStmt.setInt(1, 3);
		boolean rv = preparedStmt.execute();		// execute the prepared statement
		closeConnection();
		System.out.println(String.format("DELETE OUTCOME : %s", String.valueOf(rv)));
		return !rv; // !rv because prepared stmt returns opposite of what you would expect .. 
	}

	//----------------------------------------------------------------------------------------
	
	/** TO BE DEFINED WITHIN INSTANTIATION*/

	/**
	 * 
	 * @param columns
	 * @param values
	 * @return sanitised data
	 */
	public abstract String[] sanitisedInsertValues(String[] columns, String[] values);

	/**
	 * SELECT * FROM table
	 * @return List<T> allResults
	 * @throws SQLException
	 */
	public abstract List<T> getAllResults() throws SQLException;

	/**
	 * 
	 * @param id
	 * @return get result from table for its ID (Primary Key)
	 * @throws SQLException
	 */
	public abstract T getResultByID(int id) throws SQLException;

	/**
	 * get results from table for a condition "WHERE x"
	 * @param condition
	 * @return results for condition
	 * @throws SQLException
	 */
	public abstract List<T> getResultsForCondition(String condition) throws SQLException;

	public abstract T getNextTypeData(ResultSet rs) throws SQLException;

	//----------------------------------------------------------------------------------------
	
	/****************************
	 * GETTERS AND SETTERS
	 */

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNetworkAddress() {
		return networkAddress;
	}

	public void setNetworkAddress(String networkAddress) {
		this.networkAddress = networkAddress;
	}

	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	public String getUrl() {
		return url;
	}
}
