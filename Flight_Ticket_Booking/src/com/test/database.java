package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
	private static final String url = "jdbc:mysql://localhost:3306/ticket_1";
	private static final String username = "root";
	private static final String password = "Hemasai@2002";
	
	public static Connection getconnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
}
