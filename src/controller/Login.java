package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public Label passwordLabel;
    public Label userIdLabel;
    public TextField userIdTextField;
    public Button loginButton;
    public PasswordField passwordTextField;
    public Label locationLabel;
    public Label languageLabel;
    public Label languageTextLabel;
    Parent scene;
    Stage stage;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginButtonHandler(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
