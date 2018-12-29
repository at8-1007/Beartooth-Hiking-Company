/**
 * Dynamically updates and displays list of durations depending on hike selected.
 * 
 * @param selectedHike
 * @returns
 */
function getDuration(hike, durationList) {
	var validDurations; // stores array of valid durations for selected hike
	
	// assign array values based on hikeID of hike selected
	if(hike.value == "Gardiner Lake") {
		validDurations = ['3', '5'];
	}
	else if(hike.value == "Hellroaring Plateau") {
		validDurations = ['2', '3', '4'];
	}
	else {
		validDurations = ['5', '7'];
	}
	
	var i; // iterator
	
	// populate drop down menu with valid durations
	durationList.options.length = 0;
	for(i = 0; i < validDurations.length; i++) {
		createOption(durationList, validDurations[i], validDurations[i]);
	}
	
	durationList.className = 'show'; // display the menu of durations
}

/**
 * Dynamically updates list of days depending on month selected.
 * 
 * @param month
 * @param day
 * @returns
 */
function getDay(month, day) {
    switch (month.value) {
        case '7': // if July --> 31 days
        	day.options.length = 30; // keep current number of days
        	createOption(day, 31, 31); // but add one more day
            break;
        case '8': // if August --> 31 days
        	day.options.length = 30;
        	createOption(day, 31, 31);
            break;
        default: // if June or September, do nothing
            day.options.length = 30;
        	break;
    }
}

/**
 * Creates an option for the specified list.
 * 
 * @param select
 * @param text
 * @param value
 * @returns
 */
function createOption(select, text, value) {
    var opt = document.createElement('option'); // create option object
    opt.value = value; // set value of option
    opt.text = text; // set text of option
    select.options.add(opt); // add option to select
}

function verifyFields() {
	if(!document.form.gardiner.checked) {
		if(!document.form.hellroaring.checked) {
			if(!document.form.beaten.checked) {
				alert("Please select a hike followed by a duration.");
				return false;
			}
		}
	}
	return true;
}

/**
 * Checks if end date of hike is out of season.
 * 
 * @returns
 */
function checkDates() {
	var duration = document.form.duration.value - 1; // minus 1 since duration includes start date
	var month = document.form.month.value - 1; // minus 1 since months range from 0 to 11
	var day = document.form.day.value;
	var year = document.form.year.value;
	
	var seasonEnd = new Date(year, 9, 1); // season ends October 1st
	var startDate = new Date(year, month, day); // set start date
	var endDate = new Date(startDate.setDate(startDate.getDate() + duration)); // calculate and set end date
	
	// check if end date is out of season
	if(endDate > seasonEnd) {
		alert("End date is not within season.\n" 
				+ "Last day of the season is October 1st.\n" 
				+ "Please select a different start date.");
		return false; // don't submit form
	}	
	else {
		return true;
	}
}