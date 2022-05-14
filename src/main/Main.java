package main;

import database.DatabaseAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("language/Lang", Locale.getDefault());

        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"),resourceBundle);
        primaryStage.setTitle("Scheduling System");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        DatabaseAccess.getConnection();
        DatabaseAccess.closeConnection();


    }

    public static void main(String[] args) {
        launch(args);
    }

}

