package com.club.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.club.model.Activite;
import com.club.view.Home;

public class AuthenDAO {
	private Connection myConn;
		
		public AuthenDAO() throws Exception {
			
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
		
		public void logIn(String email, String password) throws Exception {
			/**List<Member> list = new ArrayList<Activite>();*/

			PreparedStatement myStmt = null;
			ResultSet myRs = null;

			try {
				myStmt = myConn.prepareStatement("select * from users where email like ? and password like ?");
				
				myStmt.setString(1, email);
				myStmt.setString(2, password);
				myRs = myStmt.executeQuery();
				
				String typeUser = myRs.getString("typeUser");
				if(typeUser == "admin") {
					Home windowAdmin = new Home();
				}else if (typeUser == "memberBureau") {
					Home windowMemberBureau = new Home();
				}else if (typeUser == "memberSimple") {
					Home windowMemberSimple = new Home();
				}else {
					System.err.println("Type d'utilisateur non defini !");;
				}
			}
			finally {
				close(myStmt, myRs);
			}
		}
		
		private void close(Statement myStmt, ResultSet myRs) throws SQLException {
			close(null, myStmt, myRs);		
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
}
