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


public class UpdateAppointment implements Initializable {
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
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
    private ComboBox<User> userComboBox;
    @FXML
    private ChoiceBox typeChoiceBox;

    Parent scene;
    Stage stage;
    private static Appointment updateAppointment;

    /**
     * return to schedule view
     */
    public void cancelButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * checks to ensure fields are not empty
     */
    private boolean fieldValidation() {
        return !titleTextField.getText().isEmpty() && !locationTextField.getText().isEmpty() && !descriptionTextField.getText().isEmpty() && contactComboBox.getValue() != null && startDatePicker.getValue() != null
                && endDatePicker.getValue() != null && typeChoiceBox.getValue() != null && customerComboBox.getValue() != null && userComboBox.getValue() != null && !startDateHour.getText().isEmpty() && !startDateMinute.getText().isEmpty() &&
                !endDateHour.getText().isEmpty() && !endDateMinute.getText().isEmpty();
    }
    /**
     * Saves an appointment within constraints
     * @throws SQLException if database query fails
     */
    public void saveButtonHandler(ActionEvent actionEvent) throws SQLException {
      if(fieldValidation()) {
          int appointmentId = Integer.parseInt(appointmentIDLabel.getText());
          String appointmentTitle = titleTextField.getText();
          String appointmentLocation = locationTextField.getText();
          String appointmentDescription = descriptionTextField.getText();
          Contact appointmentContact = contactComboBox.getValue();
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
          if (proposedStartEst.getHour() < 8 || proposedEndEst.getHour() > 20) {
              ConfirmationScreens.warningScreen("Outside Of Business Hours", "Business hours are 8AM to 10PM EST, including Weekends", "Please choose a different Time");
              return;
          }
       /*   try {
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
          }*/
          try {
              DbAppointment.updateAppointment(String.valueOf(appointmentContact), appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime, endTime, appointmentCustomer, appointmentUser, appointmentId);
          } catch (Exception e) {
              System.out.println("Error: " + e.getMessage());
          }
      }
      else{
          ConfirmationScreens.warningScreen("Check Fields", "One or More Fields are blank", "All Fields must be completed");
      }
    }
    /**
     * creates appointment types
     */
    private void appointmentTypeComboBox() {
        ObservableList<String> typeList = FXCollections.observableArrayList();

        typeList.addAll("Planning Session", "Debriefing", "Debugging", "Implementing", "On-boarding");

        typeChoiceBox.setItems(typeList);
    }

    /**
     * retrieves selected appointment from schedule view
     */
    public static void sendUpdateAppointment(Appointment appointment) {
        updateAppointment = appointment;
    }

    /**
     * Builds data into the view
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTypeComboBox();
        try {
            userComboBox.setItems((DbUser.selectUsers()));
            contactComboBox.setItems(DbContact.selectContacts());
            customerComboBox.setItems(DbCustomer.selectCustomers());

            Appointment appointment = DbAppointment.selectAppointmentById(updateAppointment.getAppointmentId());

            if (appointment != null) {
                appointmentIDLabel.setText(String.valueOf(appointment.getAppointmentId()));
                titleTextField.setText(appointment.getTitle());
                descriptionTextField.setText(appointment.getDescription());
                locationTextField.setText(appointment.getLocation());
                startDatePicker.setValue(appointment.getStartDate());
                startDateHour.setText(String.valueOf((appointment).getStartTime().getHour()));
                startDateMinute.setText(String.valueOf((appointment).getStartTime().getMinute()));
                endDatePicker.setValue(appointment.getEndDate());
                endDateHour.setText(String.valueOf((appointment).getStartTime().getHour()));
                endDateMinute.setText(String.valueOf((appointment).getStartTime().getMinute()));
                typeChoiceBox.getSelectionModel().select(appointment.getType());

                for(Contact contact: contactComboBox.getItems()){
                    if (contact.getContactId()==appointment.getContactId()){
                        contactComboBox.setValue(contact);
                        break;
                    }
                }
                for(Customer customer: customerComboBox.getItems()){
                    if (customer.getCustomerId()==appointment.getCustomerId()){
                        customerComboBox.setValue(customer);
                        break;
                    }
                }
                for(User user: userComboBox.getItems()){
                    if (user.getUserId()==appointment.getUserId()){
                        userComboBox.setValue(user);
                        break;
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}