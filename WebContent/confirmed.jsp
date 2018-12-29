<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title color="#FF0090">BHC - Booking</title>
	<link href="bhc-styles.css" rel="stylesheet" type ="text/css"/>
<jsp:useBean id="reservation" class="com.rbevans.bookingrate.Reservation" scope="session" />
</head>
<body text="#494a4c" link="#820b1e" vlink="#000000" alink="#dbdbdb" style="padding: 2% 30%">
<font face="Georgia" align="center">
<big><h1 align="center">Beartooth Hiking Company</h1></big>
 <hr/><br> 
<h3>Reserved</h3><br>
<center>
${reservation.hike}<br>
${reservation.guests}<br>
${reservation.start}<br>
${reservation.duration}<br>
${reservation.cost}<br>
<br>Thank you for choosing <a href= "bhc.html">Beartooth Hiking Company</a>!
</center>
</font>
</body>
</html>