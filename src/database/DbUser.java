package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUser {
    /**
     * Checks login against Database
     * @return Returns a boolean on if the username password matches the database
     * @throws SQLException if database query fails
     */
    public static boolean authentication(String username, String password) throws SQLException {
        try {
            String sqlQuery = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (resultSet.next());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    /**
     * Create a list of all users
     * @return List of all users in the database
     * @throws SQLException if database query fails
     */
    public static ObservableList<User> selectUsers() throws SQLException {
        try {
            ObservableList<User> users = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM users;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                User newUser = new User(
                        resultSet.getInt("User_ID"),
                        resultSet.getString("User_Name"),
                        resultSet.getString("Password")
                );

                users.add(newUser);
            }
            return users;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
