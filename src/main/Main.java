package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// FUTURE ENHANCEMENT
// The largest thing that I would like to see for the future of this project is more styling added to the FXML files,
// currently the screens are very functional but could be more aesthetically pleasing, the ability to resize windows and have the program resize
// automatically with the user would be a great add.

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

