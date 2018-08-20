<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Register</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link href="<c:url value="/resource/css/style.css" />" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="navBar.jsp" %> 

<div id="backgroundImage">
    <div class="container">
		<div class="jumbotron">
			<div class="form">		  
				<div class="loginRegisterTitle">Edit Profile</div>
				<hr>
				
				<form:form method="POST" commandName="user" >
				
					<div class="row">
						<br/>
						<div class="col-sm-6">
							<div class="inputFields">First Name</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</span>
								<form:input placeholder="First Name" path="firstName" class="form-control"/>
							</div>
							<form:errors path="firstName" cssClass="text-danger" />
						</div>
						<div class="col-sm-6">
							<div class="inputFields">Last Name</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</span>
								<form:input placeholder="Last Name" path="lastName" class="form-control"/>
							</div> 
						<form:errors path="lastName" cssClass="text-danger" />
						</div>
					</div>
					
					<div class="row">
						<br/>
						<div class="col-sm-6">
							<div class="inputFields">Email</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-envelope"></span>
								</span>
								<form:input path="email" class="form-control disable" readonly="true"/>
							</div>
							<form:errors path="email" cssClass="text-danger"/>
						</div>
					</div>
					
					<div class="row">
						<br/>
						<div class="col-sm-6">
							<div class="inputFields">Password</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</span>
								<form:password placeholder="Password" path="password" class="form-control" />
							</div>
							<form:errors path="password" cssClass="text-danger"/>
						</div>
						<div class="col-sm-6">
							<div class="inputFields">Confirm Password</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</span>
								<form:password placeholder="Confirm Password" path="confirmPassword" class="form-control" />
							</div>
							<form:errors path="confirmPassword" cssClass="text-danger" />
						</div>
					</div>
					
					<div class="row">
						<br/>
						<div class="col-sm-6">
							<div class="inputFields">Address</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-globe"></span>
								</span>
								<form:input placeholder="Address" path="address" class="form-control" />
							</div>
							<form:errors path="address" cssClass="text-danger" />
						</div>
						<div class="col-sm-6">
							<div class="inputFields">City</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-globe"></span>
								</span>
								<form:input placeholder="City" path="city" class="form-control" />
							</div>
							<form:errors path="city" cssClass="text-danger" />
						</div>
					</div>
					
					<div class="row">
						<br/>
						<div class="col-sm-6">
							<div class="inputFields">State</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-globe"></span>
								</span>
								<form:input placeholder="State" path="state" class="form-control" />
							</div>
							<form:errors path="state" cssClass="text-danger" />
						</div>
						<div class="col-sm-6">
							<div class="inputFields">Postcode</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-globe"></span>
								</span>
								<form:input placeholder="Postcode" path="postcode" type="number" class="form-control" />
							</div> 
							<form:errors path="postcode" cssClass="text-danger" />
						</div>
					</div>
					
					<div class="row">
						<br/>
						<div class="col-sm-6">
							<div class="inputFields">Country</div>
							<div>
								<form:select path="country" class="form-control">
									<form:option value="NONE" label="Select Country" class="form-control" />
								  	<form:options items="${countryList}" cssClass="text-danger" />
								</form:select>   
							</div>
						</div>
						<div class="col-sm-6">
							<div class="inputFields">Phone Number</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-phone-alt"></span>
								</span>
								<form:input placeholder="Phone Number" path="phoneNumber" type="number" class="form-control" />
							</div>
							<form:errors path="phoneNumber" cssClass="text-danger "/>
						</div>
					</div>
					
					<div class="row">
						<br/>
						<div class="col-sm-6">
							<div class="text-left">
								<input class="btn btn-warning" type="reset" value="RESET" />
								or <a href="/sydney/userHomePage">Cancel</a>	
							</div>
						</div>
						<div class="col-sm-6">
							<div class="text-right">
								<input class="btn btn-primary" type="submit" value="UPDATE" />
							</div>
						</div>
					</div>
			
				</form:form>
				
				<br/>
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>	
				</c:if>		
			</div>
			<%@ include file="footer.jsp" %> 	    
		</div>
	</div>
</div>	

<!-- load the javascript file -->
<script src="<c:url value="/resource/jss/script.js" />"></script>

</body>

</html>