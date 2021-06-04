package com.cognixia.jump.LibraryProject.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.LibraryProject.connection.ConnectionManager;
import com.cognixia.jump.LibraryProject.dao.BookDAO;
import com.cognixia.jump.LibraryProject.dao.LibrarianDAO;
import com.cognixia.jump.LibraryProject.dao.PatronDAO;
import com.cognixia.jump.LibraryProject.model.Book;

public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDAO bookDao;
	private PatronDAO patronDao;
	private LibrarianDAO librarianDao;

//	private PatronDAO patDao = new PatronDAO();
//	private LibrarianDAO libDao = new LibrarianDAO();
	
	private static final String libPage = "librarian-page.jsp";
	private static final String patPage = "patron-page.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		bookDao = new BookDAO();
		patronDao = new PatronDAO();
		librarianDao = new LibrarianDAO();
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
		case "/list-books":
			listBooks(request, response);
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
			success = patronDao.login(username, password);
			page = patPage;
		} else {
			success = librarianDao.login(username, password);
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
		success = patronDao.createAccount(firstName, lastName, username, password);
		
		if(success) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(patPage);
			dispatcher.forward(request, response);
		} else {
			// TODO send failed message
		}
		
	}
	
	//list books for patrons


		private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Book> allBooks = bookDao.getBooks();
			
			request.setAttribute("allBooks", allBooks);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("lisg-of-books.jsp");
			
			dispatcher.forward(request, response);
		}
		
		//list checkedout books for patrons
		private void listCheckedoutBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Book> checkedoutBooks = bookDao.getCheckedoutBooks();
			request.setAttribute("checkedoutBooks", checkedoutBooks);
			RequestDispatcher dispatcher = request.getRequestDispatcher("checkedout-books.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//checkout book for patron
		private void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
		
		//return book for patron
		private void returnbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
		
		//update name, username, password
		private void updateCredential(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
		
		//addBook for librarian
		private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
		
		//update book
		private void updateBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
		
		//approve patron for librarian
		private void approve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

		
		//update username, password for librarian
		private void updateCredentialLibrarian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

}
