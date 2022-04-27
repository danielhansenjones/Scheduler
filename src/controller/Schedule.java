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

public class Schedule implements Initializable {
    @FXML
    private TableView appointmentTableView;
    @FXML
    private TableColumn appointmentIdColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private Tab overviewTab;
    @FXML
    private TableColumn descriptionColumn;
    @FXML
    private TableColumn locationColumn;
    @FXML
    private TableColumn contactColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn endDateColumn;
    @FXML
    private TableColumn startDateColumn;
    @FXML
    private TableColumn customerIDColumn;
    @FXML
    private Tab monthTab;
    @FXML
    private TableColumn monthAppointmentIdColumn;
    @FXML
    private TableView appointmentMonthTableview;
    @FXML
    private TableColumn monthTitleColumn;
    @FXML
    private TableColumn weekAppointmentIdColumn;
    @FXML
    private TableColumn weekTitleColumn;
    @FXML
    private TableView appointmentWeekTableview;
    @FXML
    private Tab weekTab;
    @FXML
    private TableColumn weekDescriptionColumn;
    @FXML
    private TableColumn weekLocationColumn;
    @FXML
    private TableColumn weekContactColumn;
    @FXML
    private TableColumn weekTypeColumn;
    @FXML
    private TableColumn weekStartDateColumn;
    @FXML
    private TableColumn weekEndDateColumn;
    @FXML
    private TableColumn weekCustomerIDColumn;
    @FXML
    private Button customerButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableColumn monthLocationColumn;
    @FXML
    private TableColumn monthDescriptionColumn;
    @FXML
    private TableColumn monthContactColumn;
    @FXML
    private TableColumn monthTypeColumn;
    @FXML
    private TableColumn monthStartDateColumn;
    @FXML
    private TableColumn monthCustomerIdColumn;
    @FXML
    private TableColumn monthEndDateColumn;
    @FXML
    private Button updateButton;
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Label currentDateLabel;
    @FXML
    private Button reportsButton;

    Parent scene;
    Stage stage;

    public void addButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void updateButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void customerButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerData.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void reportsButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void deleteButtonHandler(ActionEvent actionEvent) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void overviewTabHandler(Event event) {
    }

    public void monthTabHandler(Event event) {
    }

    public void weekTabHandler(Event event) {
    }
}
