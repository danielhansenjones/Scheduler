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
import model.ConfirmationScreens;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Schedule implements Initializable {
    @FXML
    private TableColumn weekUserIdColumn;
    @FXML
    private TableColumn monthUserIdColumn;
    @FXML
    private TableColumn userIdColumn;
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
    /**
     * loads add appointment screen
     */
    public void addButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * loads update appointment screen  if an appointment is selected
     */
    public void updateButtonHandler(ActionEvent actionEvent) throws IOException {
        if(appointmentTableView.getSelectionModel().getSelectedItem() != null) {
            UpdateAppointment.sendUpdateAppointment((Appointment) appointmentTableView.getSelectionModel().getSelectedItem());
            scene = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            ConfirmationScreens.warningScreen("No Appointment Selected", "You must Select an Appointment", "Please choose from table");
        }
    }
    /**
     * loads customer data screen
     */
    public void customerButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerData.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * loads report data screen
     */
    public void reportsButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Deletes a selected customer.
     * @param actionEvent delete a selected customer on button press
     */
    public void deleteButtonHandler(ActionEvent actionEvent) throws IOException, SQLException {
        Appointment appointment = (Appointment) appointmentTableView.getSelectionModel().getSelectedItem();
        if (appointment != null) {
            if (ConfirmationScreens.confirmationScreen("You are about to delete: ", " Appointment ID: " + appointment.getAppointmentId() + " of type:  " + appointment.getType())) {
                try {
                    ConfirmationScreens.informationScreen("Appointment Deleted", "Appointment ID #  " + appointment.getAppointmentId(), "1 Line Updated");
                    DbAppointment.deleteAppointment(appointment.getAppointmentId());
                    appointmentTableView.setItems(DbAppointment.selectAppointments());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            ConfirmationScreens.warningScreen("No Appointment Selected", "You must Select an Appointment", "Please choose from table");
        }
    }
    /**
     * Shows all appointments
     */
    public void overviewTabHandler(Event event) throws SQLException {
        appointmentTableView.setItems(DbAppointment.selectAppointments());

        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    /**
     * shows all appointments this month
     */
    public void monthTabHandler(Event event) throws SQLException {

        appointmentMonthTableview.setItems(DbAppointment.selectAppointmentsByMonth());

        monthAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        monthTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        monthEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        monthCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * shows all appointments within one week
     */
    public void weekTabHandler(Event event) throws SQLException {
        appointmentWeekTableview.setItems(DbAppointment.selectAppointmentsByWeek());

        weekAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weekTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weekLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        weekContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        weekTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        weekStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        weekEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        weekCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        weekUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    /**
     * Builds data into the view
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            appointmentTableView.setItems(DbAppointment.selectAppointments());

            appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            contactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

