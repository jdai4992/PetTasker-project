function Redirect(id) {
//	document.getElementById('iframe_a').src = id;
//	
//	location.href='/sydney/staff/manageUser';
//	
//	console.log(123456789);
	
	//document.getElementById('iframe_a').src = '/sydney/staff/manageUser/'+id;

	//$("but").click(function(){$("#iframe_a").attr("src", "/sydney/staff/manageUser/"+id)});
	
	location.href='/sydney/staff/manageUser';
	
	console.log('this is id in js ' + id);
	
}

//function to filter for users that match a searched user
function searchByUserNameFunction() {

	var searchInput = document.getElementById("searchInput");   // get the element with the id "searchInput"
	var searchedUser = searchInput.value.toLowerCase();   // convert input values to lowercase (so that the search is not restricted to case sensitive)

	var table = document.getElementById("userBox");   // get the element with the id "userBox"
	var tableRow = table.getElementsByTagName("tr");   // get all elements in the document with the tag name "tr"
	var tableRowLength = tableRow.length;
	var matchedRow = false;

	// iterate through all the rows in the table
	for (var i = 0; i < tableRowLength; i++) {
		
		var userColumn = tableRow[i].getElementsByTagName("td")[0];     // get all elements in the first column (in this case, username) with the tag name "td"
		console.log(userColumn)
		if (userColumn) {
			
			var changeTitleCase = userColumn.innerHTML.toLowerCase();	// change all the username to lowercase and assign it to "changeTitleCase"
			var result = changeTitleCase.indexOf(searchedUser);
			
			// check if userColumn contains the user's searched Title AND check if the user doesn't perform empty search
			if ((result >= 0) && (searchInput.value != "")) {
				matchedRow = true;
				tableRow[i].style.display = "";    // color the rows green that match the search value
			} else {
				tableRow[i].style.display = "none";   // leave the rows clear that don't match the search value
			}
		}     
	}
}

function resetFunction() {

	document.getElementById("searchInput").value = "";	// reset makes the searchTaskInput Bar clear

	var table = document.getElementById("userBox");   // get the element with the id "userBox"
	var tableRow = table.getElementsByTagName("tr");   // get all elements in the document with the tag name "tr"
	var tableRowLength = tableRow.length;

	// iterate through all the rows in the table and make the background clear and display all rows
	for (var i = 0; i < tableRowLength; i++) {
		tableRow[i].style.display = "";
	}
}

//windown.onload function is triggered when the entire page loads (including scripts, img and css)
window.onload = function() {
	// call the filter function when the user presses the "filterbtn" on the screen
	document.getElementById("searchBtn").onclick = searchByUserNameFunction;
	// call the reset function when the user presses the "resetBtn" on the screen
	document.getElementById("resetBtn").onclick = resetFunction;
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//REFERENCES
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//References used were:
//
//- W3schools.com. (2017). JavaScript Events. [online] Available at: https://www.w3schools.com/js/js_events.asp [Accessed 20 Oct. 2017].
//The above site was used as a reference to figure out how trigger a function when clicked
//
//- W3schools.com. (2017). How To Create a Filter/Search Table. [online] Available at: https://www.w3schools.com/howto/howto_js_filter_table.asp [Accessed 20 Oct. 2017].
//The above site was used as a reference to write searchFunction() and filterFunction() methods
//
//- based, D. (2017). Disappear the alert box with time based. [online] Stackoverflow.com. Available at: http://stackoverflow.com/questions/18650494/disappear-the-alert-box-with-time-based [Accessed 20 Oct. 2017].
//The above site was used as a reference to write filterSearchNotFound() method