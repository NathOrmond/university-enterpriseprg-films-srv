package com.nathanormond.model.data.structures;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * 
 * @author Nathan Ormond
 *
 */
public class Films {
	
	@XStreamAlias("films")
	public static List<Film> allFilms;
	
	/**
	 * @return adllFilms
	 */
	public static List<Film> getAllfilms() {
		return allFilms;
	}
	

}
