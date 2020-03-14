package com.nathanormond.controllers.listeners;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.nathanormond.model.data.db.FilmDAO;
import com.nathanormond.model.data.structures.Films;

/**
 * @author Nathan Ormond
 */
@WebListener
public class ServletContextListenerImpl implements Runnable, ServletContextListener {
	
	private Thread thread;
	
	/***************************************
	 * RUN ON SERVLET INITIALISATION
	 */
	
	@Override
	public void contextInitialized(ServletContextEvent e) {
		System.out.println("FILMS CONTEXT SERVLET LISTENER STARTED");	
		startThread();
	}
	
	/**
	 * Starts new thread 
	 */
	private void startThread() { 
		System.out.println("ASYNC TASK: UPDATING LOCAL FILMS CACHE");
		thread = new Thread(this);
		thread.start();
	}
	
	/***************************************
	 * UPDATE DATA CACHE ON NEW THREAD
	 */
	
	/**
	 * Asynchronous task
	 */
	@Override
	public void run() {
		ServletContextListenerImpl.updateFilmsCache();
	}
	
	/**
	 * Updates film cache
	 */
	public static void updateFilmsCache() { 
		System.out.println("UPDATING CACHE");
		FilmDAO dao = new FilmDAO();
		Films.allFilms = dao.getAllResults();
		System.out.println("CACHE UPDATE COMPLETE!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// not used
	}
	
}
