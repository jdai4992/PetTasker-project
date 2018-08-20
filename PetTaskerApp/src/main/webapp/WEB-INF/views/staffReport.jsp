<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Staff's Report List Page</title>

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
				<div class="page-heading">List of User Complaints/Reports</div>

				<%--  		${resultMessage} --%>

				<table class="table table-hover">
					<tr>
						<th>Report Id</th>
						<th>Category</th>
						<th>Title</th>
						<th>Reporter</th>
						<th>Task Status</th>
						<!--  <th>Report Status</th>-->
					</tr>
					<c:forEach var="report" items="${reports}">
						<tr class='clickable-row'
							onclick="window.location.href='staffReport/${report.getReportId()}'">
							<th>${report.getReportId()}</th>
							<td>${report.getReason()}</td>
							<!-- need to figure out date -->
							<td>${report.getTitle()}</td>
							<td>${report.getReporterId()}</td>
							<td>${report.getReportStatus()}</td>
						</tr>
					</c:forEach>
				</table>

				<!-- <a href="/sydney/staff/staffHomePage">Homepage</a>    |    <a href="/">Manage Task</a>    |    <a href="/">Manage User</a> -->
			</div>
			<%@ include file="footer.jsp"%>
		</div>
	</div>
</body>
</html>
