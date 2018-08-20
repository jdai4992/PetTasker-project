<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Report</title>
	
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
			<div class="page-heading">Report</div>
			
			<form:form method="POST" modelAttribute="report" >
			
				<div class="inputFields">Title</div>
				<form:input placeholder="Write title for the report" path="title" class="form-control"/>
				<form:errors path="title" cssClass="text-danger" />
				<br/>
				
				<div class="inputFields">Reason</div>
				<div class="input-group" align="left">
					<form:radiobutton path="reason" value="Scammer" class="inputRadio"/>This person is a scammer
					<form:radiobutton path="reason" value="Illegal Behaviour or information" class="inputRadioBtn"/>Illegal behaviour or information
					<form:radiobutton path="reason" value="Other" class="inputRadioBtn"/>Other reason			
				</div>
				<form:errors path="reason" cssClass="text-danger" />
				<br/>
				
				<div class="inputFields">Details</div>
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-align-justify"></span>
					</span>
					<form:textarea placeholder="Write the report details here" path="reportDetails" class="form-control"/>
				</div>
				<form:errors path="reportDetails" cssClass="text-danger" />
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