package com.club.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Displayusers {
	
	
private Connection myConn;
	
	public Displayusers() throws Exception {
		
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
	
	//verification des textes inserees
	

}

