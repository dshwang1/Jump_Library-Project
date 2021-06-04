package com.cognixia.jump.LibraryProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.LibraryProject.connection.ConnectionManager;
import com.cognixia.jump.LibraryProject.model.Checkout;
import com.cognixia.jump.LibraryProject.model.Patron;

public class PatronDAO {

	public static final Connection conn = ConnectionManager.getConnection();
	
	
	// Query Strings
	public static final String UPDATE_NAME = "UPDATE patron SET first_name = ?, last_name = ? WHERE patron_id = ?";
	public static final String UPDATE_USERNAME = "UPDATE patron SET username = ? WHERE patron_id = ?";
	public static final String UPDATE_PASSWORD = "UPDATE patron SET password = ? WHERE patron_id = ?";
	public static final String CREATE_ACCOUNT = "INSERT INTO patron (patron_id, first_name, last_name, username, password, frozen"
												+ "VALUES(null, ?, ?, ?, ?, 'false')";
	public static final String LOGIN = "SELECT * FROM patron "
										+ "WHERE username = ? AND password = ?";
	public static final String USERNAME_CHECK = "SELECT * FROM patron WHERE username = ?";
	public static final String PATRON_CHECKOUT_LIST = "select * book";
	
	
	public boolean updateCredentials(int id, String firstName, String lastName, String username, String password) {
		
		boolean success = false;
		
		if(updateName(id, firstName, lastName)) {
			updateUsername(id, username);
			updatePassword(id, password);
			success = true;
		}
		
		
		return success;
	}
	
	public boolean updateName(int id, String firstName, String lastName) {
		
		boolean updated = false;
		
		try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_NAME)) {
			
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setInt(3, id);
			
			updated = pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}
	
	public boolean updateUsername(int id, String username) {
		
		boolean updated = false;
		
		// if username not taken, update username
		if(!usernameTaken(username)) {
			try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_USERNAME)) {
				
				pstmt.setString(1, username);
				pstmt.setInt(2, id);
				
				updated = pstmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return updated;
	}
	
	public boolean updatePassword(int id, String password) {
		
		boolean updated = false;
		
		try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_PASSWORD)) {
			
			pstmt.setString(1, password);
			pstmt.setInt(2, id);
			
			updated = pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}
	
	public boolean createAccount(String firstName, String lastName, String username, String password) {
		
		boolean inserted = false;
		
		// if username is not taken, create the account
		if(!usernameTaken(username)) {
			try(PreparedStatement pstmt = conn.prepareStatement(CREATE_ACCOUNT)) {
				
				pstmt.setString(1, firstName);
				pstmt.setString(2, lastName);
				pstmt.setString(3, username);
				pstmt.setString(4, password);
				
				inserted = pstmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return inserted;
	}
	
	public boolean login(String username, String password) {
		
		boolean success = false;
		
		try(PreparedStatement pstmt = conn.prepareStatement(LOGIN)) {
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			success = pstmt.executeQuery().next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	// returns true if username is taken, false otherwise
	public boolean usernameTaken(String username) {
		
		boolean taken = false;
		
		try(PreparedStatement pstmt = conn.prepareStatement(USERNAME_CHECK)) {
			
			pstmt.setString(1, username);
			taken = pstmt.executeQuery().next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return taken;
	}
	
	public Patron getPatronByUsername(String username) {
		Patron patron = null;
		
		try(PreparedStatement ps = conn.prepareStatement(USERNAME_CHECK)) {
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String un = rs.getString("username");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				int id = rs.getInt("patron_id");
				String pw = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");
				
				patron = new Patron(id, first_name, last_name, un, pw, frozen);
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return patron;
	}
	
//	public Checkout getCheckoutByUserId(int id) {
//		
//	}
}
