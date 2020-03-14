package com.nathanormond.model.data.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;

import com.nathanormond.model.consts.DBConstants;
import com.nathanormond.model.consts.DBQueryBuilder;
import com.nathanormond.model.consts.FilmsSQL;
import com.nathanormond.model.data.structures.Film;

/**
 * 
 * @author Nathan Ormond
 *
 */
@WebService
public class FilmDAO extends AbstractDAO<Film> {

	private String tableName = "films";
	
	/**
	 * Constructor
	 */
	public FilmDAO() {
		super(DBConstants.getAwsDbNetworkAddress(), DBConstants.getAwsDbPortNumber(), DBConstants.getAwsDbUsername(), DBConstants.getAwsDbPassword(), DBConstants.getAwsDbName());
		
		/**Swap for below constructor for mudfoot server**/
//		super(DBConstants.getFilmDbNetworkAddress(), DBConstants.getFilmDbPortNumber(), DBConstants.getFilmDbUsername(), DBConstants.getFilmDbPassword(), DBConstants.getFilmDbName());
	}
	
	/**************************************
	 * DATABASE INTERACTION
	 */
	
	/**
	 * @throws SQLException
	 */
	@Override
	public Film getNextTypeData(ResultSet rs) throws SQLException {
		return new Film(rs.getInt(FilmsSQL.getColumnId()), rs.getString(FilmsSQL.getColumnTitle()),rs.getInt(FilmsSQL.getColumnYear()), rs.getString(FilmsSQL.getColumnDirector()), rs.getString(FilmsSQL.getColumnStars()), rs.getString(FilmsSQL.getColumnReview()));
	}
	

	/**
	 * Returns all results in DB as a List<Film>
	 */
	@Override
	public List<Film> getAllResults() {
		try {
			return executeTypeQuery(DBQueryBuilder.SELECT_ALL(tableName));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns all results in DB for a particular condition given as parameter
	 */
	@Override
	public List<Film> getResultsForCondition(String condition) {
		System.out.println(condition);
		try {
			return executeTypeQuery(DBQueryBuilder.SELECT_ALL(tableName) + condition);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the film with a particular ID (Primary Key so always unique).
	 */
	@Override
	public Film getResultByID(int id) {
		return getResultsForCondition(DBQueryBuilder.WHERE_REGEX(FilmsSQL.getColumnId(), String.valueOf(id))).get(0);
	}

	/**
	 * Returns the film with a particular name
	 * 
	 * @param name
	 * @return film
	 */
	public Film getFilm(String name) throws IndexOutOfBoundsException {
		return getResultsForCondition(DBQueryBuilder.WHERE_EQUALS(FilmsSQL.getColumnTitle(), name)).get(0);
	}
	
	/**
	 * Returns true if value is successfully added to database
	 * @throws SQLException 
	 */
	public boolean insertFilm(Film film) throws SQLException {
		insertToTable(tableName, Film.getColumns(), film.getValues(), FilmsSQL.getColumnTitle(), film.getTitle());
		return isValueInDB(tableName, FilmsSQL.getColumnTitle(), film.getTitle());
	}
	
	
	/***********************************************
	 * DATA SANITISATION
	 */

	/**
	 * Sanitises insert data to prevent doing "a bad mess up" of the database
	 * @param column headings
	 * @param column values
	 */
	@Override
	public String[] sanitisedInsertValues(String[] columns, String[] values) {
		int count = 0;

		for (String column : columns) {
			// Check for SQL injection
			if (!valueContainsSQL(values[count])) {
				
				//Check for ID column
				if (column.equals(FilmsSQL.getColumnId())) {
					// Set ID column to highest ID in table + 1
					try {
						values[count] = String.valueOf((getHighestValueInDB(FilmsSQL.getTableName(), FilmsSQL.getColumnId()) + 1));
					} catch (SQLException e) {
						e.printStackTrace();
					}

					break;
				}
			} else { 
				return null;
			}
			count++;
		}

		return values;
	}
	
	
	/*******************************
	 * GETTERS AND SETTERS
	 */
	
	public String getTableName() {
		return tableName;
	}

}
