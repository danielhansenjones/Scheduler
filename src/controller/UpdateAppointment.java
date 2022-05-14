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


    public void cancelButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    public void saveButtonHandler(ActionEvent actionEvent) throws SQLException {
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
        LocalTime  proposedStartEst = startEst.toLocalTime();

        if (startTime.isAfter(endTime) | (endTime.isEqual(startTime))) {
            ConfirmationScreens.warningScreen("Check Fields", "Start Time Cannot be after or during end Time", "Please choose a different Time");
            return;
        }

        DbAppointment.updateAppointment(String.valueOf(appointmentContact), appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime, endTime, appointmentCustomer, appointmentUser, appointmentId);
    }

    private void appointmentTypeComboBox() {
        ObservableList<String> typeList = FXCollections.observableArrayList();

        typeList.addAll("Planning Session", "Debriefing", "Debugging", "Implementing", "On-boarding");

        typeChoiceBox.setItems(typeList);
    }


    public static void sendUpdateAppointment(Appointment appointment) {
        updateAppointment = appointment;
    }


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