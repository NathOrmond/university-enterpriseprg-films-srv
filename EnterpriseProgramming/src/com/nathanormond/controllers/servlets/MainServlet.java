package com.nathanormond.controllers.servlets;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nathanormond.model.data.structures.Film;
import com.nathanormond.model.data.structures.Films;

/**
 * Servlet implementation class Films
 */
/**
 * 
 * @author Nathan Ormond
 *
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// create Key Words and add to request
		Set<String> autocompleteKeyWords = new LinkedHashSet<String>();

		if (Films.getAllfilms() != null) {   // Avoids 500 error if GET request made before local cache created (e.g. at startup)
			populateKeyWords(Films.getAllfilms(), autocompleteKeyWords);
			request.setAttribute("autocomplete_key_words", autocompleteKeyWords);
		}

		RequestDispatcher requestDispatcher = webPageDispatcher(request);

		// Forward the request to the view
		requestDispatcher.forward(request, response);
	}
	
	/**
	 * 
	 * @param films
	 * @param autocompleteKeyWords
	 * @return populated set of keywords
	 */
	private Set<String> populateKeyWords(List<Film> films, Set<String> autocompleteKeyWords){ 
		for (Film film : films) {
			autocompleteKeyWords.add(film.getTitle());
			for (String actor : film.getStars().split(",")) {
				autocompleteKeyWords.add(actor);
			}
		}
		return autocompleteKeyWords;
	}

	/**
	 * 
	 * @param request
	 * @return requestDispatcher
	 */
	private RequestDispatcher webPageDispatcher(HttpServletRequest request) {
		return request.getRequestDispatcher("/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}