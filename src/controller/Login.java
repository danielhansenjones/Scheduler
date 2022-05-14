package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private Label passwordLabel;
    @FXML
    private Label userIdLabel;
    @FXML
    private TextField userIdTextField;
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label locationLabel;
    @FXML
    private Label languageLabel;
    @FXML
    private Label languageTextLabel;

    Parent scene;
    Stage stage;
    private ResourceBundle resourceBundle;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = resourceBundle.getLocale();
        System.out.println(locale);
        System.out.println(Locale.getDefault());
        ZoneId id = ZoneId.systemDefault();

        passwordLabel.setText(resourceBundle.getString("password")+ ":");
        userIdLabel.setText(resourceBundle.getString("username")+ ":");
        locationLabel.setText(id.toString());
        languageTextLabel.setText(resourceBundle.getString("language") + ":");
        languageLabel.setText(resourceBundle.getString("french"));
        loginButton.setText(resourceBundle.getString("login"));

    }

    public void loginButtonHandler(ActionEvent actionEvent) throws IOException, SQLException {
        boolean valid = database.DbUser.authentication(userIdTextField.getText(), passwordTextField.getText());
        try {
            if (valid) {
                loginSuccessful(String.valueOf(userIdTextField));
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
            else {
                loginFailed(String.valueOf(userIdTextField));
                if (Locale.getDefault().toString().equals("en_US"))
                    model.ConfirmationScreens.warningScreen("Incorrect Password", "Check caps lock", "Try again");
                if (Locale.getDefault().toString().equals("fr_FR"))
                    model.ConfirmationScreens.warningScreen("Mot de passe incorrect", "Vérifier le verrouillage des majuscules", "Réessayer");
            }
        }
             catch(Exception e){
                e.printStackTrace();
            }

        }


    public static void loginSuccessful(String username) {

        try {
            String activityLog = "login_activity.txt";
            File file = new File(activityLog);
            FileWriter fw = new FileWriter(activityLog, true);
            PrintWriter results = new PrintWriter(fw);
            LocalDateTime localDateTime = LocalDateTime.now();
            results.println("User: " + username + " Successfully logged in at: " + Timestamp.valueOf(localDateTime));
            results.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void loginFailed(String username) {

            try {
                String activityLog = "login_activity.txt";
                File file = new File(activityLog);
                FileWriter fw = new FileWriter(activityLog, true);
                PrintWriter results = new PrintWriter(fw);
                LocalDateTime localDateTime = LocalDateTime.now();
                results.println("User: " + username +  " Unsuccessful log in at: " + Timestamp.valueOf(localDateTime) );
                results.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

}




