<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Reported User Profile</title>

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
				<div class="page-heading">User Profile List</div>
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
				<form method="get" action="">
					<div id="searchBox">
						<input type="search" id="searchInput" placeholder="Search User Names">
						<!-- search submit button -->
						<input type="button" value="Search" id="searchBtn">
						<!-- reset button -->
						<input type="button" value="Reset" id="resetBtn">
					</div>	
					
					<div id="userBox">
						<table class="table table-hover">
							<tr>
								<th>User Id</th>
								<th>User Name</th>
								<th>User City</th>
								<th>User State</th>
								<th>User Country</th>
							</tr>
							<c:forEach var="user" items="${users}">
								<tr class='clickable-row'
									onclick="window.location.href='/sydney/staff/manageUser/${user.getId()}'">
									<th>${user.getId()}</th>
									<td>${user.getFullName()}</td>
									<td>${user.getCity()}</td>
									<td>${user.getState()}</td>
									<td>${user.getCountry()}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</form>
			</div>
			<%@ include file="footer.jsp"%>
		</div>
	</div>
	<script src="<c:url value="/resource/jss/staffScript.js" />"></script>
</body>
</html>