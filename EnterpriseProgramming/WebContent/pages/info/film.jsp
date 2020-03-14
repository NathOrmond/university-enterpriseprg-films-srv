<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Film</title>
<!-- JQUERY -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- CSS Styling -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet" href="./css/menubar.css" type="text/css" />
<link rel="stylesheet" href="./css/table.css" type="text/css" />
<link rel="stylesheet" href="./css/theme.css" type="text/css" />
<!-- JS -->
<script src="./js/ajax-utils.js" type="text/javascript"></script>
<script src="./js/google-search-api-utils.js" type="text/javascript"></script>





</head>
<body>

<c:set var="film" value="${film}"></c:set>
<c:set var="insert_outcome" value="${insert_outcome}"></c:set>

<!-- TITLE-->
	<div>
		<h1>Enterprise Programming</h1>
		<p>Nathan Ormond Enterprise Programming Project. Search for a film below or choose an option from the menu.</p>
	</div>
		
	<br>

	<br>

	<!-- Menu-->
	<div>
		<ul id="menu-bar">
			<li><a href="<c:url value="/" />">Home</a></li>
			<li class="active"><a href="films">Films</a></li>
			<li><a href="data">Raw Data</a>
				<ul>
					<li><a href="data?format=xml">XML</a></li>
					<li><a href="data?format=json">JSON</a></li>
					<li><a href="data?format=csv">CSV</a></li>
				</ul></li>
			<li><a href="insert">Add New</a>
		</ul>
	</div>

	<br>

	<!-- Film Result -->


	<div style="width: 80%; margin: 0px auto;">
		<table id="films_table" class="display">
			<thead>
				<tr>
					<th>Title</th>
					<th>Year</th>
					<th>Cover</th>
					<th>Director</th>
					<th>Stars</th>
					<th>Review</th>
					<th>Remove</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td id="film_title" style="width: 10%; height: 400px;"><c:out value="${film.title}"></c:out></td>
					<td style="width: 5%; height: 400px;"><c:out value="${film.year}"></c:out></td>
					<td style="width: 25%; height: 400px;"><img id="film-image" SRC="" alt="IMAGE NOT FOUND" border=3 height=100%></img></td>
					<td style="width: 10%; height: 400px;"><c:out value="${film.director}"></c:out></td>
					<td style="width: 10%; height: 400px;"><c:out value="${film.stars}"></c:out></td>
					<td style="width: 35%; height: 400px;"><c:out value="${film.review}"></c:out></td>
					<td style="width: 5%; height: 400px;">
						<form action="film" method="POST">
						 	<input type="hidden" name="film_title" value="${film.title}" />
							<input class="deleteButton" type="submit" value="Delete"/>
						</form>
						
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	
		<!-- SCRIPT FOR DELETE OUTCOME NOTIFICATION -->
	<script>
		$(document).ready(function() {
			var outcome = '${insert_outcome}';
			if ((outcome == null) || outcome == "") {
				console.log("no outcome")
			} else { 
				alert(outcome);
			}
		});
	</script>


</body>
</html>