package com.cognixia.jump.LibraryProject.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.LibraryProject.connection.ConnectionManager;
import com.cognixia.jump.LibraryProject.dao.BookDAO;
import com.cognixia.jump.LibraryProject.dao.LibrarianDAO;
import com.cognixia.jump.LibraryProject.dao.PatronDAO;

/**
 * Servlet implementation class LibraryServlet
 */
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDAO bookDao;
	private PatronDAO patronDao;
	private LibrarianDAO librarianDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		bookDao = new BookDAO();
		patronDao = new PatronDAO();
		librarianDao = new LibrarianDAO();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			ConnectionManager.getConnection().close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//login
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	//librarian login
	private void librarianLogin(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	
	//list books for patrons
	private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	//list checkedout books for patrons
	private void listCheckedoutBooks(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	//sign up for patron
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	//checkout book for patron
	private void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	//return book for patron
	private void returnbook(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	//update name, username, password
	private void updateCredential(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	//addBook for librarian
	private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	//update book
	private void updateBooks(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	//approve patron for librarian
	private void approve(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	//update username, password for librarian
	private void updateCredentialLibrarian(HttpServletRequest request, HttpServletResponse response) throws ServletExceptoin, IOException {
		
	}
	
	
	
	
	

}
