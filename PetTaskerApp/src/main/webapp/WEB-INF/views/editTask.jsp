<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Edit Task</title>
	
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
			<div class="page-heading">Edit task</div>
				<form:form method="POST" action="/sydney/tasks/${task.getId()}/edit" modelAttribute="task">
				
					<div class="inputFields">Title</div>
					<form:errors path="taskName" cssClass="text-danger" />
					<div class="input-group">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-envelope"></span>
						</span>
						<form:input placeholder="${task.getTaskName()}" path="taskName" class="form-control"/>
					</div>
					
					<div class="inputFields">Price</div>
					<form:errors path="taskPrice" cssClass="text-danger" />
					<div class="input-group">
						<span class="input-group-addon">$</span>
						<form:input placeholder="${task.getTaskPrice()}" path="taskPrice" type="number" class="form-control"/>
					</div>
					
					<div class="inputFields">Location</div>
					<form:errors path="taskLocation" cssClass="text-danger" />
					<div class="input-group">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-map-marker"></span>
						</span>
						<form:input placeholder="${task.getTaskLocation()}" path="taskLocation" class="form-control"/>
					</div>
					
					<div class="inputFields">Description</div>
					<form:errors path="taskDescription" cssClass="text-danger" />
					<div class="input-group">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-align-justify"></span>
						</span>
						<form:textarea placeholder="${task.getTaskDescription()}" path="taskDescription" class="form-control"/>
					</div>

					<br/>
					
					<div class="text-left">
						<input class="btn btn-primary" type="submit" value="Submit" />
					</div>
				</form:form>
		</div>
		<%@ include file="footer.jsp" %> 
	</div>
</div>

<!-- load the javascript file -->
<%-- <script src="<c:url value="/resource/jss/filterTasks.js" />"></script> --%>
<script src="<c:url value="/resource/jss/script.js" />"></script>

</body>

</html>