package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class application {
	
		
		    private static final String url = "jdbc:mysql://localhost:3306/ticket_1";
		    private static final String username = "root";
		    private static final String password = "Hemasai@2002";

		    public static void main(String[] args) {
		        try (Connection connection = DriverManager.getConnection(url, username, password)) {
		        	signup signup = new signup(connection);
		            userDAO userDAO = new userDAO(connection);
		            adminDAO adminDAO = new adminDAO(connection);
		            flightDAO flightDAO = new flightDAO(connection);
		            bookingDAO bookingDAO = new bookingDAO(connection);

		            Scanner scanner = new Scanner(System.in);

		            while (true) {
		            	System.out.println("1. Sign Up");
		            	System.out.println("2. User Login");
		                System.out.println("3. Admin Login");
		                System.out.println("4. Exit");
		                System.out.print("Enter your choice: ");
		                int choice = scanner.nextInt();

		                switch (choice) {
		                    case 1:
		                    	sign_up(scanner, signup);
		                    	break;
		                    case 2:
		                        userLogin(userDAO, scanner, bookingDAO, flightDAO);
		                        break;
		                    case 3:
		                        adminLogin(adminDAO, flightDAO, bookingDAO, scanner);
		                        break;
		                    case 4:
		                        System.out.println("Exiting the application. Have a good day!");
		                        System.exit(0);
		                    default:
		                        System.out.println("Invalid choice. Please enter a valid option.");
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    private static void sign_up(Scanner scanner, signup signup) {
		    	System.out.println("Enter email address:");
		    	String useremail = scanner.next();
		        System.out.println("Enter username:");
		        String username = scanner.next();
		        System.out.println("Enter password:");
		        String password = scanner.next();
		        System.out.println("Enter phone number:");
		        String phone_number = scanner.next();

		        signup.signUp(useremail, username, password, phone_number);
		        
		    }
		    private static void userLogin(userDAO userDAO, Scanner scanner, bookingDAO bookingDAO, flightDAO flightDAO) {
		        System.out.print("Enter your username: ");
		        String username = scanner.next();
		        System.out.print("Enter your password: ");
		        String password = scanner.next();

		        user user = userDAO.getUser(username, password);

		        if (user != null) {
		            System.out.println("User login successful!");
		            userMenu(user, flightDAO, bookingDAO, scanner);
		        } else {
		            System.out.println("Invalid username or password. Please try again.");
		        }
		    }

		    
			private static void userMenu(user user, flightDAO flightDAO, bookingDAO bookingDAO, Scanner scanner) {
		        while (true) {
		            System.out.println("1. Search Flights");
		            System.out.println("2. Book a Flight");
		            System.out.println("3. Logout");
		            System.out.print("Enter your choice: ");
		            int choice = scanner.nextInt();

		            switch (choice) {
		                case 1:
		                    searchFlights(flightDAO, scanner);
		                    break;
		                case 2:
		                    bookFlight(user.getId(), bookingDAO, scanner);
		                    break;
		                case 3:
		                    System.out.println(user.getUsername() + " Logging out "+ "!");
		                    return;
		                default:
		                    System.out.println("Invalid choice. Please enter a valid option.");
		            }
		        }
		    }

		    private static void searchFlights(flightDAO flightDAO, Scanner scanner) {
		        System.out.print("Enter the (Flight Name/Date/Flight Number): ");
		        String searchTerm = scanner.next();

		        List<flight> flights = flightDAO.searchFlights(searchTerm);
		        

		        if (flights.isEmpty()) {
		            System.out.println("No flights found for the given search term.");
		        } else {
		            System.out.println("Found flights:");
		            for (flight flight : flights) {
		                System.out.println(flight.toString());
		            }
		        }
		    }

		    private static void bookFlight(int user_Id, bookingDAO bookingDAO, Scanner scanner) {
		        System.out.println("Enter the Flight ID you want to book: ");
		        int flight_Id = scanner.nextInt();
		        System.out.println("Enter the Flight Name you want to book: ");
		        String flight_Name = scanner.next();
		        
		        
		       
		        if (isSeatAvailable(flight_Id)) {
		            try (Connection connection = DriverManager.getConnection(url, username, password)) {
		                
		                String bookFlightQuery = "INSERT INTO booking_1(flight_Id, flight_Name) VALUES (?, ?)";
		                try (PreparedStatement bookFlightStatement = connection.prepareStatement(bookFlightQuery)) {
		                    bookFlightStatement.setInt(1, flight_Id);
		                    bookFlightStatement.setString(2, flight_Name);
		                    bookFlightStatement.executeUpdate();
		                }

		                
		                String updateSeatsQuery = "UPDATE flight_1 SET available_seats = available_seats - 1 WHERE flight_Id = ?";
		                try (PreparedStatement updateSeatsStatement = connection.prepareStatement(updateSeatsQuery)) {
		                    updateSeatsStatement.setInt(1, flight_Id);
		                    updateSeatsStatement.executeUpdate();
		                }

		                System.out.println("Booking successful! Enjoy your flight!");
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        } else {
		            System.out.println("Sorry, the flight is fully booked. Please choose another flight.");
		        }
		    }

		    
		    private static boolean isSeatAvailable(int flightId) {
		        
		        String query = "SELECT available_seats FROM flight_1 WHERE flight_Id = ?";
		        try (Connection connection = DriverManager.getConnection(url, username, password);
		             PreparedStatement statement = connection.prepareStatement(query)) {
		            statement.setInt(1, flightId);
		            ResultSet resultSet = statement.executeQuery();
		            if (resultSet.next()) {
		                int availableSeats = resultSet.getInt("available_seats");
		                return availableSeats > 0;
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return false;
		    }


		    
		    

		    private static void adminLogin(adminDAO adminDAO, flightDAO flightDAO, bookingDAO bookingDAO, Scanner scanner) {
		    	System.out.print("Enter your username (admin): ");
		        String admin_name = scanner.next();
		        System.out.print("Enter your password (admin): ");
		        String admin_password = scanner.next();

		        admin admin = adminDAO.getAdmin(admin_name, admin_password);

		        if (admin != null) {
		            System.out.println("Admin login successful!");
		            adminMenu(flightDAO, bookingDAO, scanner);
		        } else {
		            System.out.println("Invalid admin credentials. Please try again.");
		        }
		    }

		    private static void adminMenu(flightDAO flightDAO, bookingDAO bookingDAO, Scanner scanner) {
		        while (true) {
		            System.out.println("1. Add a Flight");
		            System.out.println("2. View Bookings");
		            System.out.println("3. Logout");
		            System.out.print("Enter your choice: ");
		            int choice = scanner.nextInt();

		            switch (choice) {
		                case 1:
		                    addFlight(flightDAO, scanner);
		                    break;
		                case 2:
		                    viewBookings(bookingDAO, scanner);
		                    break;
		                case 3:
		                    System.out.println("Logging out. Goodbye, admin!");
		                    return;
		                default:
		                    System.out.println("Invalid choice. Please enter a valid option.");
		            }
		        }
		    }

		    private static void addFlight(flightDAO flightDAO, Scanner scanner) {
		        System.out.print("Enter Flight Name: ");
		        String flightName = scanner.next();
		        System.out.print("Enter Flight Number: ");
		        String flightNumber = scanner.next();
		        System.out.print("Enter Date (yyyy-MM-dd): ");
		        String dateString = scanner.next();

		        try {
		            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		            flight newFlight = new flight();
		            newFlight.setFlight_Name(flightName);
		            newFlight.setFlight_Number(flightNumber);
		            newFlight.setDate(sqlDate);

		            flightDAO.addFlight(newFlight);
		            System.out.println("Flight added successfully!");
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void viewBookings(bookingDAO bookingDAO, Scanner scanner) {
		        System.out.println("Filter Bookings by:");
		        System.out.println("1. flight_Id");
		        System.out.println("2. flight_Name");
		        System.out.println("3. date");
		        System.out.print("Enter your choice: ");

		        int filterOption = scanner.nextInt();
		        scanner.nextLine(); 

		        String filterValue = "";
		        if (filterOption == 1){
		            System.out.print("Enter the flight Id : ");
		            filterValue = scanner.nextLine();
		        } else if (filterOption == 2) {
		            System.out.print("Enter the flight Name: ");
		            filterValue = scanner.nextLine();
		        } else if (filterOption == 3) {
		        	System.out.print("Enter the date: ");
		        	filterValue = scanner.nextLine();
		        }

		        List<booking> bookings = bookingDAO.viewBookings(filterOption, filterValue);

	            if (bookings.isEmpty()) {
	                System.out.println("No bookings found.");
	            } else {
	                System.out.println("Bookings:");
	                for (booking booking : bookings) {
	                    System.out.println(booking);
	                }
	            }

	        } 

}