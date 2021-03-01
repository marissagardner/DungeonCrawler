package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class menu_screen_controller {
    @FXML
    private ToggleGroup weaponGroup;

    @FXML
    private ToggleGroup difficultyGroup;

    @FXML
    private ToggleButton easy;

    @FXML
    private ToggleButton medium;

    @FXML
    private ToggleButton hard;

    @FXML
    private ToggleButton sword;

    @FXML
    private ToggleButton bow;

    @FXML
    private ToggleButton axe;

    @FXML
    private Label gameTitle;

    @FXML
    private TextField nameInput;

    @FXML
    private Button startGame;

    @FXML
    public void initialize() {
        gameTitle = new Label("Forbidden Temple");
    }

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
            if (newVal == null) {
                oldVal.setSelected(true);
            }
        });
    }

    public void goToGameScreen(ActionEvent event) throws IOException {
        if (nameInput.getText().equals(null) ||
                nameInput.getText().replace(" ", "").equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Input a valid name.");
            a.show();
            System.out.println(a.getButtonTypes());
        } else if (!sword.isSelected() && !bow.isSelected() && !axe.isSelected()) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please select a weapon.");
            a.show();
        } else if (!easy.isSelected() && !medium.isSelected() && !hard.isSelected()) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please choose your difficulty.");
            a.show();
        } else {
            if (easy.isSelected()) {
                System.out.println("Easy");
                Settings.gameDifficulty = Difficulty.EASY;
                Settings.money = 100;
            } else if (medium.isSelected()) {
                System.out.println("Medium");
                Settings.gameDifficulty = Difficulty.MEDIUM;
                Settings.money = 75;
            } else if (hard.isSelected()) {
                System.out.println("Hard");
                Settings.gameDifficulty = Difficulty.HARD;
                Settings.money = 50;
            }

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

}
