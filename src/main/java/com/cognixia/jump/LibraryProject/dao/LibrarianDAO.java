package com.cognixia.jump.LibraryProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cognixia.jump.LibraryProject.connection.ConnectionManager;

public class LibrarianDAO {

	public static final Connection conn = ConnectionManager.getConnection();
	
	// Query Strings
	public static final String APPROVE_PATRON = "UPDATE patron SET account_frozen = 'true' WHERE patron_id = ?";
	public static final String UPDATE_USERNAME = "UPDATE librarian SET username = ? WHERE librarian_id = ?";
	public static final String UPDATE_PASSWORD = "UPDATE librarian SET password = ? WHERE librarian_id = ?";
	
	
	public boolean approvePatron(int id) {
		boolean approved = false;
		
		try(PreparedStatement pstmt = conn.prepareStatement(APPROVE_PATRON)) {
			
			pstmt.setInt(1, id);;
			approved = pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return approved;
		
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
	
	public boolean updateUsernameAndPassword(int id, String username, String password) {
		
		// TODO execute commit
		
		boolean success = false;
		
		if(updateUsername(id, username)) {
			
			if(updatePassword(id, password)) {
				success = true;
			} else {
				// TODO revert change
			}
		}
		
		return success;
	}
	
}
