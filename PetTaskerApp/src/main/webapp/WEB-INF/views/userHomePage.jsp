<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>	
    <title>User Home Page</title>
    
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link href="<c:url value="/resource/css/style.css" />" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<style>
		table {
   			font-family: arial, sans-serif;
    			border-collapse: collapse;
    			width: 100%;
		}

		td, th {
    			border: 1px solid #dddddd;
    			text-align: left;
    			padding: 8px;
		}

		tr:nth-child(even) {
    			background-color: #dddddd;
		}
	</style>
</head>

<body>
<%@ include file="navBar.jsp" %> 

<div id="backgroundImage">
    <div class="container">
		<div class="jumbotron">
			<div class="page-heading">Task History and Status</div>
			<div class="btn-float-right">
				<a href="/sydney/newTask" class="btn btn-primary btn-sm">Create a new task</a>
			</div>
			<!-- <div class="section-heading">Task Details</div> -->
	
			Welcome to back to your homepage <strong>${currentUser.getFullName()}</strong>
			<br/><br/>
			
			<div class="panel panel-default">
			    <div class="panel-body">
					<div class="section-heading">Tasks Added:</div>
					<table class="table table-hover">
						<col width="15%">
						<col width="50%">
						<col width="10%">
						<col width="25%">
						<tr>
							<th>Task Status</th>
							<th>Task Title</th>
							<th>Report?</th>
							<th>Operations</th>
						</tr>
						<c:forEach var="task" items="${addedTasks}">
						<tr>
							<td>${task.getTaskStatus()}</td>
							<td><a href="/sydney/tasks/${task.getId()}">${task.getTaskName()}</a></td>
							<td>
								<c:choose>
									<c:when test="${task.getWorkerId() != '0'}">
										<a class="btn btn-default btn-sm" href="/sydney/tasks/${task.getId()}/report" role="button">Report</a>
									</c:when>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${task.getTaskStatus() == 'openTask'}">
									<a class="btn btn-default btn-sm" href="/sydney/tasks/${task.getId()}/choose" role="button">Choose Candidate</a>
									<a class="btn btn-default" href="/sydney/tasks/${task.getId()}/edit" role="button"><span class="glyphicon glyphicon-pencil"></span></a>
									<a class="btn btn-default" href="/sydney/tasks/${task.getId()}/delete" role="button"><span class="glyphicon glyphicon-trash"></span></a>
									</c:when>
									<c:when test="${task.getTaskStatus() == 'assignedTask'}">
									<c:choose>
										<c:when test="${task.getAdderCompletedStatus() != null}">
										<a class="btn btn-default disabled btn-sm" href="/sydney/tasks/${task.getId()}/adderCompleted" role="button">Mark as Done!</a>
										</c:when>
										<c:otherwise>
										<a class="btn btn-default btn-sm" href="/sydney/tasks/${task.getId()}/adderCompleted" role="button">Mark as Done!</a>
										</c:otherwise>
									</c:choose>
									</c:when>
									<c:when test="${task.getTaskStatus() == 'completedTask' &&  not task.getTaskAdderReview() && task.getAdderId() == currentUser.getId()}">
										<a class="btn btn-default btn-sm" href="/sydney/tasks/${task.getId()}/reviews" role="button">Write a Review</a>
									</c:when>
									<c:when test="${task.getTaskAdderReview()}">
										Task Already Reviewed
									</c:when>
								</c:choose>
							</td>
							
		<%-- 					<td>
								<c:choose>
									<c:when test="${task.getTaskStatus() == 'completedTask'}">
									</c:when>
								</c:choose>
							</td> --%>
						</tr>
						</c:forEach>
					</table>
					<br/><br/>
				</div>
			</div>
				
			
			<div class="panel panel-default">
			    <div class="panel-body">
					<div class="section-heading">Tasks Applied:</div>
					<table class="table table-hover">
						<col width="15%">
						<col width="50%">
						<col width="10%">
						<col width="25%">
						<tr>
							<th>Task Status</th>
							<th>Task Title</th>
							<th>Report?</th>
							<th>Operations</th>
						</tr>
						<c:forEach var="task" items="${appliedTasks}">
						<tr>
							<td>${task.getTaskStatus()}</td>
							<td><a href="/sydney/tasks/${task.getId()}">${task.getTaskName()}</a></td>
							<td><a class="btn btn-default btn-sm" href="/sydney/tasks/${task.getId()}/report" role="button">Report</a></td>
							<td>
								<c:choose>
									<c:when test="${task.getTaskStatus() == 'openTask'}">
										You Applied for this task
									</c:when>
									<c:when test="${task.getTaskStatus() == 'assignedTask'}">
										<c:choose>
											<c:when test="${task.getWorkerId() == currentUser.getId()}">
												<c:choose>
													<c:when test="${task.getWorkerCompletedStatus() != null}">
														<a class="btn btn-default disabled btn-sm" href="/sydney/tasks/${task.getId()}/workerCompleted" role="button">Mark as Done!</a>
													</c:when>
													<c:otherwise>
														<a class="btn btn-default btn-sm" href="/sydney/tasks/${task.getId()}/workerCompleted" role="button">Mark as Done!</a>
													</c:otherwise>
												</c:choose>
											</c:when>
										</c:choose>
									</c:when>
									<c:when test="${task.getTaskStatus() == 'completedTask' &&  not task.getTaskWorkerReview() && task.getWorkerId() == currentUser.getId()}">
										<a class="btn btn-default btn-sm" href="/sydney/tasks/${task.getId()}/reviews" role="button">Write a Review</a>
									</c:when>
									<c:when test="${task.getTaskWorkerReview() && task.getWorkerId() == currentUser.getId()}">
										Task Already Reviewed
									</c:when>
								</c:choose>
							</td>
						</tr>
						</c:forEach>
						
					</table>
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