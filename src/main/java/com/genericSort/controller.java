package com.genericSort;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/sortIt")
public class controller extends HttpServlet{

	CachingModel model = CachingModel.getInstance();
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String dbName = req.getParameter("dataBase");
		String tableName = req.getParameter("table");
		String columnName = req.getParameter("column");
		
		Sorter sorter = new Sorter();
		
		ArrayList<?> sortedList = sorter.sort1(columnName,tableName);
		
		HttpSession session = req.getSession();
		
		session.setAttribute("sortedList", sortedList);
//		session.setAttribute("dataBase", dbName);
//		session.setAttribute("table", tableName);
		session.setAttribute("column", columnName);
		session.setAttribute("model", model);
		
		
		
		try {
			res.sendRedirect("resultPage");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
