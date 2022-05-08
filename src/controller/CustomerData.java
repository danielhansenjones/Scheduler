package controller;

import database.DbCountry;
import database.DbCustomer;
import database.DbDivision;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerData implements Initializable {
    public Label divisionLabel;
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
    private ComboBox countryComboBox;
    @FXML
    private ComboBox divisionComboBox;
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

    public void backButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }



 // combo boxes
    public void countrySelectionHandler(ActionEvent actionEvent) throws SQLException {
        {
            if(countryComboBox.getValue() == null )
            {
                divisionComboBox.getSelectionModel().clearSelection();
            }
            else
            {
                divisionComboBox.setItems(DbDivision.selectDivisions());// need to figure out country combo boxes...
            }
        }
    }
    //combo boxes
    public void regionSelectionHandler(ActionEvent actionEvent) {
    }

    public void addButtonHandler(ActionEvent actionEvent) throws SQLException {
        String customerName = nameColumn.getText();
        String address = addressTextField.getText();
        String postalCode = postalTextField.getText();
        Country country = (Country) countryComboBox.getValue();
        Division division = (Division) divisionComboBox.getValue();
        String phone = phoneTextField.getText();

        DbCustomer.insertCustomer(customerName,address,postalCode,phone, String.valueOf(division));

    }

    public void deleteButtonHandler(ActionEvent actionEvent) throws SQLException {
        Customer selectedCustomer = (Customer) customerTableView.getSelectionModel().getSelectedItem();

        DbCustomer.deleteCustomer(((Customer) customerTableView.getSelectionModel().getSelectedItem()).getCustomerId());

    }

    @FXML
    private void cancelButtonHandler(ActionEvent actionEvent) {
            customerIDLabel.setText(String.valueOf(createId));
            nameTextField.clear();
            addressTextField.clear();
            postalTextField.clear();
            countryComboBox.getSelectionModel().clearSelection();
            divisionComboBox.getSelectionModel().clearSelection();
            phoneTextField.clear();
    }


    public void modifyButtonHandler(ActionEvent actionEvent) {
        Customer customer = (Customer) customerTableView.getSelectionModel().getSelectedItem();
        customerIDLabel.setText(String.valueOf(customer.getCustomerId()));
        nameTextField.setText(String.valueOf((customer).getCustomerName()));
        addressTextField.setText(String.valueOf((customer).getAddress()));
        postalTextField.setText(String.valueOf((customer).getPostalCode()));
        countryComboBox.setValue(customer.getCountry());
        divisionComboBox.setValue(customer.getDivision());
        phoneTextField.setText(String.valueOf((customer).getPhoneNumber()));

    }

    public void saveButtonHandler(ActionEvent actionEvent) {
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIDLabel.setText(String.valueOf(createId));
        try {
            customerTableView.setItems(DbCustomer.selectCustomers());
            countryComboBox.setItems(DbCountry.selectCountries());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

        saveButton.setVisible(false);
    }

}