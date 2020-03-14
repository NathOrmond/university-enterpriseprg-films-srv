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
 * Servlet implementation class DataAPIServlet
 */

/**
 * 
 * @author Nathan Ormond
 *
 */
@WebServlet(name = "data", urlPatterns = { "/data" })
public class DataAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String format = "format";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataAPIServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = null;
		request.setAttribute("data", Films.getAllfilms());

		if (request.getParameter(format) == null) {

			requestDispatcher = jsonDispatcher(request);
			
		} else {

			switch (request.getParameter(format).toLowerCase()) {

			case "xml":
				requestDispatcher = xmlDispatcher(request);
				response.setContentType("text/xml;charset=UTF-8");
				break;
			case "csv":
				requestDispatcher = csvDispatcher(request);
				response.setContentType("text/plain;charset=UTF-8");
				break;
			case "json":
				requestDispatcher = jsonDispatcher(request);
				response.setContentType("application/json;charset=UTF-8");
				break;
			}
		}
		
		
		requestDispatcher.forward(request, response);
	}
	


	/*************************************
	 * XML Methods
	 */
	
	private RequestDispatcher xmlDispatcher(HttpServletRequest request){
		return request.getRequestDispatcher("pages/data/xml.jsp");
	}
	
	/*************************************
	 * JSON Methods
	 */

	private RequestDispatcher jsonDispatcher(HttpServletRequest request) {
		return request.getRequestDispatcher("pages/data/json.jsp");
	}
	
	/*************************************
	 * CSV Methods
	 */

	private RequestDispatcher csvDispatcher(HttpServletRequest request){
		return request.getRequestDispatcher("pages/data/csv.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
