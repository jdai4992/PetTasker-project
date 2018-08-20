<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>User Profile</title>

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

				<div class="text-right">
					<a href="/sydney/staff/manageUser">Back to Manage User</a>
				</div>

				<div class="page-heading">User: ${user.getFullName()}</div>

				<!--
				<c:choose>
					<c:when test="${user == null}">
						<iframe src="/sydney/staff/manageUser/staffUserDetail"
							id="iframe_a" name="iframe_a" width="80%" height="60%"
							style="display: none;"></iframe>
					</c:when>
					<c:otherwise>

							Reported User: ${user.getFullName()} <br /> <br />

							<div>User Id: ${user.getId()}</div>
							<br />
							<div>User Address: ${user.getAddress()}</div>
							<br />
							<div>User City: ${user.getCity()}</div>
							<br />
							<div>User State: ${user.getState()}</div>
							<br />
							<div>User Postcode: ${user.getPostcode()}</div>
							<br />
							<div>User Country: ${user.getCountry()}</div>
							<br />
							<div>User Phone Number: ${user.getPhoneNumber()}</div>
							<br />
							<div>User Email: ${user.getEmail()}</div>
						</div>
					</c:otherwise>

				</c:choose>
				-->

				<table class="table">
					<col width="30%">
					<col width="70%">
					<tr>
						<th>User Id</th>
						<td>${user.getId()}</td>
					</tr>
					<tr>
						<th>User Email</th>
						<td>${user.getEmail()}</td>
					</tr>
					<tr>
						<th>User Phone Number</th>
						<td>${user.getPhoneNumber()}</td>
					</tr>
					<tr>
						<th>User Address</th>
						<td>${user.getAddress()}</td>
					</tr>
					<tr>
						<th>User City</th>
						<td>${user.getCity()}</td>
					</tr>
					<tr>
						<th>User State</th>
						<td>${user.getState()}</td>
					</tr>
					<tr>
						<th>User Postcode</th>
						<td>${user.getPostcode()}</td>
					</tr>
					<tr>
						<th>User Country</th>
						<td>${user.getCountry()}</td>
					</tr>
				</table>

				<br /> <br />

				<div class="page-heading">User's Task Details</div>
				<div class="container-fluid">
					<div class="row">
						<div style="width: 100%; float: right">

							<h3>Tasks Posted:</h3>
							<table class="table table-hover">
								<col width="70%">
								<col width="30%">
								<tr>
									<th>Title</th>
									<th>Status</th>
								</tr>
								<c:forEach var="task" items="${addedTasks}">
									<tr class='clickable-row'
							onclick="window.location.href='/sydney/staff/manageTask/${task.getId()}'">
										<td>${task.getTaskName()}</td>
										<td>${task.getTaskStatus()}</td>
									</tr>
								</c:forEach>

							</table>

							<h3>Tasks Applied:</h3>
							<table class="table table-hover">
								<col width="70%">
								<col width="30%">
								<tr>
									<th>Title</th>
									<th>Status</th>
								</tr>
								<c:forEach var="task" items="${appliedTasks}">
									<tr class='clickable-row'
							onclick="window.location.href='/sydney/staff/manageTask/${task.getId()}'">
										<td>${task.getTaskName()}</td>
										<td>${task.getTaskStatus()}</td>
									</tr>
								</c:forEach>

							</table>

							<a class="btn btn-default btn-float-right"
								href="/sydney/staff/manageUser/${user.getId()}/delete"
								role="button">Delete Account <span
								class="glyphicon glyphicon-trash"></span></a>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>