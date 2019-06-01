package com.club.dao;

import java.util.*;

import com.club.model.Compta;

import java.sql.*;
import java.io.*;

public class ComptaDAO {

	private Connection myConn;
	
	public ComptaDAO() throws Exception {
		
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
	
	public void deleteCompta(int operationId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from membres where idmembre=?");
			
			// set param
			myStmt.setInt(1, operationId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	
	public void updateCompta(Compta theCompta) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update membres"
					+ " set nommembre=?, prenommembre=?, emailmembre=?, passwordmembre=?, niveaumembre=?, phonemembre=?"
					+ " where idmembre=?");
			
			// set params
			myStmt.setString(1, theCompta.getNomoperation());
			myStmt.setString(2, theCompta.getObjectifoperation());
			myStmt.setString(3, theCompta.getDateoperation());
			myStmt.setString(4, theCompta.getMembreoperation());
			myStmt.setString(5, theCompta.getSoldeoperation());
			myStmt.setInt(7, theCompta.getId());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	public void addCompta(Compta theCompta) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into membres"
					+ " (nommembre, prenommembre, emailmembre, passwordmembre,niveaumembre,phonemembre)"
					+ " values (?, ?, ?, ?, ?,?)");
			
			// set params
			myStmt.setString(1, theCompta.getNomoperation());
			myStmt.setString(2, theCompta.getObjectifoperation());
			myStmt.setString(3, theCompta.getDateoperation());
			myStmt.setString(4, theCompta.getMembreoperation());
			myStmt.setString(5, theCompta.getSoldeoperation());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	public List<Compta> getAllCompta() throws Exception {
		List<Compta> list = new ArrayList<Compta>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from membres order by nommembre");
			
			while (myRs.next()) {
				Compta theCompta = convertRowToCompta(myRs);
				list.add(theCompta);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Compta> searchCompta(String nomoperation) throws Exception {
		List<Compta> list = new ArrayList<Compta>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			nomoperation += "%";
			myStmt = myConn.prepareStatement("select * from membres where nommembre like ?  order by nommembre");
			
			myStmt.setString(1, nomoperation);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Compta theCompta = convertRowToCompta(myRs);
				list.add(theCompta);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Compta convertRowToCompta(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("idmembre");
		String nomoperation = myRs.getString("nommembre");
		String objectifoperation = myRs.getString("prenommembre");
		String dateoperation = myRs.getString("emailmembre");
		String membreoperation = myRs.getString("passwordmembre");
		String soldeoperation = myRs.getString("niveaumembre");


		
		Compta theCompta = new Compta(id, nomoperation, objectifoperation, dateoperation,membreoperation,soldeoperation);
		
		return theCompta;
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
		
		ComptaDAO dao = new ComptaDAO();
		System.out.println(dao.searchCompta("thom"));

		System.out.println(dao.getAllCompta());
	}

}
