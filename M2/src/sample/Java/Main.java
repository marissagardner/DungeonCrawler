package sample.Java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Settings.setGameState(GameState.START_SCREEN);
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/start_screen.fxml"));
        primaryStage.setTitle("Forbidden Forest");
        primaryStage.setScene(new Scene(root, 960, 600));
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(960);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(960);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
