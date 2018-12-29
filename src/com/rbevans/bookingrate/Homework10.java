package com.rbevans.bookingrate;
/**
 * 
 * Created on July 24, 2018
 * Modified on August 2, 2018
 */


import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbevans.bookingrate.Reservation;
import com.rbevans.bookingrate.Rates.HIKE;

/**
 * This is the controller in the MVC2 model.
 * 
 * @author athom126
 */
@WebServlet(name="Homework10", urlPatterns="/Homework10")
public class Homework10 extends HttpServlet {
	
	/* instantiate variables to store parameters */
	private static final long serialVersionUID = 1L;
	String hike = "";
	int people = 0;
	int month = -1;
	int day = -1;
	int year = -1;
	int duration = -1;
	static Reservation reservation;
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	protected void doPost(HttpServletRequest request, 
						HttpServletResponse response) 
						throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		boolean goodInput = true;
		
		/* get and store input */
		try {
			hike = request.getParameter("hike"); // HIKE
			if(hike.equals(null) || hike == "") { 
				throw new NullPointerException(); // if no hike selected, throw an exception
			}
		} catch(NullPointerException e) {
			reservation = new Reservation("Please select a hike.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			goodInput = false;
		}
		
		try {
			people = Integer.parseInt(request.getParameter("people")); // GROUP SIZE
			if(people < 1 || people > 10) {
				throw new NumberFormatException();
			}
		} catch(NumberFormatException e) {
			reservation = new Reservation("Please select a group size between 1 and 10.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			goodInput = false;
		}
		
		try {
			month = Integer.parseInt(request.getParameter("month")); // MONTH
		} catch(NumberFormatException e) {
			reservation = new Reservation("Please select a month.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			goodInput = false;
		}
		try {
			day = Integer.parseInt(request.getParameter("day")); // DAY
		} catch(NumberFormatException e) {
			reservation = new Reservation("Please select a day.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			goodInput = false;
		}
		try {
			year = Integer.parseInt(request.getParameter("year")); // YEAR
		} catch(NumberFormatException e) {
			reservation = new Reservation("Please select a year.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			goodInput = false;
		}
		try {
			duration = Integer.parseInt(request.getParameter("duration")); // DURATION
		} catch(NumberFormatException e) {
			reservation = new Reservation("Please select a duration.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			goodInput = false;
		}
		
		/* process input */
		if(goodInput) {
			// check if the duration is valid for the selected hike
			boolean validLen = checkDuration(hike, duration, request, response);
			// if duration is valid, check dates and get cost
			if(validLen) {
				// create Rates object for hike
				Rates rate = createRatesObject(hike); 
				// calculate cost
				double cost = getCost(month, day, year, duration, rate, request, response);
				// if cost is 0.0, then bad begin and end dates
				if(cost == 0.0) {
					reservation = new Reservation("The begin and end dates for " + 
							"the tour should be between June 1st and October 1st. " + 
							"Please select a valid day for the selected month " + 
							"that is within season, or an appropriate duration for the selected hike.");
					request.setAttribute("reservation", reservation);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			        dispatcher.forward(request, response);
				}
				// cost is not 0.0 therefore display tour details
				else {
			        reservation = new Reservation(people, month, day, year, duration, cost, hike);
			        request.setAttribute("reservation", reservation);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmed.jsp");
			        dispatcher.forward(request, response);
				}
			}
		}
	}
	
	/** 
	 * 
	 * Creates a Rate object for the specified hike.
	 * 
	 * @param hike
	 * @return
	 */
	public static Rates createRatesObject(String hike) {
		// create a Rates object for the selected hike
		if(hike.contains("Gardiner Lake")) {
			return new Rates(HIKE.GARDINER);
		}
		else if(hike.contains("Hellroaring Plateau")) {
			return new Rates(HIKE.HELLROARING);
		}
		else {
			return new Rates(HIKE.BEATEN);
		}
	}
	
	/**
	 * 
	 * Checks if a valid duration was entered for specified hike.
	 * 
	 * @param hike
	 * @param duration
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException 
	 */
	public static boolean checkDuration(String hike, int duration, 
										HttpServletRequest request, 
										HttpServletResponse response) 
										throws IOException, ServletException {
		// check if selected duration is valid for selected hike
		if(hike.equals(null)) {
			reservation = new Reservation("Please select a hike.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			return false; // invalid duration so return false
		}
		else if(hike.contains("Gardiner Lake") && (duration != 3 && duration != 5)) {
			reservation = new Reservation("Please select a duration of 3 or 5.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			return false; // invalid duration so return false
		}
		else if(hike.contains("Hellroaring Plateau") && (duration != 2 && duration != 3 
														&& duration != 4)) {
			reservation = new Reservation("Please select a duration of 2, 3, or 4.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			return false;
		}
		else if(hike.contains("The Beaten Path") && (duration != 5 && duration != 7)) {
			reservation = new Reservation("Please select a duration of 5 or 7.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			return false;
		}
		else if(!hike.contains("Gardiner Lake") 
				&& !hike.contains("Hellroaring Plateau") 
				&& !hike.contains("The Beaten Path")) {
			reservation = new Reservation("Please select a valid hike and/or duration.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			return false;
		}
		else {
			return true; // valid duration for selected hike so return true
		}
	}
	
	/**
	 * 
	 * Determines if the start date is valid and within season and returns the cost.
	 * 
	 * @param month
	 * @param day
	 * @param year
	 * @param numDays
	 * @param rate
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public static double getCost(int month, int day, int year, 
								int numDays, Rates rate,
								HttpServletRequest request, 
								HttpServletResponse response) throws ServletException, IOException {
		// check if valid calendar day
		BookingDay bookingDay = new BookingDay(year, month, day);
		if(!bookingDay.isValidDate()) {
			reservation = new Reservation("Please select a valid date.");
			request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			return 0.0; // return 0
		}	
		// check if begin and end dates are within season
		rate.setBeginDate(bookingDay); // set begin date
		rate.setDuration(numDays); // set duration
		GregorianCalendar endDate = bookingDay.getDate(); 
		endDate.add(Calendar.DAY_OF_MONTH, numDays - 1); // set end date
	    BookingDay lastDay = new BookingDay(endDate.get(Calendar.YEAR),
	    endDate.get(Calendar.MONTH) + 1,
	    endDate.get(Calendar.DAY_OF_MONTH));
	    if(!rate.isValidDates()) {
	    	reservation = new Reservation("Please select a date that is within season.");
	    	request.setAttribute("reservation", reservation);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        dispatcher.forward(request, response);
			return 0.0; // return 0
		}
	    return rate.getCost(); // return cost
	}
}
