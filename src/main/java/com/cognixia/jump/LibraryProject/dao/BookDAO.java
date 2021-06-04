package com.cognixia.jump.LibraryProject.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.LibraryProject.connection.ConnectionManager;
import com.cognixia.jump.LibraryProject.model.Book;
import com.cognixia.jump.LibraryProject.model.Checkout;
import com.cognixia.jump.LibraryProject.model.Patron;

public class BookDAO {
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static final String SELECT_ALL_BOOKS = "select * from book";
	private static final String UPDATE_BOTH = "update book set title = ?, descr = ? where isbn = ?";
	private static final String ADD_BOOK = "insert into book(isbn, title, descr, rented, added_to_library) values(?, ?, ?, ?, ?)";
	private static final String VIEW_CHECKOUT_BOOKS = "select distinct book.isgn, title, descr, rented, added_to_library from book join book_checkout on book.isbn = book_checkout.isbn";
	private static final String RETURN_BOOK = "update book_checkout set returned = ? where checkout_id = ?";
	private static final String CHECKOUT_BOOK = "insert into book_checkout(patron_id, isbn, checkedout, due-date, returned) vlaues(?, ?, ?, null, null)";
	private static final String CHECK_RETURN = "select rented from book where isbn = ?";
	private static final String UPDATE_RETURN = "update book set rented = ? where isbn = ?";
	private static final String SELECT_BOOK_BY_ISBN = "select * from book where isbn = ?";
	
	
	public List<Book> getBooks() {
		List<Book> allBooks = new ArrayList<>();
		try(PreparedStatement ps = conn.prepareStatement(SELECT_ALL_BOOKS);
				ResultSet rs = ps.executeQuery()) {
			
			while(rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String descr = rs.getString("descr");
				Boolean rented = rs.getBoolean("rented");
				Date date = rs.getDate("added_to_library");
				allBooks.add(new Book(isbn, title, descr, rented, date));
			}
			
		} catch(SQLException e) { 
			e.printStackTrace();
		}
		return allBooks;
	}
	
	
	public List<Book> getCheckedoutBooks() {
		List<Book> checkedoutBooks = new ArrayList<>();
		try(PreparedStatement ps = conn.prepareStatement(VIEW_CHECKOUT_BOOKS);
				ResultSet rs = ps.executeQuery()) {
			
			while(rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String descr = rs.getString("descr");
				Boolean rented = rs.getBoolean("rented");
				Date date = rs.getDate("added_to_library");
				checkedoutBooks.add(new Book(isbn, title, descr, rented, date));
			}
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return checkedoutBooks;
	}
	
	public boolean addBook(Book book) {
		try(PreparedStatement ps = conn.prepareStatement(ADD_BOOK)) {
			ps.setString(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getDescr());
			ps.setBoolean(4, false);
			ps.setDate(5, null);
			
			if(ps.executeUpdate() > 0)
				return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean updateBook(Book book) {
		try(PreparedStatement ps = conn.prepareStatement(UPDATE_BOTH)) {
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getDescr());
			ps.setString(3, book.getIsbn());
			
			if(ps.executeUpdate() > 0)
				return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	//add new checkout row into book_checkout table
	public boolean addCheckout(Book book, Patron patron) {
		//is checkRent returns true, this book is in rent, can't checkout.
		if(checkRent(book))
			return false;
		
		try(PreparedStatement ps = conn.prepareStatement(CHECKOUT_BOOK)) {
			ps.setInt(1, patron.getId());
			ps.setString(2, book.getIsbn());
			//need to get today's date
			ps.setDate(3, null);
			updateRent(book);
			if(ps.executeUpdate() > 0)
				return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	public boolean returnBook(Checkout checkout, Book book) {
		//if book is not in rent, then return false;
		if(!checkRent(book))
			return false;
		
		try(PreparedStatement ps = conn.prepareStatement(RETURN_BOOK)) {
			ps.setDate(1, null);
			ps.setInt(2, checkout.getId());
			updateRent(book);
			
			if(ps.executeUpdate() > 0)
				return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	//returns the current book rented status.
	public boolean checkRent(Book book) {
		boolean isrent = false;
		try(PreparedStatement ps = conn.prepareStatement(CHECK_RETURN)) {
			
			ps.setString(1, book.getIsbn());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				isrent = rs.getBoolean(1);
			
			rs.close();
			
			return isrent;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return isrent;
	}
	
	//updates the rented to opposite status no matter what status is in.
	public boolean updateRent(Book book) {
		boolean rent = checkRent(book);
		if(rent)
			rent = false;
		else
			rent = true;
		try(PreparedStatement ps = conn.prepareStatement(UPDATE_RETURN)) {
			ps.setBoolean(1, rent);
			ps.setString(2, book.getIsbn());
			if(ps.executeUpdate() > 0)
				return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//returns a Book by bookISBN
	public Book getBookByIsbn(String isbn) {
		Book book = null;
		
		try(PreparedStatement ps = conn.prepareStatement(SELECT_BOOK_BY_ISBN)) {
			ps.setString(1, isbn);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String this_isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String descr = rs.getString("descr");
				book = new Book(this_isbn, title, descr);
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return book;
	}
	

	
	
	
}
