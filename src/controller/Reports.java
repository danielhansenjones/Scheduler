package controller;

import database.DatabaseAccess;
import database.DbAppointment;
import database.DbContact;
import database.DbCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Contact;
import model.Customer;
import model.Report;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    @FXML
    private ComboBox<Customer> customerComboBox1;
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
    private ComboBox<Contact> contactComboBox;
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

    /**
     *return the user to the schedule screen
     */
    public void backButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * Checks all appointments by type and counts them based on SQL query in database
     * to create a list showing all appointments by type counted within the month
     * @param event when tab clicked
     */
    public void totalTabHandler(Event event) throws SQLException {
        try {
            ObservableList<Appointment> typeMonthReport = FXCollections.observableArrayList();
            String sqlQuery = "SELECT MonthName(Start) AS monthName , Type AS typeName, COUNT(*) AS amount FROM appointments GROUP BY MONTHNAME (Start), Type";
            PreparedStatement preparedStatement = DatabaseAccess.getConnection().prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String month = resultSet.getString("monthName");

                String type = resultSet.getString("typeName");

                String amount = resultSet.getString("amount");

                typeMonthReport.add(new Report(month, type, amount));
            }
            monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
            monthTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
            monthTypeTableview.getItems().setAll(typeMonthReport);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Filters all appointments by contact combo box
     * @throws SQLException if database query fails
     */
    public void contactComboSelected(ActionEvent actionEvent) throws SQLException {
        {
            if (contactComboBox.getValue() == null) {
                contactScheduleTableview.getSelectionModel().clearSelection();
            } else
                try {
                    Contact aContact = contactComboBox.getValue();
                    int contactId = aContact.getContactId();
                    contactScheduleTableview.setItems(DbAppointment.selectAppointmentsByContactId(contactId));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
    /**
     * Shows appointments by customer
     *
     * @param event when tab clicked
     */
    public void customerTabHandler(Event event) {
        try {

            customerComboBox1.setItems(DbCustomer.selectCustomers());
            contactScheduleTableview1.setItems(DbAppointment.selectAppointments());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        appointmentIDColumn1.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn1.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn1.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationColumn1.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactColumn1.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeColumn1.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn1.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn1.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        customerIDColumn1.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    /**
     * Filters all appointments by customer combo box
     * @throws SQLException if database query fails
     */
    public void customerComboBoxSelected(ActionEvent actionEvent) throws SQLException {
       {
            if (customerComboBox1.getValue() == null) {
                contactScheduleTableview1.getSelectionModel().clearSelection();
            } else {
                Customer customer= customerComboBox1.getValue();
                    int customerId = customer.getCustomerId();
                    contactScheduleTableview1.setItems(DbAppointment.selectAppointmentsByCustomerId(customerId));
            }
        }
    }
    /**
     * Builds data into the view
     */
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
