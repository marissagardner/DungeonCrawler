package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class start_screen_controller {
    public void goToMenuScreen(ActionEvent event) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource("menu_screen.fxml"));
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
