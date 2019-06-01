package com.club.dao;

import java.util.*;

import com.club.model.Event;

import java.sql.*;
import java.io.*;
import java.math.BigDecimal;


public class EventDAO {

	private Connection myConn;
	
	public EventDAO() throws Exception {
		
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
	
	public void deleteEvent(int eventId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from evenements where id_evenement=?");
			
			// set param
			myStmt.setInt(1, eventId);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	
	public void updateEvent(Event theEvent) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update evenements"
					+ " set nom_evenement=?, objectif_evenement=?, date_evenement=?, description_evenement=?"
					+ " where id_evenement=?");
			
			// set params
			myStmt.setString(1, theEvent.getNomevent());
			myStmt.setString(2, theEvent.getObjectifevent());
			myStmt.setString(3, theEvent.getDateevent());
			myStmt.setString(4, theEvent.getDescriptionevent());
			myStmt.setInt(5, theEvent.getId());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	public void addEvent(Event theEvent) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into evenements"
					+ " (nom_evenement, objectif_evenement, date_evenement, description_evenement)"
					+ " values (?, ?, ?, ?)");
			
			// set params
			myStmt.setString(1, theEvent.getNomevent());
			myStmt.setString(2, theEvent.getObjectifevent());
			myStmt.setString(3, theEvent.getDateevent());
			myStmt.setString(4, theEvent.getDescriptionevent());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	public List<Event> getAllEvent() throws Exception {
		List<Event> list = new ArrayList<Event>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from evenements order by nom_evenement");
			
			while (myRs.next()) {
				Event tempEvent = convertRowToEvent(myRs);
				list.add(tempEvent);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Event> searchEvent(String nomevent) throws Exception {
		List<Event> list = new ArrayList<Event>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			nomevent += "%";
			myStmt = myConn.prepareStatement("select * from evenements where nom_evenement like ?  order by nom_evenement");
			
			myStmt.setString(1, nomevent);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Event tempEvent = convertRowToEvent(myRs);
				list.add(tempEvent);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Event convertRowToEvent(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id_evenement");
		String nomevent = myRs.getString("nom_evenement");
		String objectifevent = myRs.getString("objectif_evenement");
		String dateevent = myRs.getString("date_evenement");
		String descriptionevent = myRs.getString("description_evenement");
		
		Event tempEvent = new Event(id,nomevent, objectifevent, dateevent, descriptionevent);
		
		return tempEvent;
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
		
		EventDAO dao = new EventDAO();
		System.out.println(dao.searchEvent("thom"));

		System.out.println(dao.getAllEvent());
	}

}
