package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**This class creates and closes the connection to a MySQL Database.*/
public class DatabaseAccess {


    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    /** This method starts the connection with the MYSQL Database
     */
    public static Connection getConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
        return connection;
    }


    /** This method closes the connection with the MYSQL Database */
    public static void closeConnection() {

        try {
            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}