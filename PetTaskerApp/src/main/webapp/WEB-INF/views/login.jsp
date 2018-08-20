<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Login</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link href="<c:url value="/resource/css/style.css" />" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
    <div id="logo" class="">Welcome to PetTasker</div>
    		<div class="row marketing" id="row">		
		      <!-- Left Pet Picture -->
		        <div class="col-sm-8 col-md-8 locateAboutUs">
					<div class="panel panel-default ">
		            		<div class="panel-body panel-body-login">
		            		<div class="page-heading">About Us!</div>
		            			The primary aim of the PetTasker application is to <strong>help users outsource pet-related tasks
							and hire other users to complete those tasks</strong> e.g. users can find and hire an experienced
							dog sitter or walker.
							<br>This application will help pet owners solve issues such as:
							<ol>
							  	<li> If you are the owner of a pet Dog and you decided to go on a holiday, what do you do
									with your pet? Where do you keep them?</li>
								<li> What if you need to buy pet food and you're at work?</li>
								<li> What if you need to give your pet some food while you're away?</li>
							</ol>
							<strong>PetTasker saves the day!</strong>
							<br><br>
							
							<div class="page-heading">How does the application Work?</div>
							<ol>
							  	<li>A user posts (known as ‘Task Adder’) a pet-related task that they want to get done. e.g.
									‘Look after my dog while I’m on a holiday for 2 weeks’</li>
							 	 <li>Other users (known as ‘Task Workers’) who are interested in the task can apply for the
									task by sending the ‘Task Adder’ an expression of interest</li>
							  	<li>The ‘Task Adder’ can receive multiple applications from ‘Task Workers’ within a set
									time period.</li>
							  	<li>The ‘Task Adder’ can choose which ‘Task Worker’ he/she wants and then the ‘Task
									Worker’ will be notified to complete the task.</li>		
							   	<li>Once the decision is made, the contact details are exchanged to allow the ‘Task Adder’
									to privately communicate with the ‘Task Worker’, and vice versa.</li>		
								<li>Upon a successful job completion, ‘Task Adder’ can make a payment in cash to ‘Task
									Worker’.</li>	
								<li>Both the ‘Task Adder’ and ‘Task Worker’ can leave a review for each other.</li>
							</ol>
		            		</div>
		            	</div>
				</div>
			   <!-- Right Pet Details -->
		        <div class="col-sm-4 col-md-4">
                		<div class="loginContainer card card-1 pullUp" id="centeredDiv">
		    			
						<h2 class="form-signin-heading loginRegisterTitle login-title"><b>Please Sign-in</b></h2>
						<hr>
								
						<form:form commandName="user">
							
							<div class="inputFields">Email</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-envelope"></span>
								</span>
								<form:input placeholder="Email" path="email" class="form-control" autofocus="true"/>
							</div>
							<form:errors path="email" cssClass="text-danger"/>
							
							<br/>
							<div class="inputFields">Password</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</span>
								<form:password placeholder="Password" path="password" class="form-control"/>
							</div>
							<form:errors path="password" cssClass="text-danger"/> 
							
							<br/>
							<div class="text-left">
								<input class="loginBtn btn btn-primary" type="submit" value="LOGIN" />
							</div>
							
						</form:form>
						
						<br/>
						<p>Not registered? <a href="/sydney/register"><strong>Create an account</strong></a></p>
						<p>Staff of PetTasker? <a href="/sydney/staff"><strong>Login here</strong></a></p>
						
						<br/>
						<c:if test="${not empty errorMessage}">
							<div class="alert alert-danger login-alert-danger">${errorMessage}</div>	
						</c:if>
						<c:if test="${not empty submittedMessage}">
						<div class="alert alert-success" id="success-alert">
						    <button type="button" class="close" data-dismiss="alert">x</button>
						    <strong>Success! </strong>${submittedMessage}
						</div>
						</c:if>
        				</div>
	        		</div>
			</div>        
<script>
$("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
})
</script>
</body>

</html>
