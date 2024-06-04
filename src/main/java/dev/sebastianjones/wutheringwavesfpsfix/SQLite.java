package dev.sebastianjones.wutheringwavesfpsfix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLite {

    public static Connection connection;
    private static String url = "jdbc:sqlite:";
    private static final String databasePath = "\\Wuthering Waves Game\\Client\\Saved\\LocalStorage\\LocalStorage.db";

    public static void openConnection(String installationPath) {
        try {
            connection = DriverManager.getConnection(url + installationPath + databasePath);

            System.out.println("SQLite database opened.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if(connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static boolean alterFPSValue() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE LocalStorage SET value = json_set(value, '$.KeyCustomFrameRate', '120') WHERE key = 'GameQualitySetting';");
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }
    }
}
