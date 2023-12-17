package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminDAO {
	
		private Connection connection;

		public adminDAO(Connection connection) {
			super();
			this.connection = connection;
		}
		
		public admin getAdmin(String admin_name, String admin_password) {
			admin admin = null;
			String query = "select * from admin_1 where admin_name = ? and admin_password = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, admin_name);
	            preparedStatement.setString(2, admin_password);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                admin = new admin();
	                admin.setAdmin_Id(resultSet.getInt("admin_Id"));
	                admin.setAdmin_name(resultSet.getString("admin_name"));
	                admin.setAdmin_password(resultSet.getString("admin_password"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return admin;
	    }

}