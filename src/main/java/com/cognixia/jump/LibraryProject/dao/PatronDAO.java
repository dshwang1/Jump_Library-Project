package com.cognixia.jump.LibraryProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cognixia.jump.LibraryProject.connection.ConnectionManager;

public class PatronDAO {

	public static final Connection conn = ConnectionManager.getConnection();
	
	// Query Strings
	public static final String UPDATE_NAME = "UPDATE patron SET first_name = ?, last_name = ? WHERE patron_id = ?";
	public static final String UPDATE_USERNAME = "UPDATE patron SET username = ? WHERE patron_id = ?";
	public static final String UPDATE_PASSWORD = "UPDATE patron SET password = ? WHERE patron_id = ?";
	public static final String CREATE_ACCOUNT = "INSERT INTO patron (patron_id, first_name, last_name, username, password, frozen"
												+ "VALUES(null, ?, ?, ?, ?, 'false')";
	
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
		
		try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_USERNAME)) {
			
			pstmt.setString(1, username);
			pstmt.setInt(2, id);
			
			updated = pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		try(PreparedStatement pstmt = conn.prepareStatement(CREATE_ACCOUNT)) {
			
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, username);
			pstmt.setString(4, password);
			
			inserted = pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return inserted;
		
	}
	
	
}
