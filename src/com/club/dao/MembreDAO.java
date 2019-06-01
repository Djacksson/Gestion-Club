package com.club.dao;

import java.util.*;

import com.club.model.Membre;

import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

public class MembreDAO {

	private Connection myConn;
	
	public MembreDAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("projet.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	public void deleteMembre(int employeeId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from membres where idmembre=?");
			
			// set param
			myStmt.setInt(1, employeeId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	
	public void updateMembre(Membre theMembre) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update membres"
					+ " set nommembre=?, prenommembre=?, emailmembre=?, passwordmembre=?, niveaumembre=?, phonemembre=?"
					+ " where idmembre=?");
			
			// set params
			myStmt.setString(1, theMembre.getFirstName());
			myStmt.setString(2, theMembre.getLastName());
			myStmt.setString(3, theMembre.getEmail());
			myStmt.setString(4, theMembre.getPassword());
			myStmt.setString(5, theMembre.getNiveau());
			myStmt.setString(6, theMembre.getPhone());
			myStmt.setInt(7, theMembre.getId());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	public void addMembre(Membre theMembre) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into membres"
					+ " (nommembre, prenommembre, emailmembre, passwordmembre,niveaumembre,phonemembre)"
					+ " values (?, ?, ?, ?, ?,?)");
			
			// set params
			myStmt.setString(1, theMembre.getFirstName());
			myStmt.setString(2, theMembre.getLastName());
			myStmt.setString(3, theMembre.getEmail());
			myStmt.setString(4, theMembre.getPassword());
			myStmt.setString(5, theMembre.getNiveau());
			myStmt.setString(6, theMembre.getPhone());

			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	public List<Membre> getAllMembre() throws Exception {
		List<Membre> list = new ArrayList<Membre>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from membres order by nommembre");
			
			while (myRs.next()) {
				Membre theMembre = convertRowToMembre(myRs);
				list.add(theMembre);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Membre> searchMembre(String lastName) throws Exception {
		List<Membre> list = new ArrayList<Membre>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from membres where nommembre like ?  order by nommembre");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Membre theMembre = convertRowToMembre(myRs);
				list.add(theMembre);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Membre convertRowToMembre(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("idmembre");
		String lastName = myRs.getString("nommembre");
		String firstName = myRs.getString("prenommembre");
		String email = myRs.getString("emailmembre");
		String password = myRs.getString("passwordmembre");
		String niveau = myRs.getString("niveaumembre");
		String phone = myRs.getString("phonemembre");


		
		Membre theMembre = new Membre(id, lastName, firstName, email,password,niveau,phone);
		
		return theMembre;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);		
	}
	
	public static void main(String[] args) throws Exception {
		
		MembreDAO dao = new MembreDAO();
		System.out.println(dao.searchMembre("thom"));

		System.out.println(dao.getAllMembre());
	}

}
