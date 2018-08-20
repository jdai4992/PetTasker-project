<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Choose a worker</title>
	
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
			<div class="page-heading">Choose a worker</div>				
			<form:form method="POST" action="/sydney/tasks/${task.getId()}/choose" commandName="task">
				<c:forEach var="worker" items="${taskAppliers}">
			
				<form:radiobutton path="workerId" value="${worker.getId().getUser().getId()}" />
				${worker.getId().getUser().getFullName()} <br>
				${worker.getReasonForApply()} <br><br>
			
			
				</c:forEach>
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
							<input class="btn btn-primary" type="submit" value="Choose" />
						</div>
					</div>
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