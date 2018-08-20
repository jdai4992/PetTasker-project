<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Staff Login</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link href="<c:url value="/resource/css/style.css" />" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<div id="logo" class="">Welcome to PetTasker</div>
		<div class="container" align="center">
		    <div class="cardStaff">
		        <div class="card-block">		    			
					<h2 class="form-signin-heading loginRegisterTitle login-title"><b>PetTasker Staff Sign-in</b></h2>
					<hr>
					
					<form:form commandName="staff">
				
						<div class="inputFields">Email</div>
						<div class="input-group">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-envelope"></span>
							</span>
							<form:input placeholder="Email" path="email" class="form-control" />
						</div>
						<form:errors path="email" cssClass="text-danger" />
				
						<br />
						<div class="inputFields">Password</div>
						<div class="input-group">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-lock"></span>
							</span>
							<form:password placeholder="Password" path="password"
								class="form-control" />
						</div>
						<form:errors path="password" cssClass="text-danger" />
				
						<br />
						<div class="text-left">
							<input class="loginBtn btn btn-primary" type="submit"
								value="LOGIN" />
						</div>
				
					</form:form>
				
					<br />
					<p>
						Not a PetTasker Staff? <a href="/sydney"><strong>Login
								here</strong></a>
					</p>
				
					<br />
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger">${errorMessage}</div>
					</c:if>
				</div>
			</div>
		</div>
</body>

</html>