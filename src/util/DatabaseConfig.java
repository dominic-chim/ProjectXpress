package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabaseConfig {

    private String userName;
    private String password;
    private String databaseName;

    public DatabaseConfig() {

        File dbConfigFile = new File("src/config.csv");

        try {
            Scanner in = new Scanner(dbConfigFile);
            String line = in.nextLine();
            String[] settings = line.split(",");
            userName = settings[0].trim();
            password = settings[1].trim();
            databaseName = settings[2].trim();

        } catch (FileNotFoundException e) {
            userName = "root";
            password = "";
            databaseName = "gp12";
        }

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
