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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class AddAppointment implements Initializable {
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
    private ComboBox customerComboBox;
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
    private ComboBox userComboBox;


    Parent scene;
    Stage stage;
    public static int createId;


    public void cancelButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


   public void addButtonHandler(ActionEvent event) throws IOException, SQLException {
        try {
            String appointmentTitle = titleTextField.getText();
            String appointmentLocation = locationTextField.getText();
            String appointmentDescription = descriptionTextField.getText();
            String appointmentContact = (String) contactComboBox.getValue();
            String appointmentType = (String) typeChoiceBox.getValue();
            LocalDateTime startDate = startDatePicker.getValue().atTime(Integer.parseInt(startDateHour.getText()), Integer.parseInt(startDateMinute.getText()));
            Timestamp startTime = Timestamp.valueOf(startDate);
            LocalDateTime endDate = startDatePicker.getValue().atTime(Integer.parseInt(endDateHour.getText()), Integer.parseInt(endDateMinute.getText()));
            Timestamp endTime = Timestamp.valueOf(endDate);
            Integer appointmentCustomer = (Integer) customerComboBox.getValue();
            Integer appointmentUser = (Integer) userComboBox.getValue();

            DbAppointment.insertAppointment(appointmentContact, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime, endTime, appointmentCustomer, appointmentUser);


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("Schedule.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }

    }

    private void appointmentTypeComboBox() {
        ObservableList<String> typeList = FXCollections.observableArrayList();

        typeList.addAll("Planning Session", "Debriefing", "Debugging", "Implementing", "On-boarding");

        typeChoiceBox.setItems(typeList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentIDLabel.setText(String.valueOf(createId));
        appointmentTypeComboBox();
        try {
            contactComboBox.setItems(DbContact.selectContacts());
            customerComboBox.setItems(DbCustomer.selectCustomers());
            userComboBox.setItems((DbUser.selectUsers()));

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}