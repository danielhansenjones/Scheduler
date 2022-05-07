package controller;

import database.DbAppointment;
import database.DbContact;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    @FXML
    private ComboBox customerComboBox1;
    @FXML
    private Tab CustomerScheduleTab;
    @FXML
    private TableView contactScheduleTableview1;
    @FXML
    private TableColumn appointmentIDColumn1;
    @FXML
    private TableColumn titleColumn1;
    @FXML
    private TableColumn descriptionColumn1;
    @FXML
    private TableColumn locationColumn1;
    @FXML
    private TableColumn contactColumn1;
    @FXML
    private TableColumn typeColumn1;
    @FXML
    private TableColumn startDateColumn1;
    @FXML
    private TableColumn endDateColumn1;
    @FXML
    private TableColumn customerIDColumn1;
    @FXML
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

    public void totalTabHandler(Event event) {
    }

    public void individualTabHandler(Event event) {
    }

    public void contactComboSelected(ActionEvent actionEvent) {
    }

    public void customerTabHandler(Event event) {
    }


    public void customerComboBoxSelected(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            contactComboBox.setItems(DbContact.selectContacts());
            contactScheduleTableview.setItems(DbAppointment.selectAppointments());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }



}
