package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class bookingDAO {
	
		private Connection connection;

		public bookingDAO(Connection connection) {
			super();
			this.connection = connection;
		}
		
		public List<booking> viewBookings(int filterOption, String filterValue) {
			List<booking> bookings = new ArrayList<>();
	        String query = "SELECT * FROM booking_1";

	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                com.test.booking booking = new com.test.booking();
	                booking.setBooking_Id(resultSet.getInt("booking_Id"));
	                booking.setFlight_Id(resultSet.getInt("flight_Id"));
	                booking.setFlight_Name(resultSet.getString("flight_Name"));
	                booking.setAvailable_Seats(resultSet.getInt("available_seats"));
	                bookings.add(booking);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return bookings;
	    }


		public void bookFlight(int userId, int flightId, String flightName) {
			
			
		}

		public List<booking> booking(int user_Id) {
			
			return null;
		}


}