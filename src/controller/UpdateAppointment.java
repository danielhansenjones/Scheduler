package controller;

import javafx.event.ActionEvent;
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
    public Button saveButton;
    public Button cancelButton;
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public ComboBox contactComboBox;
    public Label appointmentIDLabel;
    public ComboBox customerComboBox;
    public DatePicker startDatePicker;
    public TextField startDateHour;
    public TextField startDateMinute;
    public TextField endDateHour;
    public TextField endDateMinute;
    public ComboBox userComboBox;
    public ChoiceBox typeChoiceBox;
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
