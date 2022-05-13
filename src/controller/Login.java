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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private ResourceBundle rb;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void loginButtonHandler(ActionEvent actionEvent) throws IOException, SQLException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        boolean valid = database.DbUser.authentication(userIdTextField.getText(), passwordTextField.getText());

        /*try {
            if (valid) {

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();


                } else
                    model.ConfirmationScreens.warningScreen("Incorrect Password", "Check caps lock", "Try again");

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
            //Lambda expression prints out message when users login is successful.
            new Thread(() -> System.out.println(username + " Logged In Successfully"));

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
                //Lambda expression prints out message when users login is unsuccessful.
                new Thread(() -> System.out.println(username + "Login Incorrect"));

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
*/


    }}
