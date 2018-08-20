<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Review</title>
	
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
			<div class="page-heading">Make a review</div>				
			<form:form method = "POST" commandName="reviews">
				<div class="inputFields">Ratings for Attitude</div>
				<div class="input-group">
					<form:radiobutton path="attitude" value="5" class="inputRadio"/>Very Good
					<form:radiobutton path="attitude" value="4" class="inputRadioBtn"/>Good
					<form:radiobutton path="attitude" value="3" class="inputRadioBtn"/>Acceptable
					<form:radiobutton path="attitude" value="2" class="inputRadioBtn"/>Bad
					<form:radiobutton path="attitude" value="1" class="inputRadioBtn"/>Very Bad
				</div>
				<form:errors path="attitude" cssClass="text-danger" />
				<br/>
				<br/>
				
				<div class="inputFields">Ratings for Honesty</div>
				<div class="input-group">
					<form:radiobutton path="honesty" value="5" class="inputRadio"/>Very Good
					<form:radiobutton path="honesty" value="4" class="inputRadioBtn"/>Good
					<form:radiobutton path="honesty" value="3" class="inputRadioBtn"/>Acceptable
					<form:radiobutton path="honesty" value="2" class="inputRadioBtn"/>Bad
					<form:radiobutton path="honesty" value="1" class="inputRadioBtn"/>Very Bad
				</div>
				<form:errors path="honesty" cssClass="text-danger" />
				<br/>
				<br/>

				<div class="inputFields">Ratings for Efficiency</div>
				<div class="input-group">
					<form:radiobutton path="efficiency" value="5" class="inputRadio"/>Very Good
					<form:radiobutton path="efficiency" value="4" class="inputRadioBtn"/>Good
					<form:radiobutton path="efficiency" value="3" class="inputRadioBtn"/>Acceptable
					<form:radiobutton path="efficiency" value="2" class="inputRadioBtn"/>Bad
					<form:radiobutton path="efficiency" value="1" class="inputRadioBtn"/>Very Bad
				</div>
				<form:errors path="efficiency" cssClass="text-danger" />
				<br/>
				<br/>

				<div class="inputFields">Ratings for Overall</div>
				<div class="input-group">
					<form:radiobutton path="overall" value="5" class="inputRadio"/>Very Good
					<form:radiobutton path="overall" value="4" class="inputRadioBtn"/>Good
					<form:radiobutton path="overall" value="3" class="inputRadioBtn"/>Acceptable
					<form:radiobutton path="overall" value="2" class="inputRadioBtn"/>Bad
					<form:radiobutton path="overall" value="1" class="inputRadioBtn"/>Very Bad
				</div>
				<form:errors path="overall" cssClass="text-danger" />
				<br/>
				<br/>
				
				<div>
					<div><strong>Review Details</strong></div>
					<form:textarea placeholder="Write the review reason here" path="reviewsDetails" class="form-control"/>
					<form:errors path="reviewsDetails" cssClass="text-danger" />
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
							<input class="btn btn-primary" type="submit" value="SUBMIT REVIEW" />
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