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

public class BookDAO {
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static final String SELECT_ALL_BOOKS = "select * from book";
	private static final String UPDATE_BOTH = "update book set title = ?, descr = ? where isbn = ?";
	private static final String ADD_BOOK = "insert into book(isbn, title, descr, rented, added_to_library) values(?, ?, ?, ?, ?)";
	private static final String VIEW_CHECKOUT_BOOKS = "select distinct book.isgn, title, descr, rented, added_to_library from book join book_checkout on book.isbn = book_checkout.isbn";
	private static final String RETURN_BOOK = "";
	private static final String CHECKOUT_BOOK = "";
	
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
			ps.setDate(5, book.getAdded_to_library());
			
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
	
	
	
	
}
