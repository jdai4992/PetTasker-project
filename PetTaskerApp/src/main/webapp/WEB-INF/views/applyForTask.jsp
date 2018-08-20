<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Apply for a task</title>
    
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
			<div class="page-heading">Task: ${task.getTaskName()}</div>

		    <div class="row marketing" id="row">
		
		        <!-- Left Pet Picture -->
		        <div class="col-sm-4 col-md-3">
		            <div class="panel panel-default">
		                <div class="panel-body">
							<img alt="image" src="/sydney/image/${task.getId()}.png" class="taskPic">
		           <%--          	<h4>Task Id: ${task.getId()}</h4> --%>
		                </div>
		            </div>
		            <div class="panel panel-default">
		            		<div class="panel-body">
		            		<div class="section-heading">Poster Details</div>
		                    <h4>Poster's Name:</h4>${adder.getFullName()}
		                    <h4>Email:</h4><a href="mailto:${adder.getEmail()}">${adder.getEmail()}</a>
		                    <h4>Contact Number:</h4>${adder.getPhoneNumber()}
		                </div>
					</div>
		        </div>
		        <!-- Right Pet Details -->
		        <div class="col-sm-8 col-md-9">
		            <div class="panel panel-default">
		                <div class="panel-body">
							<div class="btn-float-right">
								<a href="/sydney/tasks" class="btn btn-primary btn-sm">Back to Task List</a>
							</div>
							<div class="section-heading">Task Details</div>
		                    <table class="table table-hover">
		                    		<col width="30%">
		                    		<col width="70%">
								<tr>	
									<th>Task</th>
									<td>${task.getTaskName()}</td>
								</tr>
								<tr>
									<th>Location</th>
									<td>${task.getTaskLocation()}</td>
								</tr>
								<tr>	
									<th>Description</th>
									<td>${task.getTaskDescription()}</td>
								</tr>
								<tr>
									<th>Price</th>
									<td>$${task.getTaskPrice()}</td>
								</tr>
								<tr>
									<th>Task Posted Date</th>
									<td>${task.getStartDate()}</td>
								</tr>
							</table>
							<c:choose>
								<c:when test="${task.getAdderId() == user}">
									<div class="alert alert-warning">
										<strong>You are the poster of this task! So, you can't apply for your own task</strong>
									</div>
								</c:when>
								<c:when test="${task.getTaskStatus() == 'completedTask'}">
									<div class="alert alert-warning">
										<strong>This task has already been completed!</strong>
									</div>
								</c:when>
								<c:when test="${task.getTaskStatus() == 'assignedTask'}">
									<div class="alert alert-warning">
										<strong>This task is currently closed and assigned to someone!</strong>
									</div>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${not empty errorMessage}">
												<div class="alert alert-danger">${errorMessage}</div>	
										</c:when>
										<c:otherwise>
											<div class="section-heading">Reason for Apply</div>
											<form:form commandName="taskAppliers">
												<div class="inputFields">Reason for Apply</div>
												<div class="input-group">
													<span class="input-group-addon">
														<span class="glyphicon glyphicon-align-justify"></span>
													</span>
													<form:textarea placeholder="Write the reason here" path="reasonForApply" class="form-control"/>
												</div>
												<form:errors path="reasonForApply" cssClass="text-danger" />
												
												<br/>
												<br/>
												<div class="text-left">
													<input class="btn btn-primary" type="submit" value="APPLY" />
												</div>
											</form:form>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>									
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp" %> 
	</div>
</div>

<!-- load the javascript file -->
<%-- <script src="<c:url value="/resource/jss/filterTasks.js" />"></script> --%>
<script src="<c:url value="/resource/jss/script.js" />"></script>

</body>

</html>
