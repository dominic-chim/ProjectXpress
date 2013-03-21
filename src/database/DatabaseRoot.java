package database;

import java.sql.*;

import util.DatabaseConfig;

/**
 * 
 * base class for all database classes to get connect to database
 * 
 * @author Ke CHEN
 *
 */
public abstract class DatabaseRoot {

    protected static Connection connection;
    protected Statement db;
    
    public DatabaseRoot() {

    	// read configuration from config/config.csv
        DatabaseConfig config = new DatabaseConfig();

        
        String url = String.format("jdbc:mysql://%s/%s", config.getHost(), config.getDatabaseName()); 
                
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = config.getUserName();
            String password = config.getPassword();
            if (connection == null) 
            	connection = DriverManager.getConnection(url, userName, password);
            this.db = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("errorCNF");
        } catch (SQLException e) {
            System.out.println("errorSQL");
        }
        
    }
    
}
