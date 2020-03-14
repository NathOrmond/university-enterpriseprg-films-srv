<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Films</title>

<!-- JQUERY -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- CSS Styling -->
<link rel="stylesheet" href="./css/menubar.css" type="text/css" />
<link rel="stylesheet" href="./css/theme.css" type="text/css" />
<link rel="stylesheet" href="./css/searchbar.css" type="text/css" />
<!-- JS -->
<script src="./js/ajax-utils.js" type="text/javascript"></script>
</head>


<body>

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
			<li class="active"><a href="<c:url value="/" />">Home</a></li>
			<li><a href="films">Films</a></li>
			<li><a href="data">Data</a>
				<ul>
					<li><a href="data?format=xml">XML</a></li>
					<li><a href="data?format=json">JSON</a></li>
					<li><a href="data?format=csv">CSV</a></li>
				</ul></li>
			<li><a href="insert">Add New</a>
		</ul>
	</div>

	<br>

	<!-- SEARCH BAR WITH AJAX -->

	<div>
		<h1>Look for Films</h1>
		<p/>
		<form action="films" method="GET">
			<div class="autocomplete" style="width:300px;">
				<input id="main_searchbar" type="text" name="query" class="searchbar" placeholder="query ...">
			</div>
			<p/>
			<input type="submit" value="Search" />
		</form>
	</div>


	
	<c:set var="autocomplete_key_words" value="${autocomplete_key_words}"></c:set>

	<script>
		var keyword_arr = [
			<c:forEach var="autocomplete_keyword" items="${autocomplete_key_words}">
				"${autocomplete_keyword}",
			</c:forEach>
				"Easter Egg"
		];
		autocomplete(document.getElementById("main_searchbar"), keyword_arr);
	</script></html>