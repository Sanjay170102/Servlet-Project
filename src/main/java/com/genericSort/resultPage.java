package com.genericSort;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/resultPage")
public class resultPage extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session = req.getSession();
		
//		String dbName = (String) session.getAttribute("dataBase");
//		String tableName = (String) session.getAttribute("table");
		String columnName = (String) session.getAttribute("column");
				
		ArrayList<?> sortedList = (ArrayList<?>)session.getAttribute("sortedList");
		
		
		try {
			PrintWriter out = res.getWriter();
			out.println("<html>"
					+ "<head>"
					+   "<link href=\"styles.css\" rel=\"stylesheet\">"
					+  "<title>Table Sorter</title> </head><body><header class = \"navbar-container\">\r\n"
					+ "	<nav  class = \"nav-bar\">\r\n"
					+ "	  <a href=\"index.html\">\r\n"
					+ "	    <img src=\"images/UnSorted_1.png\" alt=\"Logo\" id=\"logo\">\r\n"
					+ "	  </a>\r\n"
					+ "	</nav>\r\n"
					+ "	</header>"
					+ "<Section class = formContainer><h1>The Sorted List of ");
		  out.println(columnName+ "<br>");
			out.println(sortedList);
			out.println("</h1></section></body></html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
