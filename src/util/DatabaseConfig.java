package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabaseConfig {

	private String host;
    private String userName;
    private String password;
    private String databaseName;

    public DatabaseConfig() {

        File dbConfigFile = new File("config/config.csv");

        try {
            Scanner in = new Scanner(dbConfigFile);
            String line = in.nextLine();
            String[] settings = line.split(",");
            host = settings[0].trim();
            userName = settings[1].trim();
            password = settings[2].trim();
            databaseName = settings[3].trim();
            in.close();

        } catch (FileNotFoundException e) {
        	host = "localhost:3306";
            userName = "root";
            password = "";
            databaseName = "gp12";
        }
    }
    
    // getters
    public String getHost() {
    	return host;
    }
    
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

}
