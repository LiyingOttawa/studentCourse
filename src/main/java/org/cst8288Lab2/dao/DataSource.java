/**
 * File name: DataSource.java
 * Author: Liying Guo, 040858257
 * Course: CST8288 OOP with Design Patterns
 * Assignment: Lab2
 * Date: 2024-03-03
 * Professor: Gustavo Adami
 * Purpose: DataSource is a singleton class
 */
package org.cst8288Lab2.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * This class should manage the creation of a single instance of the database connection.
 * @author Liying Guo
 * @version 1.0
 * @since 2024-Feb-27
 */
public class DataSource {
	private static DataSource singleton;
	public static Connection connection = null;
	
	private String serverUrl = "jdbc:mysql://";
	private String userString = "root";
	private String passwordString = "root";
	
        private DataSource()
        { 
        }
        
        public static DataSource getInstance()
        {
            if(singleton == null)
            {
                singleton = new DataSource();
            }
            return singleton;
        }
        
        public Connection getConnection() throws SQLException
        {
            if(connection == null)
            {
                //Ensure that you use the Properties class to load values from the database.properties file
                Properties properties = new Properties();
                //Preserve this input path
                try (InputStream in = new FileInputStream("./data/database.properties")){
                    properties.load(in);
                    serverUrl=String.format("jdbc:%s://%s:%s/%s", 
                            properties.getProperty("db"),properties.getProperty("host"),
                            properties.getProperty("port"),properties.getProperty("name"));
                    userString = properties.getProperty("user");
                    passwordString = properties.getProperty("pass");

                } catch (IOException e){
                    e.printStackTrace();
                }
               connection = DriverManager.getConnection( serverUrl, userString, passwordString );
            }
            
            return connection;
        }
        
        public void closeConnection() throws SQLException
        {
            if(connection != null)
            {
               connection.close();
            }
            
            connection = null;
        }

}
