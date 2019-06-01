package com.club.dao;

import java.util.*;

import com.club.model.Activite;

import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

public class ActiviteDAO {

	private Connection myConn;
	
	public ActiviteDAO() throws Exception {
		
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
	
	public void deleteActivite(int activiteId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from activites where idactivite=?");
			
			// set param
			myStmt.setInt(1, activiteId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	
	public void updateActivite(Activite theActivite) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update activites"
					+ " set nomactivite=?, membreactivite=?, datedebutactivite=?, datefinactivite=?, descriptionactivite=?"
					+ " where idactivite=?");
			
			// set params
			myStmt.setString(1, theActivite.getMembreactivite());
			myStmt.setString(2, theActivite.getNomactivite());
			myStmt.setString(3, theActivite.getDatedebut());
			myStmt.setString(4, theActivite.getDatefin());
			myStmt.setString(5, theActivite.getDescription());
			myStmt.setInt(7, theActivite.getId());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	public void addActivite(Activite theActivite) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into activites"
					+ " (nomactivite, membreactivite, datedebutactivite ,datefinactivite,descriptionactivite)"
					+ " values (?, ?, ?, ?, ?)");
			
			// set params
			myStmt.setString(1, theActivite.getMembreactivite());
			myStmt.setString(2, theActivite.getNomactivite());
			myStmt.setString(3, theActivite.getDatedebut());
			myStmt.setString(4, theActivite.getDatefin());
			myStmt.setString(5, theActivite.getDescription());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	public List<Activite> getAllActivite() throws Exception {
		List<Activite> list = new ArrayList<Activite>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from activites order by nomactivite");
			
			while (myRs.next()) {
				Activite theActivite = convertRowToActivite(myRs);
				list.add(theActivite);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Activite> searchActivite(String nomactivite) throws Exception {
		List<Activite> list = new ArrayList<Activite>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			nomactivite += "%";
			myStmt = myConn.prepareStatement("select * from activites where nomactivite like ?  order by nomactivite");
			
			myStmt.setString(1, nomactivite);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Activite theActivite = convertRowToActivite(myRs);
				list.add(theActivite);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Activite convertRowToActivite(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("idactivite");
		String nomactivite = myRs.getString("nomactivite");
		String membreactivite = myRs.getString("membreactivite");
		String datedebut = myRs.getString("datedebutactivite");
		String datefin = myRs.getString("datefinactivite");
		String description = myRs.getString("descriptionactivite");

		
		Activite theActivite = new Activite(id, nomactivite, membreactivite, datedebut,datefin,description);
		
		return theActivite;
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
		
		ActiviteDAO dao = new ActiviteDAO();
		System.out.println(dao.searchActivite("thom"));

		System.out.println(dao.getAllActivite());
	}

}
