package com.genericSort;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CachingModel<T>
{
    /// Variables
    final static String URL = "jdbc:postgresql://localhost:5432/";
    final static String DB_NAME ="GenericSort";
    final static String USERNAME = "postgres";
    final static String PASSWORD = "SKatk1611#";
    final static int MAX_ENTRIES = 3;
    
    static Connection connection = null;
    
    private Map<String, ArrayList<T>> cacheMemory;
    private static CachingModel instance;
    
    //Constructor
    
    private CachingModel() {
    	cacheMemory = new LinkedHashMap<>(16,0.75f,false) {
    		protected boolean removeEldestEntry(Map.Entry<String, ArrayList<T>> eldest) {
    	        return size() > MAX_ENTRIES;
    	    }
    	};
    }

    ///Methods
    private void addToHashMap(String Key, ArrayList<T> value)
    {
        cacheMemory.put(Key, value);
    }

    public ArrayList<T> getData(String columnName)
    {
        return cacheMemory.get(columnName);
    }
    
    public void clearCache() {
		cacheMemory.clear();
	}
	
	public void viewCache() {
		System.out.println(cacheMemory);
	}
	
	//connection related methods
    
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

    public ArrayList<T> getColumn(String TableName,String ColumnName)
    {
        if(cacheMemory.containsKey(ColumnName))
        {
        	System.out.println("Cache Memory used!");
        	System.out.println(cacheMemory);
            return getData(ColumnName);
        }
        openConnection();

        ArrayList<T> values=new ArrayList<>();
        try
        {
        	System.out.println("Cache Memory not used!");
            Statement statement;
            ResultSet resultSet;
            String query = "Select "+ColumnName+" From "+TableName+";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
            {
                while(resultSet.next())
                {
                    values.add((T) resultSet.getObject(1));
                }
                cacheMemory.put(ColumnName,values);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(cacheMemory);
        closeConnection();
        return values;
    }

	public static CachingModel getInstance() {
		if (instance == null) {
            instance = new CachingModel();
        }
        return instance;
	}
}
