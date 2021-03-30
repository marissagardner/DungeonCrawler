package sample.Java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BattleScreenController {
    @FXML
    private ImageView character;

    @FXML
    private ImageView characteraxe;

    @FXML
    private ImageView characterbow;

    @FXML
    private Group man;

    @FXML
    private ImageView monster;

    ImageView player;

    @FXML
    public void initialize() {
        if (Settings.getPlayer().getWeapon().getName().equals("Sword")) {
            player = character;
        } else if (Settings.getPlayer().getWeapon().getName().equals("Bow")) {
            player = characterbow;
        } else {
            player = characteraxe;
        }
        man.getChildren().setAll(player);
        monster.setImage(new Image(Settings.getCurrentRoom().getMonster().getSpritePath()));
    }

    @FXML
    public void move(KeyEvent event) throws IOException {
//        System.out.println(Settings.getCurrentRoom().getRoomNum());
        if (event.getCode().equals((KeyCode.SPACE))) {
            Settings.getCurrentRoom().setDefeated(true);
            Parent menuParent = FXMLLoader.load(
                    getClass().getResource(Settings.getCurrentRoom().getRoomPath()));
            Scene menuScene = new Scene(menuParent, 960, 600);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(menuScene);
            window.setMinHeight(600);
            window.setMinWidth(960);
            window.setMaxHeight(600);
            window.setMaxWidth(960);
            window.show();
            menuScene.getRoot().requestFocus();
        }
    }
}
