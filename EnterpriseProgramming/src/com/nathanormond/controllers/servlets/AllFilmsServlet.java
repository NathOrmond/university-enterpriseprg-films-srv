package com.nathanormond.controllers.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nathanormond.model.data.structures.Films;

/**
 * Servlet implementation class Films
 */
/**
 * 
 * @author Nathan Ormond
 *
 */
@WebServlet(name = "films", urlPatterns = { "/films" })
public class AllFilmsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllFilmsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		// if query parameter exists add it to request object
		if(request.getParameter("query") != null) { 
			String query = request.getParameter("query");
			request.setAttribute("query", query);
		}
		
		
		// create data model and add to request object
		request.setAttribute("allFilms", Films.getAllfilms());
		
		RequestDispatcher requestDispatcher= webPageDispatcher(request);

		// Forward the request to the view
		requestDispatcher.forward(request, response);
	}

	private RequestDispatcher webPageDispatcher(HttpServletRequest request){
		return request.getRequestDispatcher("pages/info/films.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}

}
