package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCustomer {
    /**
     * Create a list of all customers
     * @return List of all customers in the database
     * @throws SQLException if database query fails
     */
    public static ObservableList<Customer> selectCustomers() throws SQLException {
        try {
            ObservableList<Customer> customers = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM customers AS customer INNER JOIN first_level_divisions AS division ON customer.Division_ID = division.Division_ID INNER JOIN countries AS country ON country.Country_ID = division.COUNTRY_ID;";
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
    /**
     * Adds a customer to the database using insert statement
     * @param name of the customer
     * @param address of the customer
     * @param postalCode of the customer
     * @param phone of the customer
     * @param division division model associated with customer
     * @return true/false if update is successful or fails
     * @throws SQLException if database query fails
     */
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
                System.out.println("No Database Changes");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Modifies a customer model by using an SQL update in the SQL server.
     * @param customerId ID of the customer
     * @param name name of the customer
     * @param address  of the customer
     * @param postalCode of the customer
     * @param phone of the customer
     * @param division of the division model obj associated with customer
     * @return List of all users in the database
     * @throws SQLException if database query fails
     */
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
                System.out.println("No Database Changes");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    /**
     * Deletes a customer based on customerId
     * @param customerId of customer
     * @return boolean true/false based on if record deleted
     * @throws SQLException if database query fails
     */
    public static boolean deleteCustomer(int customerId) throws SQLException {
        try {
            String sqlQuery = "DELETE from customers WHERE Customer_Id = ?";

            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setInt(1, customerId);
            preparedStatement.execute();
            
            if (preparedStatement.getUpdateCount() > 0) {
                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());
            } else {
                System.out.println("No Database Changes");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

}
