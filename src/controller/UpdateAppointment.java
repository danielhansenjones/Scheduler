package controller;

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
import java.util.ResourceBundle;

public class UpdateAppointment implements Initializable {
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
    private ComboBox userComboBox;
    @FXML
    private ChoiceBox typeChoiceBox;

    Parent scene;
    Stage stage;

    public void cancelButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void saveButtonHandler(ActionEvent actionEvent) {
    }
}
