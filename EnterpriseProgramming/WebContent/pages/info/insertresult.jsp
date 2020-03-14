<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Film</title>
<!-- JQUERY -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- CSS Styling -->
<link rel="stylesheet" href="./css/menubar.css" type="text/css" />
<link rel="stylesheet" href="./css/theme.css" type="text/css" />
<!-- JS -->
<script src="./js/ajax-utils.js" type="text/javascript"></script>
</head>
<body>

	<c:set var="insert_outcome" value="${insert_outcome}"></c:set>

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
			<li><a href="films">Films</a></li>
			<li><a href="data">Data</a>
				<ul>
					<li><a href="data?format=xml">XML</a></li>
					<li><a href="data?format=json">JSON</a></li>
					<li><a href="data?format=csv">CSV</a></li>
				</ul></li>
			<li class="active"><a href="insert">Add New</a></li>
		</ul>
	</div>

	<br>

	<!-- SUBMISSION FORM FOR NEW FILMS -->
	<div>
		<h1>Submit A New Film</h1>
		<form action="insert" method="POST">
			<p />
			<input type="text" name="film_title" class="searchbar"
				placeholder="Title" />
			<p />
			<input type="text" name="film_year" class="searchbar"
				placeholder="Year" />
			<p />
			<input type="text" name="film_director" class="searchbar"
				placeholder="Director" />
			<p />
			<input type="text" name="film_stars" class="searchbar"
				placeholder="Stars" />
			<p />
			<textarea class="text_area" name="film_review" 
				placeholder="write a synopsis ..."  /></textarea>
			<p />
			<input type="submit" value="Submit" />
		</form>
	</div>

	<!-- SCRIPT FOR INSERT -->
	<script>
		$(document).ready(function() {
			var outcome = '${insert_outcome}';
			if ((outcome == null) || (outcome == "")) {
				console.log("no outcome")
			} else { 
				alert(outcome);
			}
		});
	</script>
</body>
</html>