<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Task</title>
	
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
			<form:form commandName="task"  method="GET">
				<div class="btn-float-right">
					<div class="float-left">SORT BY</div>
					<div class="dropdown-float-right">
						<form:select path="" class="form-control" id="filterInput">
							<form:option value="NONE" label="PLEASE SELECT ONE" class="form-control" />
						  	<form:options items="${tasksList}" cssClass="text-danger" />
						</form:select>
					</div>
				</div>
				
				<div class="page-heading">Tasks</div>
				<c:if test="${not empty noTasksInTheDatabase}">
					<div class="alert alert-danger">${noTasksInTheDatabase}</div>	
				</c:if>	
				
				<div class="row marketing" id="row">
				   	<c:forEach var="task" items="${tasks}">
				   		<!-- only display open status tasks -->
<%-- 				   		<c:if test="${task.getTaskStatus() == 'Open'}"> --%>
						    <div class="col-sm-6 col-md-4 ${task.getTaskStatus()}">
					            <div class="thumbnail eachTaskThumbnail">
									<img alt="image" src="/sydney/image/${task.getId()}.png" class="taskThumbnailPic">
					            		<div class="caption">
<%-- 						            		<div id="1"><strong>TaskId: </strong><span class="taskId">${task.getId()}</span></div> --%>
										<div class="thumbnail thumbnailTitle">
							           		<div id="1"><span class="taskName"><strong>${task.getTaskName()}</strong></span></div>
							           	</div>
							           	<div id="2"><strong>Price: </strong>$<span class="taskPrice">${task.getTaskPrice()}</span></div>
							           	<div id="3"><strong>Location: </strong><span class="taskLocation">${task.getTaskLocation()}</span></div>
							           	<span class="taskStartDate">${task.getStartDate()}</span>
						           		<input type="button" class="btn btn-primary btn-sm viewDetailBtn" value="View Details" onclick="window.location.href='tasks/${task.getId()}'" />
						           		<br/><br>
						           	</div>
								</div>
						    	</div>	
<%-- 						  </c:if>   --%>	
					</c:forEach>   
				</div>
			</form:form>
		</div>
		<%@ include file="footer.jsp" %> 
	</div>
</div>

<!-- load the javascript file -->
<script src="<c:url value="/resource/jss/filterTasks.js" />"></script>
<script src="<c:url value="/resource/jss/script.js" />"></script>

</body>

</html>