package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class menu_screen_controller {
    @FXML
    private ToggleGroup weaponGroup;

    @FXML
    private ToggleGroup difficultyGroup;

    @FXML
    public void handleWeaponToggleClick() {
        weaponGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
    }

    @FXML
    public void handleDiffcultyToggleClick() {
        difficultyGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
    }

    public void goToGameScreen(ActionEvent event) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource("game_screen.fxml"));
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
