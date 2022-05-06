package database;
import model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbContact {

    public static ObservableList<Contact> selectContacts() throws SQLException {
        try {
            ObservableList<Contact> contacts = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM contacts AS c INNER JOIN appointments AS a ON c.Contact_ID = a.Contact_ID;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.getResultSet();
            preparedStatement.execute();


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

    public static Contact selectContactId(String contactName) throws SQLException {
        try {
            String sqlQuery = "SELECT * FROM contacts WHERE Contact_Name=?";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.getResultSet();
            preparedStatement.setString(1, contactName);

            preparedStatement.execute();

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