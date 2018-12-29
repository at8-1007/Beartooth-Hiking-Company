/**
 * Created: 08/02/18
 */
package com.rbevans.bookingrate;

import java.io.Serializable;

/**
 * This is the Java Bean for MVC2.
 * 
 * @author athom126
 *
 */
public class Reservation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String guests = null; // holds group size
	private String start = null; // var to store date as a String
	private String duration = null; // var to hold duration
	private String cost = null; // var to hold cost
	private String hike = null; // var to hold selected hike
	private String error = null; // holds error message
	
	/**
	 * Default empty constructor
	 */
	public Reservation() {}
	
	/**
	 * 
	 * @param guests
	 * @param month
	 * @param day
	 * @param year
	 * @param duration
	 * @param cost
	 * @param hike
	 */
	public Reservation(int guests, int month, int day, 
			int year, int duration, double cost, String hike) {		
		this.setGuests(guests);
		this.setStart(month, day, year);
		this.setDuration(duration);
		this.setCost(cost);
		this.setHike(hike);
	}
	
	public Reservation(String error) {
		this.setError(error);
	}

	/**
	 * 
	 * @return
	 */
	public String getGuests() {
		return guests;
	}

	/**
	 * 
	 * @param guests
	 * @return 
	 */
	private void setGuests(int guests) {
		if(guests == 1) {
			this.guests = "Group Size: " + guests + " person";
		}
		else {
			this.guests = "Group Size: " + guests + " people";
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getStart() {
		return start;
	}

	/**
	 * 
	 * @param month
	 * @param day
	 * @param year
	 */
	private void setStart(int month, int day, int year) {
		this.start = "Start Date: " + month+"/"+day+"/"+year;
	}

	/**
	 * 
	 * @return
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * 
	 * @param duration
	 */
	private void setDuration(int duration) {
		this.duration = "Duration: " + duration + " days";
	}

	/**
	 * 
	 * @return
	 */
	public String getCost() {
		return cost;
	}

	/**
	 * 
	 * @param cost
	 */
	private void setCost(double cost) {
		this.cost = "Total: $" + cost;
	}

	/**
	 * 
	 * @return
	 */
	public String getHike() {
		return hike;
	}

	/**
	 * 
	 * @param hike
	 */
	private void setHike(String hike) {
		this.hike = "Hike: " + hike;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	private void setError(String error) {
		this.error = error;
	}
}
