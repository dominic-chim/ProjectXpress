package database;

import java.sql.*;

public abstract class DatabaseRoot {

    protected Connection connection;
    protected Statement db;
    
    public DatabaseRoot() {

        String url = "jdbc:mysql://localhost:3306/scheduling"; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "root"; 
            String password = "123"; 
            this.connection = DriverManager.getConnection(url,userName,password);
            this.db = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("errorCNF");
        } catch (SQLException e) {
            System.out.println("errorSQL");
        }
    }
}
