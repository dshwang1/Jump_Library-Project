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
	private static final String UPDATE_TITLE = "";
	private static final String UPDATE_DESCRIPTION = "";
	private static final String UPDATE_BOTH = "";
	private static final String ADD_BOOK = "";
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
	
	
	
	
	
}
