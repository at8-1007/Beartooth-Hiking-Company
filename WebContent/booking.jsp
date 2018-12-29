<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="formValidation.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" http-equiv="Cache-control" content="no-cache" />
	<title color="#FF0090">BHC - Booking</title>
	<link href="bhc-styles.css" rel="stylesheet" type ="text/css"/>
</head>
<body text="#494a4c" link="#820b1e" vlink="#000000" alink="#dbdbdb" style="padding: 2% 30%">
<font face="Georgia" align="center">
 <form action="Homework10" method=POST name="form" autocomplete="off" onsubmit="return (verifyFields() && checkDates());">
<big><h1 align="center">Beartooth Hiking Company</h1></big>
 <hr/><br> 
<h3>Booking</h3><br>
Hike:
	<input type="radio" name="hike" id="gardiner" value="Gardiner Lake" onclick="getDuration(this, document.getElementById('duration'))">Gardiner Lake
	<input type="radio" name="hike" id="hellroaring" value="Hellroaring Plateau" onclick="getDuration(this, document.getElementById('duration'))">Hellroaring Plateau
	<input type="radio" name="hike" id="beaten" value="The Beaten Path" onclick="getDuration(this, document.getElementById('duration'))">The Beaten Path
<br><br>
Group Size: 
<select name="people" id="people">
		<option value=1>1 person</option>
		<option value=2>2 people</option>
		<option value=3>3 people</option>
		<option value=4>4 people</option>
		<option value=5>5 people</option>
		<option value=6>6 people</option>
		<option value=7>7 people</option>
		<option value=8>8 people</option>
		<option value=9>9 people</option>
		<option value=10>10 people</option>
</select>
<br><br>
Start Date:
	<select name="month" id="month" onchange="getDay(this, document.getElementById('day'))">
		<option value="6" >June</option>
		<option value="7">July</option>
		<option value="8">August</option>
		<option value="9">September</option>
	</select>
	<select name="day" id="day">
		<option value=1>1</option>
		<option value=2>2</option>
		<option value=3>3</option>
		<option value=4>4</option>
		<option value=5>5</option>
		<option value=6>6</option>
		<option value=7>7</option>
		<option value=8>8</option>
		<option value=9>9</option>
		<option value=10>10</option>
		<option value=11>11</option>
		<option value=12>12</option>
		<option value=13>13</option>
		<option value=14>14</option>
		<option value=15>15</option>
		<option value=16>16</option>
		<option value=17>17</option>
		<option value=18>18</option>
		<option value=19>19</option>
		<option value=20>20</option>
		<option value=21>21</option>
		<option value=22>22</option>
		<option value=23>23</option>
		<option value=24>24</option>
		<option value=25>25</option>
		<option value=26>26</option>
		<option value=27>27</option>
		<option value=28>28</option>
		<option value=29>29</option>
		<option value=30>30</option>
		<!-- getDay() adds 31 as an option for July and August -->
	</select>
	<select name="year">
		<option value=2007>2007</option>
		<option value=2008>2008</option>
		<option value=2009>2009</option>
		<option value=2010>2010</option>
		<option value=2011>2011</option>
		<option value=2012>2012</option>
		<option value=2013>2013</option>
		<option value=2014>2014</option>
		<option value=2015>2015</option>
		<option value=2016>2016</option>
		<option value=2017>2017</option>
		<option value=2018>2018</option>
		<option value=2019>2019</option>
		<option value=2020>2020</option>
	</select>
<br><br>

Duration:
	<select name="duration" id="duration" class="hide">
	<!-- getDuration() populates and displays the menu of valid durations for the selected hike -->
	</select>
<br><br><br>
<input type="submit" name="submit" value="Reserve"/><br><br>
<h5>To return to the home page, click <a href="bhc.html">here</a>.</h5>
 </form>
 </font>
</body>
</html>