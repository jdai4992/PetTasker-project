<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Report Detail</title>

<!-- Shows the details of the specific Report staff clicked in the StaffReport page-->

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

				<div class="text-right">
					<a href="/sydney/staff/staffReport">Back to list of Reports</a>
				</div>

				<h2>Details for Report: ${report.getTitle()}</h2>
				<br/>

				<div class="container-fluid">
					<div class="row">
						<div style="vertical-align: middle;">
							<div>
								<table class="table">
									<col width="30%">
									<col width="40%">
									<col width="30%">
									<tbody>
										<tr>
											<th>Report Id</th>
											<td>${report.getReportId()}</td>
											<td></td>
										</tr>
										<tr>
											<th>Reporter</th>
											<td>${report.getReporterId()}</td>
											<td class="text-right">
												<a href="/sydney/staff/manageUser/${report.getReporterId()}"
													class="btn btn-primary btn-sm">View Reporter User's Details</a>
											</td>
										</tr>
										<tr>
											<th>Reported User</th>
											<td>${report.getReportedPersonId()}</td>
											<td class="text-right">
												<a href="/sydney/staff/manageUser/${report.getReportedPersonId()}"
													class="btn btn-primary btn-sm">View Reported User's Details</a>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

						<br /> <br />
						<hr class="line">

						<table class="table">
							<col width="30%">
							<col width="40%">
							<col width="30%">
							<tbody>
								<tr>
									<th>Title</th>
									<td>${report.getTitle()}</td>
<!-- 									<td></td> -->
									<td class="text-right"><strong>Report Date</strong> ${report.getStartDate()}</td>
								</tr>
								<tr>
									<th>Report Status</th>
									<c:choose>
										<c:when
											test="${report.getReportStatus() != null}">
											<td>${report.getReportStatus()}</td>
										</c:when>
										<c:otherwise>
											<td>INCOMPLETE</td>
										</c:otherwise>
									</c:choose>
									<td></td>
								</tr>
								<tr>
									<th>Category of Report</th>
									<td>${report.getReason()}</td>
									<td></td>
								</tr>
								<tr>
									<th>Report details</th>
									<td>${report.getReportDetails()}</td>
									<td></td>
								</tr>
							</tbody>
						</table>

						<div class="text-right">
							<!-- <button type="button" class="btn btn-primary btn-lg">Resolved</button> -->
							<a class="btn btn-primary btn-sm"
								href="/sydney/staff/staffReport/${report.getReportId()}/resolved"
								role="button">Resolved</a> <a class="btn btn-secondary btn-sm"
								href="/sydney/staff/staffReport/${report.getReportId()}/unresolved"
								role="button">Unresolved</a>
						</div>


					</div>
				</div>
				<%@ include file="footer.jsp"%>
			</div>

		</div>
	</div>

	<!--
	<script>
								var url = '/sydney/staff/manageUser/{report.getReportedPersonId()}'
								$('#buttonUsr').click(function(${report.getReportedPersonId()}){
								    
								    $('#test').load('/manageUser #iframe_a');
								});
					
								function loadIframe(iframeName, url) {
								    var $iframe = $('#' + iframeName);
								    if ( $iframe.length ) {
								        $iframe.attr('src',url);   
								        return false;
								    }
								    return true;
								}
  </script>
	<script type="text/javascript">
function Redirect() {
	
	location.href='/sydney/staff/manageUser';
	
	
}
function ChangeIframe(id) {
	document.getElementById('iframe_a').src = id;
	
	console.log('this is id in js ' + id);
	
}
</script>
	<script src="<c:url value="/resource/jss/staffScript.js" />"></script>
-->


</body>
</html>
