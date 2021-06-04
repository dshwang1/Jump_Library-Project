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


@WebServlet("/")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDAO bookDao;
	private PatronDAO patronDao;
	private LibrarianDAO librarianDao;

//	private PatronDAO patDao = new PatronDAO();
//	private LibrarianDAO libDao = new LibrarianDAO();
	
	private static final String libPage = "librarian-page.jsp";
	private static final String patPage = "user-page.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Web has been initialized");
		bookDao = new BookDAO();
		patronDao = new PatronDAO();
		librarianDao = new LibrarianDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String action = "";
		String action = request.getServletPath();
		System.out.println(action);
		System.out.println("switching pages");
		// TODO handle other actions from library and patron pages
		switch(action) {
		case "/login":
			login(request,response);
			break;
		case "/signup":
			goToSignupPage(request, response);
			break;
		case "/signing":
			signup(request,response);
			break;
		case "/patron":
			toPatronPage(request, response);
			break;

		case "/librarian":
			toLibPage(request, response);
			break;
			
		case "/list":
			listBooks(request, response);
			break;
			
		case "/checklist":
			listCheckedoutBooks(request, response);
			break;
			
		case "/update-form":
			goToUpdateBook(request, response);
			break;
			
		case "/update":
			updateBooks(request, response);
			break;
			
		case "/add-book":
			addBook(request, response);
			break;
			

			
		default:
			response.sendRedirect("/");
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
	

	private void toPatronPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(patPage);
		dispatcher.forward(request, response);
	}
	
	private void toLibPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(libPage);
		dispatcher.forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("login-type");
		
		boolean success;
		String page;
		
		// set redirect page to either patron or library
		if(type.equals("patron")) {
			success = patronDao.login(username, password);
			page = patPage;
		} else {
			success = librarianDao.login(username, password);
			page = libPage;
		}
		
		// redirect
		if(success) {
			if(type.equals("patron")) {
				response.sendRedirect("patron");
			} else {
				response.sendRedirect("librarian");
			}

		} else {
			//TODO send failed message
			System.out.println("Login failed. ");
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
			response.sendRedirect("/LibrayProject");
//			toPatronPage(request, response);
		} else {
			// TODO send failed message
			System.out.println("Failed signup");
		}
		
	}
	
	//list books for patrons


		private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Book> allBooks = bookDao.getBooks();
			
			request.setAttribute("allBooks", allBooks);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("list-of-books.jsp");
			
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
			
			String username = request.getParameter("username");
			String isbn = request.getParameter("isbn");
			Book book = bookDao.getBookByIsbn(isbn);
			Patron patron = patronDao.getPatronByUsername(username);
			
			bookDao.addCheckout(book, patron);
			
		}
		
		//return book for patron
		private void returnbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String username = request.getParameter("username");
			String isbn = request.getParameter("isbn");
			Book book = bookDao.getBookByIsbn(isbn);
			
			// TODO
			//bookDao.getCheckout(patron_id);
			bookDao.returnBook(null, book);
			
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
		private boolean updatePatronCredential(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String[] fullName = request.getParameter("name").split(" ");
			String first_name = fullName[0];
			String last_name = fullName[1];
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			return patronDao.updateCredentials(id, first_name, last_name, username, password);
		}
		
		//addBook for librarian
		private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String descr = request.getParameter("description");
			
			Book book = new Book(isbn, title, descr);
			bookDao.addBook(book);
			response.sendRedirect("librarian");
			
		}
		
		private void goToUpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("update-book.jsp");
			dispatcher.forward(request, response);
		}
		
		
		//update book
		private void updateBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String descr = request.getParameter("description");
			Book book = new Book(isbn, title, descr);
			bookDao.updateBook(book);
			
			response.sendRedirect("librarian");
		}
		
		//approve patron for librarian
		private void approve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			int id = Integer.parseInt(request.getParameter("patron_id"));
			
			List<Patron> pList = librarianDao.approvePatron(id);
			
			if(pList.isEmpty()) {
				System.out.println("success");
				// TODO show changed patrons
			} else {
				System.out.println("Failed");
			}
		}

		
		//update username, password for librarian
		private void updateCredentialLibrarian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			int id = Integer.parseInt(request.getParameter("librarian_id"));
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			boolean success = librarianDao.updateUsernameAndPassword(id, username, password);
			
			if (success) {
				System.out.println("updated credentials");
			} else {
				System.out.println("failed to update credentials");
			}
			
		}

}
