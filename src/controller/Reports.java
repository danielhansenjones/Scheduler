package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    public Tab totalAppointmentsTab;
    public TableView monthTypeTableview;
    public TableColumn monthColumn;
    public TableColumn monthTypeColumn;
    public TableColumn amountColumn;
    public Tab individualScheduleTab;
    public TableView contactScheduleTableview;
    public TableColumn appointmentIDColumn;
    public TableColumn titleColumn;
    public TableColumn descriptionColumn;
    public TableColumn locationColumn;
    public TableColumn contactColumn;
    public TableColumn typeColumn;
    public TableColumn startDateColumn;
    public TableColumn endDateColumn;
    public TableColumn customerIDColumn;
    public ComboBox contactComboBox;
    public Tab totalUserNumbers;
    public TableView monthTypeTableview1;
    public Button backButton;
    public Label currentTimeLabel;
    public Label currentDateLabel;
    Parent scene;
    Stage stage;

    public void backButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void totalTabHandler(Event event) {
    }

    public void individualTabHandler(Event event) {
    }

    public void contactComboSelected(ActionEvent actionEvent) {
    }

    public void totalUsersTabHandler(Event event) {
    }
}
