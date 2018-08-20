<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Reported Task List</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link href="<c:url value="/resource/css/style.css" />" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<%@ include file="navBarStaff.jsp"%>

	<div id="backgroundImage">
		<div class="container">
			<div class="jumbotron">
				<div class="page-heading">Reported Task List</div>
				<!-- 
				<section style="float: left; width: 20%;">
					<p>Hm... This is weird... this is where search bar should be</p>
				</section>
				
				<section>
					<iframe src="/sydney/staff/manageUser/staffUserDetail"
						id="iframe_a" name="iframe_a" width="80%" height="60%"
						style="float: right;"></iframe>
				</section>
				 -->
				 
				 <!-- Need to customize for tasks -->
				<form method="get" action="">
					<div id="taskSearchBox">
						<input type="search" id="searchTaskInput" placeholder="Search Task Id">
						<!-- search submit button -->
						<input type="button" value="Search" id="taskSearchBtn">
						<!-- reset button -->
						<input type="button" value="Reset" id="resetBtn">
					</div>

					<div id="taskBox">
						<table class="table table-hover">
							<tr>
								<th>Task Id</th>
								<th>Task Name</th>
								<th>Task Poster</th>
								<th>Task Posted Date</th>
								<th>Task Status</th>
							</tr>
							<c:forEach var="task" items="${tasks}">
								<tr class='clickable-row'
									onclick="window.location.href='/sydney/staff/manageTask/${task.getId()}'">
									<td><strong>${task.getId()}</strong></td>
									<td>${task.getTaskName()}</td>
									<td>${task.getAdderId()}</td>
									<td>${task.getStartDate()}</td>
									<td>${task.getTaskStatus()}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</form>
			</div>
			<%@ include file="footer.jsp"%>
		</div>
	</div>
	<script>
	//function to filter for task that match a searched taskId
	function searchByTaskIdFunction() {

		var searchTaskInput = document.getElementById("searchTaskInput");   // get the element with the id "searchTaskInput"
		var searchedUser = searchTaskInput.value.toLowerCase();   // convert input values to lowercase (so that the search is not restricted to case sensitive)

		var table = document.getElementById("taskBox");   // get the element with the id "userBox"
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
				if ((result >= 0) && (searchTaskInput.value != "")) {
					matchedRow = true;
					tableRow[i].style.display = "";    // color the rows green that match the search value
				} else {
					tableRow[i].style.display = "none";   // leave the rows clear that don't match the search value
				}
			}     
		}
	}

	function resetFunction() {

		document.getElementById("searchTaskInput").value = "";	// reset makes the searchTaskInput Bar clear

		var table = document.getElementById("taskBox");   // get the element with the id "userBox"
		var tableRow = table.getElementsByTagName("tr");   // get all elements in the document with the tag name "tr"
		var tableRowLength = tableRow.length;

		// iterate through all the rows in the table and make the background clear and display all rows
		for (var i = 0; i < tableRowLength; i++) {
			tableRow[i].style.display = "";
		}
	}

	//windown.onload function is triggered when the entire page loads (including scripts, img and css)
	window.onload = function() {
		document.getElementById("taskSearchBtn").onclick = searchByTaskIdFunction;
		// call the reset function when the user presses the "resetBtn" on the screen
		document.getElementById("resetBtn").onclick = resetFunction;
	}
	</script>
</body>
</html>