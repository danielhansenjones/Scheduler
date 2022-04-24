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

public class Schedule implements Initializable {
    public TableView appointmentTableView;
    public TableColumn appointmentIdColumn;
    public TableColumn titleColumn;
    public Tab overviewTab;
    public TableColumn descriptionColumn;
    public TableColumn locationColumn;
    public TableColumn contactColumn;
    public TableColumn typeColumn;
    public TableColumn endDateColumn;
    public TableColumn startDateColumn;
    public TableColumn customerIDColumn;
    public Tab monthTab;
    public TableColumn monthAppointmentIdColumn;
    public TableView appointmentMonthTableview;
    public TableColumn monthTitleColumn;
    public TableColumn weekAppointmentIdColumn;
    public TableColumn weekTitleColumn;
    public TableView appointmentWeekTableview;
    public Tab weekTab;
    public TableColumn weekDescriptionColumn;
    public TableColumn weekLocationColumn;
    public TableColumn weekContactColumn;
    public TableColumn weekTypeColumn;
    public TableColumn weekStartDateColumn;
    public TableColumn weekEndDateColumn;
    public TableColumn weekCustomerIDColumn;
    public Button customerButton;
    public Button addButton;
    public Button deleteButton;
    public TableColumn monthLocationColumn;
    public TableColumn monthDescriptionColumn;
    public TableColumn monthContactColumn;
    public TableColumn monthTypeColumn;
    public TableColumn monthStartDateColumn;
    public TableColumn monthCustomerIdColumn;
    public TableColumn monthEndDateColumn;
    public Button updateButton;
    public Label currentTimeLabel;
    public Label currentDateLabel;
    public Button reportsButton;
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
