<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Reported Task</title>

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
				<div class="page-heading">Reported Task: ${task.getId()}</div>

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
						<th>Price</th>
						<td>$${task.getTaskPrice()}</td>
					</tr>
					<tr>
						<th>Task Posted Date</th>
						<td>${task.getStartDate()}</td>
					</tr>
					<tr>
						<th>Task Status</th>
						<td>${task.getTaskStatus()}</td>
					</tr>
				</table>

				<br /> <br /> <br /> <br />
				<div class="page-heading">Task Adder and Task Worker's Details</div><br/>
				<div class="container-fluid">
					<div class="row">
						<div style="width: 100%; float: right">
							<table class="table table-hover">
								<col width="40%">
								<col width="20%">
								<col width="40%">
								<tr>
									<th>Task Poster Id</th>
									<td>${task.getAdderId()}</td>
									<td>	<a href="/sydney/staff/manageUser/${task.getAdderId()}" 
											class="btn btn-primary btn-sm btn-float-right">View Poster</a> 
									</td>		
								</tr>
								<!-- 
								<tr>
									<th>Task Poster</th>
									<td>${task.getTaskName()}</td>
								</tr>
								 -->
								<tr>
									<th>Task Poster's Completion Status</th>
									<td>${task.getAdderCompletedStatus()}</td>
									<td></td>
								</tr>
								<tr>
									<th>Task Worker Id</th>
									<td>${task.getWorkerId()}</td>
									<td><a href="/sydney/staff/manageUser/${task.getWorkerId()}" 
											class="btn btn-primary btn-sm btn-float-right">View Worker</a></td>
								</tr>
								<!-- 
								<tr>
									<th>Task Worker</th>
									<td>$${task.getTaskPrice()}</td>
								</tr>
								 -->
								<tr>
									<th>Task Worker's Completion Status</th>
									<td>${task.getWorkerCompletedStatus()}</td>
									<td></td>
								</tr>

<%-- 								<div style="position: relative">
									<div style="position: absolute; right: 20%">
										<a
											href="/sydney/staff/manageUser/${task.getAdderId()}"
											class="btn btn-primary btn-sm">View Poster</a> 
										<br /> <br />
										<div>
											<a
												href="/sydney/staff/manageUser/${task.getWorkerId()}"
												class="btn btn-primary btn-sm">View Worker</a>
										</div>
									</div>
								</div> --%>
							</table>
								<a class="btn btn-default btn-float-right" href="/sydney/staff/manageTask/${task.getId()}/delete" role="button">
								<span class="glyphicon glyphicon-trash"></span></a>
						</div>
					</div>
				</div>
				<c:choose>
					<c:when
						test="${task.getTaskStatus() == 'openTask' && task.getAdderId() != user && empty errorMessage}">
						<a href="/sydney/tasks/${task.getId()}/apply"
							class="btn btn-primary btn-sm">Apply For this Task</a>
					</c:when>
					<c:when test="${not empty errorMessage}">
						<div class="alert alert-danger">${errorMessage}</div>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${task.getAdderId() == user}">
								<div class="alert alert-warning">
									<strong>You are the poster of this task! So, you can't
										apply for your own task</strong>
								</div>
							</c:when>
							<c:when test="${task.getTaskStatus() == 'completedTask'}">
								<div class="alert alert-warning">
									<strong>This task has already been completed!</strong>
								</div>
							</c:when>
							<c:when test="${task.getTaskStatus() == 'assignedTask'}">
								<div class="alert alert-warning">
									<strong>This task is currently closed and assigned to
										someone!</strong>
								</div>
							</c:when>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>