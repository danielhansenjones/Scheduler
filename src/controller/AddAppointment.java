package controller;

import database.DbAppointment;
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
    private ChoiceBox typeComboBox;
    @FXML
    private ComboBox userComboBox;

    Parent scene;
    Stage stage;

    public void cancelButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void addButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    void addButtonClicked(ActionEvent event) throws IOException, SQLException {
        //int appointmentID= ????
        String appointmentTitle = titleTextField.getText();
        String appointmentLocation = locationTextField.getText();
        String appointmentDescription = descriptionTextField.getText();
        String appointmentContact = (String) contactComboBox.getValue();
        String appointmentType = (String) typeComboBox.getValue();
        LocalDateTime startDate = startDatePicker.getValue().atTime(Integer.parseInt(startDateHour.getText()), Integer.parseInt(startDateMinute.getText()));
        Timestamp startTime = Timestamp.valueOf(startDate);
        LocalDateTime endDate = startDatePicker.getValue().atTime(Integer.parseInt(endDateHour.getText()), Integer.parseInt(endDateMinute.getText()));
        Timestamp endTime = Timestamp.valueOf(endDate);
        Integer appointmentCustomer = (Integer) customerComboBox.getValue();
        Integer appointmentUser = (Integer) userComboBox.getValue();

        DbAppointment.insertAppointment(appointmentContact, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startDate, endDate, appointmentCustomer, appointmentUser);


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
