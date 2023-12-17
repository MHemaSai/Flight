package com.test;

import java.sql.Date;

public class flight {
	
		private int flight_Id;
	    private String flight_Name;
	    private String flight_Number;
	    private Date date;
	    private int seats;
	    private int available_seats;
		public flight(int flight_Id, String flight_Name, String flight_Number, Date date, int seats, int available_seats) {
			super();
			this.flight_Id = flight_Id;
			this.flight_Name = flight_Name;
			this.flight_Number = flight_Number;
			this.date = date;
			this.seats = seats;
			this.available_seats = available_seats;
		}
		public flight() {
			
		}
		public int getFlight_Id() {
			return flight_Id;
		}
		public void setFlight_Id(int flight_Id) {
			this.flight_Id = flight_Id;
		}
		public String getFlight_Name() {
			return flight_Name;
		}
		public void setFlight_Name(String flight_Name) {
			this.flight_Name = flight_Name;
		}
		public String getFlight_Number() {
			return flight_Number;
		}
		public void setFlight_Number(String flight_Number) {
			this.flight_Number = flight_Number;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getSeats() {
			return seats;
		}
		public void setSeats(int seats) {
			this.seats = seats;
		}
		
		public int getAvailable_seats() {
			return available_seats;
		}
		public void setAvailable_seats(int available_seats) {
			this.available_seats = available_seats;
		}
		public void add(flight flight) {
			
			
		}
	    
	    

}