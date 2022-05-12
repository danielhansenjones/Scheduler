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
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private ComboBox contactComboBox;
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
    public static int createId;
    private static Appointment updateAppointment;
    String[] Type = {"Planning Session", "Debriefing", "Debugging", "Implementing", "On-boarding"};
    private Appointment appointmentMod;

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
        Contact appointmentContact = (Contact) contactComboBox.getValue();
        String appointmentType = (String) typeChoiceBox.getValue();
        LocalDateTime startTime = LocalDateTime.of(startDatePicker.getValue(), LocalTime.of(Integer.parseInt(startDateHour.getText()), Integer.parseInt(startDateMinute.getText())));
        LocalDateTime endTime = LocalDateTime.of(endDatePicker.getValue(), LocalTime.of(Integer.parseInt(endDateHour.getText()), Integer.parseInt(endDateMinute.getText())));
        Integer appointmentCustomer = customerComboBox.getValue().getCustomerId();
        Integer appointmentUser = userComboBox.getValue().getUserId();
        DbAppointment.updateAppointment(String.valueOf(appointmentContact), appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime, endTime, appointmentCustomer, appointmentUser, appointmentId);
    }

    private void appointmentTypeComboBox() {
        ObservableList<String> typeList = FXCollections.observableArrayList();

        typeList.addAll("Planning Session", "Debriefing", "Debugging", "Implementing", "On-boarding");

        typeChoiceBox.setItems(typeList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTypeComboBox();
        try {

            contactComboBox.setItems(DbContact.selectContacts());
            customerComboBox.setItems(DbCustomer.selectCustomers());
            userComboBox.setItems((DbUser.selectUsers()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}