package controller;

import database.DbAppointment;
import database.DbContact;
import database.DbCustomer;
import database.DbUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;


public class AddAppointment implements Initializable {

    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private ComboBox<Contact> contactComboBox;
    @FXML
    private Label appointmentIDLabel;
    @FXML
    private ComboBox<Customer> customerComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private TextField startDateHour;
    @FXML
    private TextField startDateMinute;
    @FXML
    private TextField endDateHour;
    @FXML
    private TextField endDateMinute;
    @FXML
    private ChoiceBox typeChoiceBox;
    @FXML
    private ComboBox<User> userComboBox;

    Parent scene;
    Stage stage;
    public static int createId;

    /**
     * returns to schedule screen
     */
    public void cancelButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * checks for blank fields
     */
    private boolean fieldValidation() {
        return !titleTextField.getText().isEmpty() && !locationTextField.getText().isEmpty() && !descriptionTextField.getText().isEmpty() && contactComboBox.getValue() != null && startDatePicker.getValue() != null
                && endDatePicker.getValue() != null && typeChoiceBox.getValue() != null && customerComboBox.getValue() != null && userComboBox.getValue() != null && !startDateHour.getText().isEmpty() && !startDateMinute.getText().isEmpty() &&
                !endDateHour.getText().isEmpty() && !endDateMinute.getText().isEmpty();
    }
    /**
     * Adds a new appointment
     * checks against timezones to ensure appointment is within constraints not overlapping and not outside of business hours
     * if successful returns to schedule screen
     * @throws SQLException if database query fails
     */
    public void addButtonHandler(ActionEvent event) throws IOException, SQLException {
        if (fieldValidation()) {
        String appointmentTitle = titleTextField.getText();
        String appointmentLocation = locationTextField.getText();
        String appointmentDescription = descriptionTextField.getText();
        Contact contact = contactComboBox.getValue();
        String appointmentType = (String) typeChoiceBox.getValue();
        LocalDateTime startTime = LocalDateTime.of(startDatePicker.getValue(), LocalTime.of(Integer.parseInt(startDateHour.getText()), Integer.parseInt(startDateMinute.getText())));
        LocalDateTime endTime = LocalDateTime.of(endDatePicker.getValue(), LocalTime.of(Integer.parseInt(endDateHour.getText()), Integer.parseInt(endDateMinute.getText())));
        Integer appointmentCustomer = customerComboBox.getValue().getCustomerId();
        Integer appointmentUser = userComboBox.getValue().getUserId();

        ZonedDateTime zonedStartTimeLocal = startTime.atZone(ZoneId.systemDefault());
        ZonedDateTime startEst = zonedStartTimeLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalTime proposedStartEst = startEst.toLocalTime();

            ZonedDateTime zonedEndTimeLocal = endTime.atZone(ZoneId.systemDefault());
            ZonedDateTime endEst = zonedEndTimeLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
            LocalTime proposedEndEst = endEst.toLocalTime();

            if (startTime.isAfter(endTime) | (endTime.isEqual(startTime))) {
                ConfirmationScreens.warningScreen("Check Fields", "Start Time Cannot be after or during end Time", "Please choose a different Time");
                return;
            }
            if (proposedEndEst.getHour() > 20 | proposedStartEst.getHour() < 8 ){
                ConfirmationScreens.warningScreen("Outside Of Business Hours","Business hours are 8AM to 10PM EST, including Weekends","Please choose a different Time");
                return;
            }
            try {
                ObservableList<Appointment> appointments = DbAppointment.selectAppointmentsByCustomerId(customerComboBox.getValue().getCustomerId());
                for (Appointment appointment: appointments) {
                    LocalDateTime overlapStartTime = appointment.getStartDate().atTime(appointment.getStartTime().toLocalTime());
                    LocalDateTime overlapEndTime = appointment.getEndDate().atTime(appointment.getEndTime().toLocalTime());
                    if (overlapStartTime.isAfter(startTime)|overlapStartTime.isEqual(startTime) && overlapStartTime.isBefore(endTime)) {
                       ConfirmationScreens.warningScreen("Overlap","Customer appointments must not overlap","Selected a different time");
                        return; }
                        if (overlapStartTime.isBefore(startTime)|overlapStartTime.isEqual(startTime) && overlapEndTime.isAfter(endTime)| overlapEndTime.isEqual(endTime)) {
                            ConfirmationScreens.warningScreen("Overlap","Customer appointments must not overlap","Selected a different time");
                            return;
                    } else if (overlapEndTime.isAfter(startTime) && overlapEndTime.isBefore(endTime)| overlapEndTime.isEqual(endTime)) {
                        ConfirmationScreens.warningScreen("Overlap","Customer appointments must not overlap","Selected a different time");
                        return;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                DbAppointment.insertAppointment(String.valueOf(contact), appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime, endTime, appointmentCustomer, appointmentUser);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
   else{
            ConfirmationScreens.warningScreen("Check Fields", "One or More Fields are blank", "All Fields must be completed");
        }
    }
    /**
     * generates appointment types for the type box
     */
    private void appointmentTypeComboBox() {
        ObservableList<String> typeList = FXCollections.observableArrayList();

        typeList.addAll("Planning Session", "Debriefing", "Debugging", "Implementing", "On-boarding");

        typeChoiceBox.setItems(typeList);
    }

    /**
     * Builds data into the view
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentIDLabel.setText(String.valueOf(createId));
        appointmentTypeComboBox();

        try {
            contactComboBox.setItems(DbContact.selectContacts());
            customerComboBox.setItems(DbCustomer.selectCustomers());
            userComboBox.setItems(DbUser.selectUsers());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}