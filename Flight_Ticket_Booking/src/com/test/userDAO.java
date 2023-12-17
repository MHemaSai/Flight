package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
	
		private Connection connection;

		public userDAO(Connection connection) {
			super();
			this.connection = connection;
		}
		
		public user getUser(String username, String password) {
			user user = null;
			String query = "select * from user_1 where user_name = ? and user_password = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                user = new user();
	                user.setId(resultSet.getInt("Id"));
	                user.setUsername(resultSet.getString("user_name"));
	                user.setPassword(resultSet.getString("user_password"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return user;
	    }

	    public void addUser(user user) {
	        String query = "insert into user_1(user_name, user_password) VALUES (?, ?)";

	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, user.getUsername());
	            preparedStatement.setString(2, user.getPassword());

	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		

}