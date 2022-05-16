package controller;

import database.DbAppointment;
import database.DbCountry;
import database.DbCustomer;
import database.DbDivision;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerData implements Initializable {

    @FXML
    private Label divisionLabel;
   @FXML
    private TableColumn divisonColumn;
    @FXML
    private TableView customerTableView;
    @FXML
    private TableColumn customerIdColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn postalColumn;
    @FXML
    private TableColumn phoneColumn;
    @FXML
    private TableColumn countryColumn;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField postalTextField;
    @FXML
    private TextField phoneTextField;

    @FXML
    private ComboBox <Country> countryComboBox;
    @FXML
    private ComboBox <Division> divisionComboBox;
    @FXML
    private Label customerIDLabel;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;

    Parent scene;
    Stage stage;
    public static int createId;
    static ObservableList<Country> country;

    /**
     * returns to schedule view
     */
    public void backButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * when country is selected divisions are set to only be within that country
     */
    public void countrySelectionHandler(ActionEvent actionEvent) throws SQLException {
        {
            if (countryComboBox.getValue() == null) {
                divisionComboBox.getSelectionModel().clearSelection();
            } else {
                Country country= countryComboBox.getValue();
                int countryId = country.getCountryId();
                divisionComboBox.setItems(DbDivision.selectDivisionsByCountry((countryId)));
            }
        }
    }

    /**
     * checks fields to make sure they are not empty
     */
    private boolean fieldValidation() {
        return !nameTextField.getText().isEmpty() && divisionComboBox.getValue() != null && countryComboBox.getValue() != null && !postalTextField.getText().isEmpty()
                && !phoneTextField.getText().isEmpty() && !addressTextField.getText().isEmpty();
    }
    //combo boxes
    public void regionSelectionHandler(ActionEvent actionEvent) {
    }

    /**
     *
     * @return List of all appointments in the database matching given contact ID
     * @throws SQLException if database query fails
     */
    public void addButtonHandler(ActionEvent actionEvent) throws SQLException {
        String customerName = nameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalTextField.getText();
        Division division = divisionComboBox.getValue();
        String phone = phoneTextField.getText();
        if (fieldValidation()) {
            DbCustomer.insertCustomer(customerName, address, postalCode, phone, String.valueOf(division));
            try {
                customerTableView.setItems(DbCustomer.selectCustomers());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else { ConfirmationScreens.warningScreen("Check Fields","One or More Fields are blank","All Fields must be completed");
        }
    }

    /**
     * Deletes a selected customer
     * checks to see if the customer can be deleted however if it is restricted based on FK constraints it will throw a warning screen
     * after all checks are completed it will delete the customer.
     * @throws SQLException if database query fails
     */

    public void deleteButtonHandler(ActionEvent actionEvent) throws SQLException {
        Customer selectedCustomer = (Customer) customerTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            ConfirmationScreens.warningScreen("Error", "A customer was not selected", "You must select a customer to delete");
            return;
        }
        if (ConfirmationScreens.confirmationScreen("Delete Selected", "Are you sure you want to delete?  " + selectedCustomer)) {
            try {
                DbCustomer.deleteCustomer(((Customer) customerTableView.getSelectionModel().getSelectedItem()).getCustomerId());
                ConfirmationScreens.informationScreen("Customer Deleted", "" + selectedCustomer, "1 Line Updated");
                customerTableView.setItems(DbCustomer.selectCustomers());
            } catch (SQLException e) {
             ConfirmationScreens.warningScreen("Customer has booked appointments","Cannot Delete Customer", "Remove appointment first");
            }
            ObservableList<Appointment> allAppointments = DbAppointment.selectAppointments();
            for (Appointment appointment : allAppointments) {
                if (appointment.getCustomerId() == selectedCustomer.getCustomerId()) {
                    DbAppointment.deleteAppointment(appointment.getAppointmentId());
                }
            }
        }
    }


    /**
     * Clears all fields including selections
     */
    @FXML
    private void cancelButtonHandler(ActionEvent actionEvent) {
     ConfirmationScreens.confirmationScreen("Are you sure you want to Cancel?","This will clear all fields.");
        customerIDLabel.setText(String.valueOf(createId));
        nameTextField.clear();
        addressTextField.clear();
        postalTextField.clear();
        countryComboBox.getSelectionModel().clearSelection();
        divisionComboBox.getSelectionModel().clearSelection();
        phoneTextField.clear();
        customerTableView.getSelectionModel().clearSelection();
        deleteButton.setVisible(true);
        addButton.setVisible(true);
    }

    /**
     * Adds all information about a selected customer to the modified fields to allow for changes.
     * @throws SQLException if database query fails
     */

    public void modifyButtonHandler(ActionEvent actionEvent) throws SQLException {
        Customer customer = (Customer) customerTableView.getSelectionModel().getSelectedItem();
        if (customer != null) {
            customerIDLabel.setText(String.valueOf(customer.getCustomerId()));
            nameTextField.setText(String.valueOf((customer).getCustomerName()));
            addressTextField.setText(String.valueOf((customer).getAddress()));
            postalTextField.setText(String.valueOf((customer).getPostalCode()));
            countryComboBox.setValue(DbCountry.selectCountryId(customer.getCountry()));
            divisionComboBox.setValue(DbDivision.selectDivisionId(customer.getDivision()));
            phoneTextField.setText(String.valueOf((customer).getPhoneNumber()));

            saveButton.setVisible(true);
            deleteButton.setVisible(false);
            addButton.setVisible(false);
        } else {
            ConfirmationScreens.warningScreen("No Customer Selected", "You must Select a Customer", "Please choose from table");
        }
    }


    /**
     * Saves all fields to update a customer either modified or new.
     * @throws SQLException if database query fails
     */
    public void saveButtonHandler(ActionEvent actionEvent) throws SQLException {
        int customerId = Integer.parseInt(customerIDLabel.getText());
        String customerName = nameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalTextField.getText();
        Division division = divisionComboBox.getValue();
        String phone = phoneTextField.getText();
        try {
            DbCustomer.updateCustomer(customerId, customerName, address, postalCode, phone, String.valueOf(division));
            customerTableView.setItems(DbCustomer.selectCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds data into the view
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIDLabel.setText(String.valueOf(createId));

        try {
            country = DbCountry.selectCountries();
            customerTableView.setItems(DbCustomer.selectCustomers());
            countryComboBox.setItems(country);
            divisionComboBox.setItems(DbDivision.selectDivisions());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        divisonColumn.setCellValueFactory(new PropertyValueFactory<>("division"));
        saveButton.setVisible(false);
    }

}
