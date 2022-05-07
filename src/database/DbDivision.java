package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbDivision {

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


    public static Division selectDivisionId(String division) throws SQLException {
        String sqlQuery = "SELECT * FROM first_level_divisions WHERE Division=?";

        PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        preparedStatement.setString(1, division);

        try {
            preparedStatement.execute();


            while (resultSet.next()) {
                Division newDivision = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("Country_ID")
                );
                return newDivision;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    public static ObservableList<Division> selectDivisionsByCountry(String country) throws SQLException {
        Country newCountry = DbCountry.selectCountryId(country);

        ObservableList<Division> divisions = FXCollections.observableArrayList();

        try {
            String sqlQuery = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?;";

            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            preparedStatement.setInt(1, newCountry.getCountryId());


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
