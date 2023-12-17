package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signup {
	
		private Connection connection;

		public signup(Connection connection) {
			super();
			this.connection = connection;
		}
		
		public boolean signUp(String user_email,String user_name, String user_password,String phone_number) {
	        try {
	            // Check if the username is already taken
	            if (isUsernameTaken(user_name)) {
	                System.out.println("Username is already taken. Please choose another.");
	                return false;
	            }

	            
	            PreparedStatement statement = connection.prepareStatement(
	                    "INSERT INTO sign_up (user_email, user_name, user_password, phone_number, is_admin) VALUES (?, ?, ?, ?, ?)"
	            );
	            statement.setString(1, user_email);
	            statement.setString(2, user_name);
	            statement.setString(3, user_password);
	            statement.setString(4, phone_number);
	            statement.setBoolean(5, false); // Set as a regular user, not admin

	            int rowsAffected = statement.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println("User registration successful.");
	                return true;
	            } else {
	                System.out.println("User registration failed. Please try again.");
	                return false;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    private boolean isUsernameTaken(String user_name) {
	        try {
	            PreparedStatement statement = connection.prepareStatement(
	                    "SELECT * FROM sign_up WHERE user_name = ?"
	            );
	            statement.setString(1, user_name);

	            return statement.executeQuery().next();

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return true; 
	        }
	    }



}