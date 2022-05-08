package controller;

import database.DbAppointment;
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
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    public void deleteButtonHandler(ActionEvent actionEvent) throws IOException, SQLException {
        Appointment appointment = (Appointment) appointmentTableView.getSelectionModel().getSelectedItem();
      /*  DbAppointment.deleteAppointment(appointment.getAppointmentId());*/ // this line is also crashing the program needs a rewrite.
    }


    public void overviewTabHandler(Event event) throws SQLException {
        appointmentTableView.setItems(DbAppointment.selectAppointments());

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    public void monthTabHandler(Event event) throws SQLException {

        appointmentMonthTableview.setItems(DbAppointment.selectAppointmentsByMonth());

        monthAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthLocationColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        monthTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        monthEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        monthCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }

    public void weekTabHandler(Event event) throws SQLException {
        appointmentWeekTableview.setItems(DbAppointment.selectAppointmentsByWeek());

        weekAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weekTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        weekLocationColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weekContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        weekTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        weekStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        weekEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        weekCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            appointmentTableView.setItems(DbAppointment.selectAppointments());

            appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

