package controller;

import javafx.event.ActionEvent;
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

public class CustomerData implements Initializable {
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
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField postalTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private Label regionLabel;
    @FXML
    private ComboBox countryComboBox;
    @FXML
    private ComboBox regionComboBox;
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

    public void backButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void countrySelectionHandler(ActionEvent actionEvent) {
    }

    public void addButtonHandler(ActionEvent actionEvent) {
    }

    public void deleteButtonHandler(ActionEvent actionEvent) {
    }

    @FXML
    private void cancelButtonHandler(ActionEvent actionEvent) {
            customerIDLabel.setText(String.valueOf(generateID));
            firstNameTextField.clear();
            lastNameTextField.clear();
            addressTextField.clear();
            postalTextField.clear();
            countryComboBox.getSelectionModel().clearSelection();
            regionComboBox.getSelectionModel().clearSelection();
            phoneTextField.clear();
    }


    public void modifyButtonHandler(ActionEvent actionEvent) {
    }

    public void saveButtonHandler(ActionEvent actionEvent) {
    }
}
