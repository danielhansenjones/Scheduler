package database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Division;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCustomer {

    public static ObservableList<Customer> selectCustomers() throws SQLException {
        try {
            ObservableList<Customer> customers = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM customers AS c INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID INNER JOIN countries AS co ON co.Country_ID=d.COUNTRY_ID;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Forward scroll resultSet
            while (resultSet.next()) {

                Customer newCustomer = new Customer(
                        resultSet.getInt("Customer_ID"),
                        resultSet.getString("Customer_Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Postal_Code"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Division"),
                        resultSet.getString("Country"),
                        resultSet.getInt("Division_ID")
                );

                customers.add(newCustomer);
            }
            return customers;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static boolean insertCustomer(String name, String address, String postalCode, String phone, String division) throws SQLException {


        try {
            Division newDivision = DbDivision.selectDivisionId(division);

            String sqlQuery = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, postalCode);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, newDivision.getDivisionId());
            preparedStatement.execute();

            if (preparedStatement.getUpdateCount() > 0) {
                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());
            } else {
                System.out.println("No change");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }


    public static boolean updateCustomer(int customerId, String name, String address, String postalCode, String phone, String division) throws SQLException {
        try {
            Division newDivision = DbDivision.selectDivisionId(division);

            String sqlQuery = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID = ?";

            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, postalCode);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, newDivision.getDivisionId());
            preparedStatement.setInt(6, customerId);
            preparedStatement.execute();

            if (preparedStatement.getUpdateCount() > 0) {
                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());
            } else {
                System.out.println("No change");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteCustomer(int customerId) throws SQLException {
        try {
            String sqlQuery = "DELETE from customers WHERE Customer_Id = ?";

            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setInt(1, customerId);
            preparedStatement.execute();


            if (preparedStatement.getUpdateCount() > 0) {
                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());
            } else {
                System.out.println("No change");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

}
