package com.cognixia.jump.LibraryProject.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.LibraryProject.connection.ConnectionManager;
import com.cognixia.jump.LibraryProject.dao.BookDAO;
import com.cognixia.jump.LibraryProject.dao.LibrarianDAO;
import com.cognixia.jump.LibraryProject.dao.PatronDAO;
import com.cognixia.jump.LibraryProject.model.Book;
import com.cognixia.jump.LibraryProject.model.Librarian;
import com.cognixia.jump.LibraryProject.model.Patron;


@WebServlet("/LibraryProject")
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
		System.out.println("Web has been initialized");
		bookDao = new BookDAO();
		patronDao = new PatronDAO();
		librarianDao = new LibrarianDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		System.out.println(action);
		// TODO handle other actions from library and patron pages
		switch(action) {
		case "/login":
			login(request,response);
			break;
		case "/signup":
			RequestDispatcher dispatcher = request.getRequestDispatcher("signup-form.jsp");
			dispatcher.forward(request, response);
			break;
		case "/signedup":
			signup(request,response);
			break;
			
		case "/user":
			break;
		case "/librarian":
			break;
			
			
		case "/list-books":
			listBooks(request, response);
			break;
			
		default:
			response.sendRedirect("/LibraryProject");
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
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO check parameter names
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
	//go to signup page
	private void goToSignupPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("signup-form.jsp");
		
		dispatcher.forward(request, response);
		
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		private void goToUserUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("username");
			Patron patron = patronDao.getPatronByUsername(username);
			request.setAttribute("patron", patron);
			RequestDispatcher dispatcher = request.getRequestDispatcher("patron-update.jsp");
			dispatcher.forward(request, response);
		}
		
		private void goToLibrarianUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("username");
			Librarian librarian = librarianDao.getLibrarianByUsername(username);
			request.setAttribute("librarian", librarian);
			RequestDispatcher dispatcher = request.getRequestDispatcher("librarian-update.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//update name, username, password
		private void updateCredential(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
		
		//addBook for librarian
		private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String descr = request.getParameter("descr");
			
			Book book = new Book(isbn, title, descr);
			bookDao.addBook(book);
			response.sendRedirect("librarian");
			
		}
		
		private void goToUpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
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
