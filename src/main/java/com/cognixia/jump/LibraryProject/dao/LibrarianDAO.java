package com.cognixia.jump.LibraryProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.LibraryProject.connection.ConnectionManager;
import com.cognixia.jump.LibraryProject.model.Librarian;

public class LibrarianDAO {

	public static final Connection conn = ConnectionManager.getConnection();
	
	// Query Strings
	public static final String APPROVE_PATRON = "UPDATE patron SET account_frozen = 'true' WHERE patron_id = ?";
	public static final String UPDATE_USERNAME = "UPDATE librarian SET username = ? WHERE librarian_id = ?";
	public static final String UPDATE_PASSWORD = "UPDATE librarian SET password = ? WHERE librarian_id = ?";
	public static final String LOGIN = "SELECT * FROM librarian"
										+ "WHERE username = ? AND password = ?";
	public static final String USERNAME_CHECK = "SELECT * FROM librarian WHERE username = ?";
	
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
		
		if(usernameTaken(username)) {
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
	
	public Librarian getLibrarianByUsername(String username) {
		Librarian librarian = null;
		
		try(PreparedStatement ps = conn.prepareStatement(USERNAME_CHECK)) {
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String un = rs.getString("username");
				int id = rs.getInt("librarian_id");
				String pw = rs.getString("password");
				librarian = new Librarian(id, un, pw);
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return librarian;
	}
}
