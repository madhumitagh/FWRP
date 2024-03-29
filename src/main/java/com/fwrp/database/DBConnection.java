
/**File name: DBConnection.java
 *Author: Madhumita Ghosh, #041119660
 *Course: CST8288-OOP
 *Assignment: Lab2
 *Date: Mar 03,2024
 *Professor Name: Gustavo Adami 
 */

package com.fwrp.database;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description: Provides a connection to the database using JDBC.
 */

/**
* @author Madhumita Ghosh
* @since 22
* @see com.fwrp.database.
*/
public class DBConnection {
	
    private static Connection connection = null;
    /**
     * Retrieves the database connection. If the connection is not established, it creates a new one.
     *
     * @param prop the properties containing database connection information
     * @return the database connection
     */
    private static Properties getProperty() {
        Properties dbProp = null;
        // Load database properties
        try (InputStream in = new FileInputStream("database.properties.cfg")){
            dbProp = new Properties();
            dbProp.load(in);
            in.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return dbProp;
    }
    public static Connection getConnection() {
	if (connection == null) {
            Properties prop = getProperty();
            
            if (prop != null) {
                try {
                    // Construct the JDBC connection URL
                    String serverUrl = String.format("jdbc:%s://%s:%s/%s",
                                                     prop.getProperty("db"),
                                                     prop.getProperty("host"),
                                                     prop.getProperty("port"),
                                                     prop.getProperty("name"));
                    // Establish the database connection
                    connection = DriverManager.getConnection(serverUrl,
                                                prop.getProperty("user"),
                                                prop.getProperty("pass"));
                } catch (SQLException e) {
                    System.out.println("Cannot Connect");
                }
                System.out.println("Connect Successfull");
            }
	}
	return connection;
    }
}
