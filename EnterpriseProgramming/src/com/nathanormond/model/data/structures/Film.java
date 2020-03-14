package com.nathanormond.model.data.structures;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author Nathan Ormond
 *
 */
public class Film {
	
	private int id;
	private String title;
	private int year;
	private String director;
	private String stars;
	private String review;
	
	
	private static final String[] columns = {"id", "title", "year", "director", "stars", "review"};
	
	
	/***********************************
	 * INSTANTIATION
	 */

	/**
	 * Constructor : Sets internal data to values passed in as parameters.
	 * 
	 * @param id
	 * @param title
	 * @param year
	 * @param director
	 * @param stars
	 * @param review
	 */
	public Film(int id, String title, int year, String director, String stars, String review) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.stars = stars;
		this.review = review;
	}


	/***********************************
	 * GETTERS & SETTERS
	 */
	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	@XmlElement
	public void setYear(int year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	@XmlElement
	public void setDirector(String director) {
		this.director = director;
	}

	public String getStars() {
		return stars;
	}

	@XmlElement
	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getReview() {
		return review;
	}

	@XmlElement
	public void setReview(String review) {
		this.review = review;
	}
	
	public static String[] getColumns() {
		return columns;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year + ", director=" + director + ", stars=" + stars + ", review=" + review + "]";
	}
	
	public String[] getValues() { 
		return new String[] {String.valueOf(id), title, String.valueOf(year), director, stars, review };
	}
}
