package main;

import database.DatabaseAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Required main class starts first view and loads it based on the user's language/location
 */
public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("language/Lang", Locale.getDefault());

        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"),resourceBundle);
        if (Locale.getDefault().toString().equals("en_US"))
            primaryStage.setTitle("Scheduling System");
        if (Locale.getDefault().toString().equals("fr_FR"))
            primaryStage.setTitle("Système de Planification");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        DatabaseAccess.getConnection();
        DatabaseAccess.closeConnection();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

