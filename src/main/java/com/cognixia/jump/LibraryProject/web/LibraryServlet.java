package com.cognixia.jump.LibraryProject.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.LibraryProject.connection.ConnectionManager;
import com.cognixia.jump.LibraryProject.dao.LibrarianDAO;
import com.cognixia.jump.LibraryProject.dao.PatronDAO;

public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PatronDAO patDao = new PatronDAO();
	private LibrarianDAO libDao = new LibrarianDAO();
	
	private static final String libPage = "librarian-page.jsp";
	private static final String patPage = "patron-page.jsp";
	
	
	public void init(ServletConfig config) throws ServletException {
		patDao = new PatronDAO();
		libDao = new LibrarianDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		// TODO handle other actions from library and patron pages
		switch(action) {
		case "/login":
			login(request,response);
			break;
		case "/signup":
			signup(request,response);
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void destroy() {
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO check paramter names
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("login-type");
		
		boolean success;
		String page;
		
		// TODO check page name
		if(type.equals("patron")) {
			success = patDao.login(username, password);
			page = patPage;
		} else {
			success = libDao.login(username, password);
			page = libPage;
		}
		
		// redirect
		if(success) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			//TODO send failed message
		}
		
		
	}
	
	public void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO check parameter names
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		
		boolean success;
		success = patDao.createAccount(firstName, lastName, username, password);
		
		if(success) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(patPage);
			dispatcher.forward(request, response);
		} else {
			// TODO send failed message
		}
		
	}

}
