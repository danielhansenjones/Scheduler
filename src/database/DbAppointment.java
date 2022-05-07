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

        public static ObservableList<Appointment> selectAppointments() throws SQLException {
            try {
                ObservableList<Appointment> appointments = FXCollections.observableArrayList();
                String sqlQuery = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID;";
                PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();


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


        public static ObservableList<Appointment> selectAppointmentsByMonth() throws SQLException {
            try {
                ObservableList<Appointment> appointments = FXCollections.observableArrayList();
                LocalDateTime currentDate = LocalDateTime.now();
                LocalDateTime lastMonth = currentDate.minusDays(30);
                String sqlQuery = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID WHERE Start < ? AND Start > ?;";
                PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                preparedStatement.setDate(1, java.sql.Date.valueOf(currentDate.toLocalDate()));
                preparedStatement.setDate(2, java.sql.Date.valueOf(lastMonth.toLocalDate()));




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


        public static ObservableList<Appointment> selectAppointmentsByWeek() throws SQLException {
            ObservableList<Appointment> appointments = FXCollections.observableArrayList();

            try {
                LocalDateTime currentDate = LocalDateTime.now();
                LocalDateTime lastWeek = currentDate.minusDays(7);

                String sqlQuery = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID WHERE Start < ? AND Start > ?;";

                PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();

                preparedStatement.setDate(1, java.sql.Date.valueOf(currentDate.toLocalDate()));
                preparedStatement.setDate(2, java.sql.Date.valueOf(lastWeek.toLocalDate()));

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

      public static boolean insertAppointment(String contactName, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customerId, Integer userID) throws SQLException {
            try {
                Contact contact = DbContact.selectContactId(contactName);
                String sqlQuery = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, Contact_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
                 preparedStatement.executeQuery();

                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);
                preparedStatement.setString(3, location);
                preparedStatement.setString(4, type);
                preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
                preparedStatement.setInt(7, customerId);
                preparedStatement.setInt(8, contact.getContactId());
                preparedStatement.setInt(9, userID);

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


        public static boolean updateAppointment(String contactName, String title, String description, String location, String type, LocalDateTime start_time, LocalDateTime end, Integer customerId, Integer userID, Integer appointmentID) throws SQLException {


            try {

                Contact contact = DbContact.selectContactId(contactName);

                String sqlQuery = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID = ?;";

                PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
                preparedStatement.executeQuery();

                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);
                preparedStatement.setString(3, location);
                preparedStatement.setString(4, type);
                preparedStatement.setTimestamp(5, Timestamp.valueOf(start_time));
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


        public static ObservableList<Appointment> selectAppointmentsByCustomerId(int CustomerID) throws SQLException {
            ObservableList<Appointment> appointments = FXCollections.observableArrayList();

            try {

                String sqlQuery = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID WHERE Customer_ID = ?;";
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


        public static ObservableList<Appointment> selectAppointmentsByContactId(int contactID) throws SQLException {
            try {
                ObservableList<Appointment> appointments = FXCollections.observableArrayList();
                String sqlQuery = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID WHERE a.Contact_ID = ?;";
                PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                preparedStatement.setInt(1, contactID);
                preparedStatement.execute();


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

        public static Appointment selectAppointmentById(int AppointmentID) throws SQLException {
            try {
                ObservableList<Appointment> appointments = FXCollections.observableArrayList();
                String sqlQuery = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID WHERE Appointment_ID = ?;";
                PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                preparedStatement.setInt(1, AppointmentID);



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
    }

