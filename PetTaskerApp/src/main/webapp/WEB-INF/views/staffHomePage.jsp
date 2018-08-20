<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Staff Home Page</title>

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
				<div class="page-heading">Staff Home Page</div>
				<div class="btn-float-right">
					<!-- <a href="/sydney/newTask" class="btn btn-primary btn-sm">Edit</a> -->
				</div>
				<!-- <div class="section-heading">Task Details</div> -->

				<h2>Welcome to your homepage ${currentStaff.getFullName()}!</h2>
				<h3>You are successfully logged in!</h3>
				<br /> <br />

				<div class="container">
					<div class="row">
						<div class="col">Staff Id: ${currentStaff.getId()}</div>
						<div class="col">Staff Name: ${currentStaff.getFullName()}</div>
						<div class="col">Technical Staff: ${currentStaff.getTechStatus()}</div>
					</div>
					<div class="row">
						<div class="col">Staff Email: ${currentStaff.getEmail()}</div>
						<div class="col">Staff Contact Number: ${currentStaff.getPhoneNumber()}</div>
					</div>
				</div>
				<br /> <br />
				<div class="container-fluid">
					<div class="row">
						<div style="width: 100%; float: right">

							<h3>Reports resolved:</h3>
							<table class="table table-hover">
								<col width="30%">
								<col width="70%">
								<tr>
									<th>Report Id</th>
									<th>Report Title</th>
								</tr>
								<c:forEach var="report" items="${resolvedReports}">
									<tr class='clickable-row'
							onclick="window.location.href='/sydney/staff/staffReport/${report.getReportId()}'">
										<td>${report.getReportId()}</td>
										<td>${report.getTitle()}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>

	<!-- <a href="/sydney/staff/logout">Logout</a>    |    <a href="/sydney/staff/staffReport">Report List</a>  -->

</body>
</html>
