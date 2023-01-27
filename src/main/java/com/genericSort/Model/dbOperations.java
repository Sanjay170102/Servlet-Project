package com.genericSort.Model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dbOperations {
	/// Variables
    final static String URL = "jdbc:postgresql://localhost:5432/";
    final static String DB_NAME ="PLSQL_Tutorial";
    final static String USERNAME = "postgres";
    final static String PASSWORD = "SKatk1611#";
    
    static Connection connection = null;
    
    private static String table;
    
    static List<List<String>> columnDataList = new ArrayList<>();
    
    static List<String> records = new ArrayList<>();
    
    /// Connection Methods
    
    private static void openConnection()
    {
        try {
        	Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    /// DB Access Methods
    
    public static List<String> getTableNames(){
    	String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';";
    	Statement statement;
    	ResultSet resultSet;
    	records.clear();
    	openConnection();
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery(query);
    		while(resultSet.next()) {
    			records.add(resultSet.getString("table_name"));
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	closeConnection();
    	return records;
    }
    
    public static List<String> getColumnNames(String tableName){
    	table = tableName;
    	String query = "SELECT column_name FROM information_schema.columns WHERE table_name = '"+tableName+"';";
    	Statement statement;
    	ResultSet resultSet;
    	records.clear();
    	openConnection();
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery(query);
    		while(resultSet.next()) {
    			records.add(resultSet.getString("column_name"));
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	closeConnection();
    	return records;
    }
    
    public static List<List<String>> getColumnsData(String[] columnNames,String table,int id){
    	openConnection();
    	columnDataList.clear();
    	for(String columnName : columnNames) {
    		List<String> columnData = new ArrayList<>();
	    	String query = "SELECT "+columnName+" FROM "+table+" LIMIT 5 OFFSET "+id+";";
	    	Statement statement;
	    	ResultSet resultSet;
	    	try {
	    		statement = connection.createStatement();
	    		resultSet = statement.executeQuery(query);
	    		while(resultSet.next()) {
	    			columnData.add(resultSet.getString(1));
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	columnDataList.add(columnData);
    	}
	    	closeConnection();
    	return columnDataList;
    }
    
    public static int getNoOfRows(String tableName) {
    	int totalRows = 0;
    	openConnection();
    	try {
    		String query = "SELECT COUNT(*) FROM "+tableName+" ;";
    		Statement statement;
    		ResultSet resultSet;
    		
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery(query);
    		
    		if(resultSet.next()) {
    			totalRows = resultSet.getInt(1);
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	closeConnection();
    	return totalRows;
    }
    
}
