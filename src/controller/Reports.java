package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
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

public class Reports implements Initializable {

    private Tab totalAppointmentsTab;
    @FXML
    private TableView monthTypeTableview;
    @FXML
    private TableColumn monthColumn;
    @FXML
    private TableColumn monthTypeColumn;
    @FXML
    private TableColumn amountColumn;
    @FXML
    private Tab individualScheduleTab;
    @FXML
    private TableView contactScheduleTableview;
    @FXML
    private TableColumn appointmentIDColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn descriptionColumn;
    @FXML
    private TableColumn locationColumn;
    @FXML
    private TableColumn contactColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn startDateColumn;
    @FXML
    private TableColumn endDateColumn;
    @FXML
    private TableColumn customerIDColumn;
    @FXML
    private ComboBox contactComboBox;
    @FXML
    private Tab totalUserNumbers;
    @FXML
    private TableView monthTypeTableview1;
    @FXML
    private Button backButton;
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Label currentDateLabel;

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
