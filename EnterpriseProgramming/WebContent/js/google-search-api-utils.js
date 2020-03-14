/**
 * 
 */
// <100 searches per day for free with Google API (pay up for more)
var googleAPIKey = "AIzaSyAwglViEjWOJi1HK6VcKop3YeJTdVPCVEc";

var googleCSEID = "016503351483343985500:cxa8kkhgwoi";


function getImageURLForKeyWord(keyWord){ 
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", getSearchURL(keyWord) , false ); // false for synchronous request
    xmlHttp.send( null );
    var response = xmlHttp.responseText;
    jsonObj = JSON && JSON.parse(response) || $.parseJSON(response);
    return jsonObj.items[1].link;
}

function  getSearchURL(keyWord){ 
	return "https://www.googleapis.com/customsearch/v1?key="+ googleAPIKey + "&cx=" + googleCSEID+"&q=" + keyWord + "%20film%20cover&searchType=image&fileType=jpg&imgSize=medium&alt=json";
}


$(document).ready(function() {
	var keyWord = document.getElementById("film_title").textContent;
	console.log(keyWord);
	document.getElementById('film-image').setAttribute('src', getImageURLForKeyWord(keyWord));
});