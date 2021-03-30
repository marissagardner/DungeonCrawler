package sample.Java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {

    @FXML
    private Button playAgain;

    @FXML
    public void initialize() {
        playAgain = new Button("Play Again?");
    }

    public void goToStartScreen(ActionEvent event) throws IOException {
        Settings.setGameState(GameState.START_SCREEN);
        Parent menuParent = FXMLLoader.load(getClass().getResource("../FXML/start_screen.fxml"));
        Scene menuScene = new Scene(menuParent, 960, 600);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.setMinHeight(600);
        window.setMinWidth(960);
        window.setMaxHeight(600);
        window.setMaxWidth(960);
        window.show();
    }
}
