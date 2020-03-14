package com.nathanormond.controllers.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nathanormond.controllers.listeners.ServletContextListenerImpl;
import com.nathanormond.model.data.db.FilmDAO;
import com.nathanormond.model.data.structures.Film;


/**
 * Servlet implementation class InsertFilmServlet
 */
@WebServlet("/insert")
public class InsertFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertFilmServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
				// Retrieve request dispatcher
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/info/insertresult.jsp");
				// Forward the request to the view
				requestDispatcher.forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		FilmDAO dao = new FilmDAO();
		String title, year, director, stars, review;
		
		title = request.getParameter("film_title");
		year = request.getParameter("film_year");
		director = request.getParameter("film_director");
		stars = request.getParameter("film_stars");
		review = request.getParameter("film_review");
		
		if((title != null) && (year != null) && (director != null) && (stars != null) && (review != null)) { 
			// create data model and add to request object
			try {
				request.setAttribute("insert_outcome", attemptUpdate(dao,title,year,director,stars,review));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else { 
			request.setAttribute("insert_outcome", "ERROR WITH INPUT FORMAT");
		}
		
		ServletContextListenerImpl.updateFilmsCache();
		
		doGet(request, response);
	}
	
	/**
	 * 
	 * @param dao
	 * @param title
	 * @param year
	 * @param director
	 * @param stars
	 * @param review
	 * @return Outcome String (added to response for ajax alert)
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	private String attemptUpdate(FilmDAO dao, String title, String year, String director, String stars, String review) throws NumberFormatException, SQLException {
		return dao.insertFilm(new Film(0, title, Integer.valueOf(year), director, stars, review)) ? "INSERT SUCCESS" : "INSERT ERROR";
	}

}
