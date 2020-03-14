package com.nathanormond.views;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.google.gson.Gson;
import com.nathanormond.model.consts.FilmsSQL;
import com.nathanormond.model.data.structures.Film;
import com.nathanormond.model.data.structures.Films;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author Nathan Ormond
 *
 */
public class ObjectToDataImplBean implements ObjectToDataInterface<Film> {

	private static final String[] CSV_HEADER = { FilmsSQL.getColumnId(), FilmsSQL.getColumnTitle(), FilmsSQL.getColumnYear(), FilmsSQL.getColumnDirector(), FilmsSQL.getColumnStars(), FilmsSQL.getColumnReview() };

	/******************************************
	 * METHODS FOR LIST<FILM> TO DATA
	 */
	
	
	/**
	 * Converts List<Film> to XML (returned as String)
	 */
	@Override
	public String toXMLString(List<Film> films) {
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		xstream.alias("film", Film.class);
		xstream.alias("films", Films.class);
		return xstream.toXML(films);
	}

	/**
	 * Converts List<Film> to JSON (returned as String)
	 */
	@Override
	public String toJSONString(List<Film> films) {
		Gson gson = new Gson();
		return gson.toJson(films);
	}

	/**
	 * Converts List<Film> to CSV (returned as String)
	 */
	@Override
	public String toCSVString(List<Film> films) {
		StringBuilder sb = new StringBuilder();
		CSVPrinter csvPrinter;
		try {
			csvPrinter = new CSVPrinter(sb, CSVFormat.EXCEL.withHeader(CSV_HEADER));	//CSV Printer - change formatting here
			for (Film film : films) {
				List<String> data = Arrays.asList(String.valueOf(film.getId()), film.getTitle(), String.valueOf(film.getYear()), film.getDirector(), film.getStars(), film.getReview());
				csvPrinter.printRecord(data);
			}
			csvPrinter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "APACHE COMMONS CSV PRINTER ERROR";
		}
		return sb.toString();
	}
	
	/******************************************
	 * METHODS FOR FILM TO DATA
	 */

	/**
	 * Object to XML String
	 */
	@Override
	public String toXMLString(Film datas) {
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		xstream.alias("film", Film.class);
		return xstream.toXML(datas);
	}

	/**
	 * Object to JSON String
	 */
	@Override
	public String toJSONString(Film datas) {
		Gson gson = new Gson();
		return gson.toJson(datas);
	}

	/**
	 * Object to CSV String
	 */
	@Override
	public String toCSVString(Film datas) {
		StringBuilder sb = new StringBuilder();
		CSVPrinter csvPrinter;
		try {
			csvPrinter = new CSVPrinter(sb, CSVFormat.EXCEL.withHeader(CSV_HEADER));	//CSV Printer - change formatting here
			List<String> data = Arrays.asList(String.valueOf(datas.getId()), datas.getTitle(), String.valueOf(datas.getYear()), datas.getDirector(), datas.getStars(), datas.getReview());
			csvPrinter.printRecord(data);
			csvPrinter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "APACHE COMMONS CSV PRINTER ERROR";
		}
		return sb.toString();
	}


}
