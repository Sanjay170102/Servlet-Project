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
	<%
		int pageId = Integer.parseInt(request.getParameter("page"));
		pageId = (pageId-1)*5;
	
	    String[] selectedColumns = request.getParameterValues("checkBoxGroup");
		String tableName = request.getParameter("tableName");
		
		request.setAttribute("tableName",tableName);
		request.setAttribute("columnNames", selectedColumns);
		
		if(selectedColumns!=null){
		List<List<String>> columnValues = dbOperations.getColumnsData(selectedColumns,tableName,pageId);
		request.setAttribute("columnValues", columnValues);
		}
		int totalPages = dbOperations.getNoOfRows(tableName);
		
		totalPages = (totalPages%5!=0) ? (totalPages/5+1) : (totalPages/5);
		
		request.setAttribute("totalPages",totalPages);
	%>
	
	<div class = "table-container">
	<c:choose>
		<c:when test="${not empty columnNames}">
		<table>
		    <thead>
		        <tr>
		            <c:forEach var="columnName" items="${columnNames}">
		                <th>${columnName}</th>
		            </c:forEach>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="i" begin="0" end="${fn:length(columnValues[0])-1}">
		            <tr>
		                <c:forEach var="columnIndex" begin="0" end="${fn:length(columnNames)-1}">
		                    <td>${columnValues[columnIndex][i]}</td>
		                </c:forEach>
		            </tr>
		        </c:forEach>
		    </tbody>
	   </table>
	   <div class = "next-page">
		   <c:forEach var = "i" begin = "1" end = "${totalPages}">
		   		<ul>
				 <form action = "tableViewer.jsp" method = "get">
				     <input type = "hidden" name = "page" value = "${i}">
				     <c:forEach var="col" items="${columnNames}">
					      <input type = "hidden" name = "checkBoxGroup" value = "${col}">
					 </c:forEach>
					 <input type = "hidden" name = "tableName" value ="${tableName }">
				     <li class = "pageNo-list">
				     	<input type = "submit" value = "${i}" class = "pageNo" >
				     </li>
				 </form>
				</ul>
		  </c:forEach>
	    </div>
	   </c:when> 
	   <c:otherwise>
	   	<h2>No Columns Selected</h2>
	   </c:otherwise>
   </c:choose>
   </div>
</body>
</html>