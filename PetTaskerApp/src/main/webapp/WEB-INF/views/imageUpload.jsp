<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page pageEncoding="utf-8"%>  
<!DOCTYPE html>  
<html>  
<head>  
	<meta charset="utf-8">  
	<title>Upload image</title>  
	
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
			<div class="page-heading">Upload a image</div>
				<form action="/sydney/newTask/${task.getId()}/imageUpload" method="post" enctype="multipart/form-data">
				
					<div class="inputFields">Choose your image file</div>
					<div class="input-group">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-picture"></span>
						</span>
						<input type="file" name="file" class="form-control"/>
					</div>
					
					<br/>
					
					<div class="text-left">
						<input class="btn btn-primary" type="submit" value="Upload" />
					</div>
				</form>
		</div>
		<%@ include file="footer.jsp" %> 
	</div>
</div>

</body>  

</html>  