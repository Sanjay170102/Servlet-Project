<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
<%@ page import = "com.genericSort.Model.dbOperations"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href="tableSorterStyle.css" rel="stylesheet">
		<title>UnSorted</title>
	</head>
	<body>
		<header class = "navBar-container">
	  		<nav  class = "nav-bar">
	  			<a href="index.html">
	    			<img src="images/UnSorted_1.png" alt="Logo" id="logo">
	  			</a>
			</nav>
		</header>
		<section class = "body-container">
		
			<div class = "body-elements-container">
			
				<h1>Choose and view the specific columns of the tables in your database.</h1>
				<p class = "page-descript">Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
				   Aenean pulvinar tellus nulla. Aliquam luctus nec metus ac malesuada. 
				   Fusce convallis augue id diam pulvinar maximus. Maecenas eu ex turpis. Cras.</p>
				<form class = "form-container" action="columnSelector.jsp" target = "myFrame">
		  			<select id="table" name="tableName" class = "selectors">
		  			<%
		  				List<String> tableNames = dbOperations.getTableNames();
		  				for(String name : tableNames){
		  					out.println("<option value = "+name+">"+name+"</option>");
		  				}
		  			%>
		  			</select>
		  			<input type ="submit" value = "Fetch Table" class = "submit-btn">
				</form>
				
			</div>
			
			<iframe name = "myFrame" src = "columnSelector.jsp" style = "border : none;"></iframe>
			
		</section>
	</body>
</html>