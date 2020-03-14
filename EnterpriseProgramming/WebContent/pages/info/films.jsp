<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Films</title>


<!-- JQUERY -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- DATA TABLES -->
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<!-- CSS Styling -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet" href="./css/menubar.css" type="text/css" />
<link rel="stylesheet" href="./css/table.css" type="text/css" />
<link rel="stylesheet" href="./css/theme.css" type="text/css" />
<!-- JS -->
<script src="./js/ajax-utils.js" type="text/javascript"></script>
<script src="./js/datatables.js" type="text/javascript"></script>
<script src="./js/table-onclick.js" type="text/javascript"></script>
</head>


<body>

	<c:set var="query" value="${query}"></c:set>
	

	<!-- TITLE-->
	<div>
		<h1>Enterprise Programming</h1>
		<p>Nathan Ormond Enterprise Programming Project. Search for a film
			below or choose an option from the menu.</p>
	</div>

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

	<!-- TABLE DISPLAYING FILMS FROM MODEL -->

	<div style="width: 80%; margin: 0px auto;">
		<table id="films_table" class="display">
			<thead>
				<tr>
					<th>Title</th>
					<th>Year</th>
					<th>Director</th>
					<th>Stars</th>
					<th>More</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="film" items="${allFilms}">
					<tr>
						<td>${film.title}</td>
						<td>${film.year}</td>
						<td>${film.director}</td>
						<td>${film.stars}</td>
						<td><button class="showMoreButton" onClick="showMore('${film.title}')">show more</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
	var table = $('#films_table').DataTable();
	var query ='${query}';
	
	

	$(document).ready(function() {
		if ((query == null) || (query == "")) {
			console.log("no outcome")
		} else { 
			table.search(query).draw();
		}
	});
	</script>


</body>

</html>