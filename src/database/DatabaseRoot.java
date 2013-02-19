package database;

import java.sql.*;
import java.util.GregorianCalendar;

import util.DatabaseConfig;

public abstract class DatabaseRoot {

    protected Connection connection;
    protected Statement db;
    
    public DatabaseRoot() {

        DatabaseConfig config = new DatabaseConfig();

        String url = "jdbc:mysql://localhost:3306/" + config.getDatabaseName(); 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = config.getUserName();
            String password = config.getPassword();
            this.connection = DriverManager.getConnection(url,userName,password);
            this.db = connection.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("errorCNF");
        } catch (SQLException e) {
            System.out.println("errorSQL");
        }
    }
    
    // TODO rewrite it using simple date format
    public GregorianCalendar dateTimeToCalendar(String dataTime) {
        String[] xs = dataTime.split(" ");
        String[] ymd = xs[0].split("-");
        String[] hms = xs[1].split(":");
        int year = Integer.parseInt(ymd[0]);
        int month = Integer.parseInt(ymd[1]);
        int day = Integer.parseInt(ymd[2]);
        int hour = Integer.parseInt(hms[0]);
        int minute = Integer.parseInt(hms[1]);
        int second = Integer.parseInt(hms[2]);
        return new GregorianCalendar(year, month, day, hour, minute, second);
    }
}
