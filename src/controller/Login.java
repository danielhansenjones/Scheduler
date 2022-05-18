package controller;

import database.DbAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Appointment;
import model.ConfirmationScreens;

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
    private Label locationTextField;
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

    /**
     * Builds data into the view
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = resourceBundle.getLocale();
        System.out.println(locale);
        System.out.println(Locale.getDefault());
        ZoneId id = ZoneId.systemDefault();

        locationTextField.setText(resourceBundle.getString("location")+ ":");
        passwordLabel.setText(resourceBundle.getString("password")+ ":");
        userIdLabel.setText(resourceBundle.getString("username")+ ":");
        locationLabel.setText(id.toString());
        languageTextLabel.setText(resourceBundle.getString("language") + ":");
        languageLabel.setText(resourceBundle.getString("french"));
        loginButton.setText(resourceBundle.getString("login"));

    }
    /**
     * First checks to see if the users it authenticated.
     * Then warns of the user has an upcoming appointment in 15 minutes.
     * if successful loads the schedule screen
     * logs all attempts based on username and time regardless of success or failure and notes which occurred.
     * @throws SQLException if database query fails
     */
    public void loginButtonHandler(ActionEvent actionEvent) throws IOException, SQLException {
        boolean valid = database.DbUser.authentication(userIdTextField.getText(), passwordTextField.getText());
        try {
            if (valid) {
                if(upcomingAppointments().size()>=1){
                    for (Appointment appointment: upcomingAppointments()){
                        ConfirmationScreens.informationScreen("You have an upcoming appointment","Appointment ID #: " + appointment.getAppointmentId(),"Appointment in less than 15 minutes" + appointment.getStartTime());
                    }
                }
                loginSuccessful(String.valueOf(userIdTextField.getText()));
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
            else {
                loginFailed(String.valueOf(userIdTextField.getText()));
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

    /**
     * Checks if login was successful.
     * @param username writes name of user who attempted to log in to login_activity .txt
     */
    public static void loginSuccessful(String username) {

        try {
            String activityLog = "login_activity.txt";
            FileWriter fileWriter = new FileWriter(activityLog, true);
            PrintWriter results = new PrintWriter(fileWriter);
            LocalDateTime localDateTime = LocalDateTime.now();
            results.println("User: " + username + " Successfully logged in at: " + Timestamp.valueOf(localDateTime));
            results.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Checks if login was failed.
     * @param username writes name of user who attempted to log in to login_activity .txt
     */
        public static void loginFailed(String username) {

            try {
                String activityLog = "login_activity.txt";
                FileWriter fileWriter = new FileWriter(activityLog, true);
                PrintWriter results = new PrintWriter(fileWriter);
                LocalDateTime localDateTime = LocalDateTime.now();
                results.println("User: " + username +  " Unsuccessful log in at: " + Timestamp.valueOf(localDateTime));
                results.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * Creates a list of all appointments.
     * Then checks against all appointments within 15 of start time.
     * if the user has an appointment within timeframe add to second list upcoming-appointments.
     * @throws SQLException if database query fails
     */
    public ObservableList<Appointment> upcomingAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = DbAppointment.selectAppointments();
        ObservableList<Appointment> upcomingAppointments = FXCollections.observableArrayList();
        if (allAppointments!= null){
            for (Appointment appointment : allAppointments){
                LocalDateTime start = appointment.getStartTime();
                LocalDateTime now = LocalDateTime.now();
                if (start.isBefore(now.plusMinutes(15))) {
                    if (start.isAfter(now)){
                        upcomingAppointments.add(appointment);
                    }
                }
            }
        }
        return upcomingAppointments;
    }

}




