package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by krystian on 20/03/2016.
 */

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scheme.fxml"));
        Scene scene = new Scene(root, 700, 350);
        stage.setTitle("The Single Machine Total Weighted Tardiness Problem");
        stage.setScene(scene);
        stage.show();
    }
}
