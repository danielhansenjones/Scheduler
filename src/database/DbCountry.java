
package database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCountry {

    public static ObservableList<Country> selectCountries() throws SQLException {
        try {
            ObservableList<Country> countries = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM countries;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();


            // Forward scroll resultSet
            while (resultSet.next()) {

                Country newCountry = new Country(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );

                countries.add(newCountry);
            }
            return countries;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static Country selectCountryId(String country) throws SQLException {
        try {
            String sqlQuery = "SELECT * FROM countries WHERE Country = ?";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                return new Country(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
