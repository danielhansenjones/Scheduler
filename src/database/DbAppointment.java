package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DbAppointment {
    /**
     * Create a list of all appointments
     * @return List of all appointments in the database
     * @throws SQLException if database query fails
     */
    public static ObservableList<Appointment> selectAppointments() throws SQLException {
        try {
            ObservableList<Appointment> appointments = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM appointments AS appoint INNER JOIN contacts AS contact ON appoint.Contact_ID = contact.Contact_ID;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getTimestamp("Start").toLocalDateTime().toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getTimestamp("End").toLocalDateTime().toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Create a list of all appointments by a month
     * @return List of all appointments in the database within a month of now
     * @throws SQLException if database query fails
     */
    public static ObservableList<Appointment> selectAppointmentsByMonth() throws SQLException {

        try {
            ObservableList<Appointment> appointments = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM appointments AS appoint INNER JOIN contacts AS contact ON appoint.Contact_ID = contact.Contact_ID WHERE MONTH(START) = MONTH(Now());";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getTimestamp("Start").toLocalDateTime().toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getTimestamp("End").toLocalDateTime().toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Create a list of all appointments by a week
     * @return List of all appointments in the database within a week of now
     * @throws SQLException if database query fails
     */
    public static ObservableList<Appointment> selectAppointmentsByWeek() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            String sqlQuery = "SELECT * FROM appointments AS appoint INNER JOIN contacts AS contact ON appoint.Contact_ID = contact.Contact_ID WHERE YEARWEEK(START) = YEARWEEK(Now());";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getTimestamp("Start").toLocalDateTime().toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getTimestamp("End").toLocalDateTime().toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    /**
     * Add a new customer to the database using the insert query
     * @return boolean true/false based on if the update is successful
     * @param contactName string of contact name
     * @param title string of appointment title
     * @param description string of appointment description
     * @param location string of appointment location
     * @param type string of appointment type
     * @param start LocalDateTime of appointment start time
     * @param end LocalDateTime of appointment end time
     * @param customerId int of customerId
     * @param userID int of userID
     * @throws SQLException if database query fails
     */
    public static boolean insertAppointment(String contactName, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customerId, Integer userID) throws SQLException {
        try {
            Contact contact = DbContact.selectContact(contactName);
            String sqlQuery = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, Contact_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, location);
            preparedStatement.setString(4, type);
            preparedStatement.setTimestamp(5, Timestamp.valueOf((start)));
            preparedStatement.setTimestamp(6, Timestamp.valueOf((end)));
            preparedStatement.setInt(7, customerId);
            preparedStatement.setInt(8, contact.getContactId());
            preparedStatement.setInt(9, userID);
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
    /**
     * Delete an appointment from the database based on appointment ID
     * @return boolean true/false based on if the deletion is completed successfully
     * @throws SQLException if database query fails
     */
    public static boolean deleteAppointment(int appointmentId) throws SQLException {
        try {
            String sqlQuery = "DELETE from appointments WHERE Appointment_Id = ?";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setInt(1, appointmentId);
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
    /**
     * Modify an appointment using an update query in the database.
     * @param contactName string value of contact name
     * @param title string value of title of appointment
     * @param description string value of description of appointment
     * @param location string value of location of appointment
     * @param type string value of type of appointment
     * @param start LocalDateTime of start of appointment time
     * @param end LocalDateTime of end of appointment time
     * @param customerId int of customerID number
     * @param userID int of UserID number
     * @param appointmentID int of appointment ID number
     * @return boolean true/false based on if the update is completed successfully
     * @throws SQLException if database query fails
     */
    public static boolean updateAppointment(String contactName, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customerId, Integer userID, Integer appointmentID) throws SQLException {
        try {

            Contact contact = DbContact.selectContact(contactName);
            String sqlQuery = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID = ?;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, location);
            preparedStatement.setString(4, type);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
            preparedStatement.setInt(7, customerId);
            preparedStatement.setInt(8, contact.getContactId());
            preparedStatement.setInt(9, userID);
            preparedStatement.setInt(10, appointmentID);
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
    /**
     * Select an Appointment by a CustomerID
     * @param CustomerID  int value of customer ID
     * @return List of all appointments in the database matching given Customer ID
     * @throws SQLException if database query fails
     */
    public static ObservableList<Appointment> selectAppointmentsByCustomerId(int CustomerID) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            String sqlQuery = "SELECT * FROM appointments AS appoint INNER JOIN contacts AS contact ON appoint.Contact_ID = contact.Contact_ID WHERE Customer_ID = ?;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CustomerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Forward scroll resultSet
            while (resultSet.next()) {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    /**
     * Select an Appointment by an appointment ID
     * @param AppointmentID  int value of appointment ID
     * @return List of all appointments in the database matching given appointment ID
     * @throws SQLException if database query fails
     */
    public static Appointment selectAppointmentById(int AppointmentID) throws SQLException {
        try {
            ObservableList<Appointment> appointments = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM appointments AS appoint INNER JOIN contacts AS contact ON appoint.Contact_ID = contact.Contact_ID WHERE Appointment_ID = ?;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setInt(1, AppointmentID);
            ResultSet resultSet = preparedStatement.executeQuery();


            // Forward scroll resultSet
            while (resultSet.next()) {

                return new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    /**
     * Select an Appointment by a contactID
     * @param contactID  int value of contact ID
     * @return List of all appointments in the database matching given contact ID
     * @throws SQLException if database query fails
     */
    public static ObservableList<Appointment> selectAppointmentsByContactId(int contactID) throws SQLException {
        try {
            ObservableList<Appointment> appointments = FXCollections.observableArrayList();
            String sqlQuery = "SELECT * FROM appointments AS appoint INNER JOIN contacts AS contact ON appoint.Contact_ID = contact.Contact_ID WHERE appoint.Contact_ID = ?;";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setInt(1, contactID);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Forward scroll resultSet
            while (resultSet.next()) {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}