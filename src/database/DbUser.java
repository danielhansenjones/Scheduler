package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUser {

    public static boolean authentication(String username, String password) throws SQLException {
        try {
            String sqlQuery = "SELECT * FROM users WHERE User_Name=? AND Password=?";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.getResultSet();
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.execute();

            return (resultSet.next());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public static ObservableList<User> selectUsers() throws SQLException {
        try {
            ObservableList<User> users = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM users;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.getResultSet();

            preparedStatement.execute();
            ;

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
