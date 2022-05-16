package database;

import model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbContact {
    /**
     * Create a list of all contacts
     * @return List of all contacts in the database
     * @throws SQLException if database query fails
     */
    public static ObservableList<Contact> selectContacts() throws SQLException {
        try {
            ObservableList<Contact> contacts = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM contacts AS contact INNER JOIN appointments AS appoint ON contact.Contact_ID = appoint.Contact_ID;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Forward scroll resultSet
            while (resultSet.next()) {
                Contact newContact = new Contact(
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name"),
                        resultSet.getString("Email")
                );

                contacts.add(newContact);
            }
            return contacts;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    /**
     * Create a list of all contacts matching a contact name
     * @param contactName select contact based on name
     * @return List of all contact matching name in database
     * @throws SQLException if database query fails
     */
    public static Contact selectContact(String contactName) throws SQLException {
        try {
            String sqlQuery = "SELECT * FROM contacts WHERE Contact_Name = ?";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, contactName);
            ResultSet resultSet = preparedStatement.executeQuery();


            // Forward scroll resultSet
            while (resultSet.next()) {
                Contact newContact = new Contact(
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name"),
                        resultSet.getString("Email")
                );

                return newContact;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}