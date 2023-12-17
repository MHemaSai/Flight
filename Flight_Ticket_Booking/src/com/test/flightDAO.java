package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class flightDAO {
	
		private Connection connection;

		public flightDAO(Connection connection) {
			super();
			this.connection = connection;
		}
		
		public List<flight> searchFlights(String searchTerm) {
	        List<flight> flights = new ArrayList<>();
	        String query = "select * from flight_1 where flight_Name LIKE ? OR date LIKE ? OR flight_Number LIKE ?  AND available_seats > 0";

	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            String likeTerm = "%" + searchTerm + "%";
	            preparedStatement.setString(1, likeTerm);
	            preparedStatement.setString(2, likeTerm);
	            preparedStatement.setString(3, likeTerm);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                flight flight = new flight();
	                flight.setFlight_Id(resultSet.getInt("flight_Id"));
	                flight.setFlight_Name(resultSet.getString("flight_Name"));
	                flight.setFlight_Number(resultSet.getString("flight_Number"));
	                flight.setDate(resultSet.getDate("date"));
	                flight.setSeats(resultSet.getInt("seats"));
	                flight.setSeats(resultSet.getInt("available_seats"));
	                flights.add(flight);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return flights;
	    }

	    public void addFlight(flight flight) {
	        String query = "INSERT INTO flight_1 (flight_name, flight_number, date) VALUES (?, ?, ?)";

	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, flight.getFlight_Name());
	            preparedStatement.setString(2, flight.getFlight_Number());
	            preparedStatement.setDate(3, flight.getDate());
	            

	            preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	   

}