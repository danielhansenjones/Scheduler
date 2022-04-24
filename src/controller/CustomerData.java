package controller;

import javafx.event.ActionEvent;
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
    public TableView customerTableView;
    public TableColumn customerIdColumn;
    public TableColumn nameColumn;
    public TableColumn addressColumn;
    public TableColumn postalColumn;
    public TableColumn phoneColumn;
    public TableColumn countryColumn;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField addressTextField;
    public TextField postalTextField;
    public TextField phoneTextField;
    public Label regionLabel;
    public ComboBox countryComboBox;
    public ComboBox regionComboBox;
    public Label customerIDLabel;
    public Button addButton;
    public Button deleteButton;
    public Button cancelButton;
    public Button modifyButton;
    public Button saveButton;
    public Button backButton;
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

    public void cancelButtonHandler(ActionEvent actionEvent) {
    }

    public void modifyButtonHandler(ActionEvent actionEvent) {
    }

    public void saveButtonHandler(ActionEvent actionEvent) {
    }
}
