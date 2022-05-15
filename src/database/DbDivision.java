package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbDivision {
    /**
     * Create a list of all divisions
     * @return List of all divisions in the database
     * @throws SQLException if database query fails
     */
    public static ObservableList<Division> selectDivisions() throws SQLException {
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        try {
            String sqlQuery = "SELECT * FROM first_level_divisions;";
            PreparedStatement preparedStatement = database.DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                Division newDivision = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("Country_ID")
                );

                divisions.add(newDivision);
            }
            return divisions;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Selects division based on ID
     * @return division by ID
     * @throws SQLException if database query fails
     */
    public static Division selectDivisionId(String division) throws SQLException {
        try {
            String sqlQuery = "SELECT * FROM first_level_divisions WHERE Division = ?";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, division);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("Country_ID")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Create a list of all divisions by country
     * @param countryId select by country id
     * @return List of all divisions based on selected country
     * @throws SQLException if database query fails
     */
    public static ObservableList<Division> selectDivisionsByCountry(int countryId) throws SQLException {
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        try {
            String sqlQuery = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?;";

            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setInt(1,(countryId));
            ResultSet resultSet = preparedStatement.executeQuery();

            // Forward scroll resultSet
            while (resultSet.next()) {

                Division newDivision = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("Country_ID")
                );

                divisions.add(newDivision);
            }
            return divisions;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
