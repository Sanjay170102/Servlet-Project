<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
<%@ page import = "com.genericSort.Model.dbOperations"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="tableSorterStyle.css" rel="stylesheet">
<title></title>
</head>
<body>
 	<div class = "column-viewer">
 <%
 	String tableName = request.getParameter("tableName");
 	List<String> columnNames = dbOperations.getColumnNames(tableName);
 	request.setAttribute("columnNames",columnNames);
 	request.setAttribute("tableName",tableName);
%>
	
	<c:if test="${ not empty columnNames}">
		<form action = "tableViewer.jsp?page=1" class = "column-list" method = "post">
			<ul>
				<c:forEach var ="index" begin = "0" end = "${fn:length(columnNames)-1}">
					<li>
						<label for = "checkBox" class = "column-Name"${index}>${columnNames.get(index)}</label>
						<input type = "checkbox" id = "checkBox"${index} name = "checkBoxGroup" value = ${columnNames.get(index) } >
					</li>
				</c:forEach>
			</ul>
			<input type="hidden" name="tableName" value="${tableName}">
			<input type = "submit" value = "View Column" class = "submit-btn">
		</form>
	</c:if>
 	</div>
</body>
</html>