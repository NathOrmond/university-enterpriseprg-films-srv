package com.nathanormond.model.consts;

/**
 * 
 * @author Nathan Ormond
 *
 */
public class FilmsSQL {
	
	private static final String TABLE_NAME = "films";
	
	/***********************************
	 * COLUMNS
	 */
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_TITLE = "title";
	private static final String COLUMN_YEAR = "year"; 
	private static final String COLUMN_DIRECTOR = "director";
	private static final String COLUMN_STARS = "stars";
	private static final String COLUMN_REVIEW = "review";
	
	public static String getTableName() {
		return TABLE_NAME;
	}
	public static String getColumnId() {
		return COLUMN_ID;
	}
	public static String getColumnTitle() {
		return COLUMN_TITLE;
	}
	public static String getColumnYear() {
		return COLUMN_YEAR;
	}
	public static String getColumnDirector() {
		return COLUMN_DIRECTOR;
	}
	public static String getColumnStars() {
		return COLUMN_STARS;
	}
	public static String getColumnReview() {
		return COLUMN_REVIEW;
	}
	
	

}
