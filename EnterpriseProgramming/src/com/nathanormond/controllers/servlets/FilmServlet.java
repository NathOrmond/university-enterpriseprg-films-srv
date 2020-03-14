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
import com.nathanormond.model.consts.FilmsSQL;
import com.nathanormond.model.data.db.FilmDAO;
import com.nathanormond.model.data.structures.Film;

/**
 * Servlet implementation class FilmServlet
 */
/**
 * 
 * @author Nathan ormond
 *
 */
@WebServlet("/film")
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FilmDAO dao;
	private HttpServletResponse response;
	private HttpServletRequest request;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		dao = new FilmDAO();
		RequestDispatcher requestDispatcher = null;
		this.request = request;
		this.response = response;
		
		if(request.getParameter("format") != null && request.getParameter("title") != null) {
			requestDispatcher = filmFormatRequest();
		}
		
		if(request.getParameter("format") == null && request.getParameter("title") != null) { 
			requestDispatcher = filmRequest();
		}
		
		if( request.getParameter("title") == null) { 
			requestDispatcher = noResultRequest();
		}
		
		requestDispatcher.forward(this.request, this.response);
	}
	
	/**
	 * 
	 * @param requestDispatcher
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private RequestDispatcher filmRequest() throws ServletException, IOException { 
		RequestDispatcher requestDispatcher;
		this.request.setAttribute("film", getFilm(request.getParameter("title")));
		// Retrieve request dispatcher
		requestDispatcher = this.request.getRequestDispatcher("pages/info/film.jsp");
		return requestDispatcher;
		
	}
	
	/**
	 * 
	 * @param requestDispatcher
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private RequestDispatcher filmFormatRequest() throws ServletException, IOException{ 
		RequestDispatcher requestDispatcher;
		this.request.setAttribute("data", getFilm(request.getParameter("title")));
		switch(this.request.getParameter("format")) { 
			case "csv":
				requestDispatcher = this.request.getRequestDispatcher("pages/data/csv.jsp");
				this.response.setContentType("text/plain;charset=UTF-8");
				break;
			case "json":
				requestDispatcher = this.request.getRequestDispatcher("pages/data/json.jsp");
				this.response.setContentType("application/json;charset=UTF-8");
				break;
			case "xml":
				requestDispatcher = this.request.getRequestDispatcher("pages/data/xml.jsp");
				this.response.setContentType("text/xml;charset=UTF-8");
				break;
			default: 
				requestDispatcher = this.request.getRequestDispatcher("pages/data/json.jsp");
				this.response.setContentType("application/json;charset=UTF-8");
				break;
		}		
		return requestDispatcher;
	}
	
	/**
	 * 
	 * @param requestDispatcher
	 * @return
	 */
	private RequestDispatcher noResultRequest() {
		RequestDispatcher requestDispatcher;
		this.request.setAttribute("film", emptyFilm());
		requestDispatcher = this.request.getRequestDispatcher("pages/info/film.jsp");
		return requestDispatcher;
	}
	
	
	/**
	 * 
	 * @param filmName
	 * @return
	 */
	private Film getFilm(String filmName) {
		try { 
			return dao.getFilm(filmName);
		} catch(IndexOutOfBoundsException e) { 
			System.out.println(e);
			return emptyFilm();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private Film emptyFilm() { 
		return new Film(0, "No Results Found", 0, "No Director", "No Stars", "No Review");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.dao = new FilmDAO();
		String title;
		title = request.getParameter("film_title");
		
		if((title != null)) { 
			try {
				request.setAttribute("insert_outcome", attemptDelete(this.dao, title));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else { 
			request.setAttribute("insert_outcome", "ERROR");
		}
		
		ServletContextListenerImpl.updateFilmsCache();
		
		doGet(request, response);
	}
	
	/**
	 * 
	 * Attempts delete on database, returns outcome for Ajax Alert dialogue (sent in response)
	 * 
	 * @param dao
	 * @param title
	 * @return Delete outcome String
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	private String attemptDelete(FilmDAO dao, String title) throws NumberFormatException, SQLException {
		return dao.deleteData(dao.getTableName(), FilmsSQL.getColumnTitle(), title) ? "DELETE SUCCESS" : "DELETE ERROR";
	}

}
