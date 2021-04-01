package sample.Java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ExitRoomController {

    //Only moves one room each time an arrow is pressed
    private boolean acceptInput = false;

    @FXML
    private ImageView character;

    @FXML
    private ImageView characterL;

    @FXML
    private ImageView character1;

    @FXML
    private ImageView character2;

    @FXML
    private ImageView character3;

    @FXML
    private ImageView character4;

    @FXML
    private ImageView characterL1;

    @FXML
    private ImageView characterL2;

    @FXML
    private ImageView characterL3;

    @FXML
    private ImageView characterL4;

    @FXML
    private ImageView characteraxe;

    @FXML
    private ImageView characteraxeL;

    @FXML
    private ImageView characteraxe1;

    @FXML
    private ImageView characteraxe2;

    @FXML
    private ImageView characteraxe3;

    @FXML
    private ImageView characteraxe4;

    @FXML
    private ImageView characteraxeL1;

    @FXML
    private ImageView characteraxeL2;

    @FXML
    private ImageView characteraxeL3;

    @FXML
    private ImageView characteraxeL4;

    @FXML
    private ImageView characterbow;

    @FXML
    private ImageView characterbowL;

    @FXML
    private ImageView characterbow1;

    @FXML
    private ImageView characterbow2;

    @FXML
    private ImageView characterbow3;

    @FXML
    private ImageView characterbow4;

    @FXML
    private ImageView characterbowL1;

    @FXML
    private ImageView characterbowL2;

    @FXML
    private ImageView characterbowL3;

    @FXML
    private ImageView characterbowL4;

    @FXML
    private Group man;

    ImageView player;
    ImageView player1;
    ImageView player2;
    ImageView player3;
    ImageView player4;
    ImageView playerL;
    ImageView playerL1;
    ImageView playerL2;
    ImageView playerL3;
    ImageView playerL4;
    @FXML
    public void initialize() {
        if (Settings.getPlayer().getWeapon().getName().equals("Sword")) {
            player = character;
            player1 = character1;
            player2 = character2;
            player3 = character3;
            player4 = character4;
            playerL = characterL;
            playerL1 = characterL1;
            playerL2 = characterL2;
            playerL3 = characterL3;
            playerL4 = characterL4;
        } else if (Settings.getPlayer().getWeapon().getName().equals("Bow")) {
            player = characterbow;
            player1 = characterbow1;
            player2 = characterbow2;
            player3 = characterbow3;
            player4 = characterbow4;
            playerL = characterbowL;
            playerL1 = characterbowL1;
            playerL2 = characterbowL2;
            playerL3 = characterbowL3;
            playerL4 = characterbowL4;
        } else {
            player = characteraxe;
            player1 = characteraxe1;
            player2 = characteraxe2;
            player3 = characteraxe3;
            player4 = characteraxe4;
            playerL = characteraxeL;
            playerL1 = characteraxeL1;
            playerL2 = characteraxeL2;
            playerL3 = characteraxeL3;
            playerL4 = characteraxeL4;
        }
        man.getChildren().setAll(player);
    }

    //Unused right now, onKeyReleased could call this method for walk functionality
    @FXML
    public void reset(KeyEvent event) throws IOException {
        t.stop();
        tL.stop();
        if (right) {
            man.getChildren().setAll(player);
        } else if (left) {
            man.getChildren().setAll(playerL);
        }
    }

    Timeline t = new Timeline();
    Timeline tL = new Timeline();
    boolean right = true;
    boolean left = false;
    @FXML
    public void move(KeyEvent event) throws IOException {
        t.setCycleCount(Timeline.INDEFINITE);
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(100),
                (ActionEvent e) -> {
                    man.getChildren().setAll(player1);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                (ActionEvent e) -> {
                    man.getChildren().setAll(player2);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300),
                (ActionEvent e) -> {
                    man.getChildren().setAll(player3);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent e) -> {
                    man.getChildren().setAll(player4);
                }
        ));
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(500),
                (ActionEvent e) -> {
                    man.getChildren().setAll(player);
                }
        ));

        tL.setCycleCount(Timeline.INDEFINITE);
        tL.getKeyFrames().add(new KeyFrame(
                Duration.millis(100),
                (ActionEvent e) -> {
                    man.getChildren().setAll(playerL1);
                }
        ));
        tL.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                (ActionEvent e) -> {
                    man.getChildren().setAll(playerL2);
                }
        ));
        tL.getKeyFrames().add(new KeyFrame(
                Duration.millis(300),
                (ActionEvent e) -> {
                    man.getChildren().setAll(playerL3);
                }
        ));
        tL.getKeyFrames().add(new KeyFrame(
                Duration.millis(400),
                (ActionEvent e) -> {
                    man.getChildren().setAll(playerL4);
                }
        ));
        tL.getKeyFrames().add(new KeyFrame(
                Duration.millis(500),
                (ActionEvent e) -> {
                    man.getChildren().setAll(playerL);
                }
        ));

        if (event.getCode().equals((KeyCode.UP))) {
            if (right) {
                t.play();
            } else if (left) {
                tL.play();
            }
            if (character.getLayoutY() - 9 >= 0
                    && character.getLayoutX() >= 0 && character.getLayoutX() <= 672) {
                character.setLayoutY(character.getLayoutY() - 9);
            }

            if (Settings.getCurrentRoom().hasNorthExit()) {
                if (character.getLayoutX() > 150 && character.getLayoutX() < 520) {
                    character.setLayoutY(character.getLayoutY() - 9);
                }
                if (character.getLayoutY() <= -150) {
                    //Settings.setCurrentRoom(Settings.getCurrentRoom().getNorthRoom());
                    acceptInput = false;

                    Parent menuParent = FXMLLoader.load(
                            getClass().getResource("../FXML/end_screen.fxml"));
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
        } else if (event.getCode().equals((KeyCode.DOWN))) {
            if (right) {
                t.play();
            } else if (left) {
                tL.play();
            }

            if (character.getLayoutY() + 9 <= 198
                    && character.getLayoutX() >= 0 && character.getLayoutX() <= 672) {
                character.setLayoutY(character.getLayoutY() + 9);
            }

            if (Settings.getCurrentRoom().hasSouthExit()) {
                if (character.getLayoutX() > 280 && character.getLayoutX() < 400) {
                    character.setLayoutY(character.getLayoutY() + 9);
                }

                if (character.getLayoutY() >= 380) {
                    Settings.setCurrentRoom(Settings.getCurrentRoom().getSouthRoom());
                    acceptInput = false;

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
        } else if (event.getCode().equals((KeyCode.RIGHT))) {
            t.play();
            right = true;
            left = false;
            if (character.getLayoutX() + 9 <= 672
                    && character.getLayoutY() >= 0 && character.getLayoutY() <= 198) {
                character.setLayoutX(character.getLayoutX() + 9);
            }

            if (Settings.getCurrentRoom().hasNorthExit()
                    || Settings.getCurrentRoom().hasSouthExit()) {
                if (character.getLayoutY() > 198 && character.getLayoutX() + 9 <= 400) {
                    character.setLayoutX(character.getLayoutX() + 9);
                }
                if (character.getLayoutY() < 0 && character.getLayoutX() + 9 <= 520) {
                    character.setLayoutX(character.getLayoutX() + 9);
                }
            }

        } else if (event.getCode().equals((KeyCode.LEFT))) {
            tL.play();
            left = true;
            right = false;

            if (character.getLayoutX() - 9 >= 0
                    && character.getLayoutY() >= 0 && character.getLayoutY() <= 198) {
                character.setLayoutX(character.getLayoutX() - 9);
            }

            if (Settings.getCurrentRoom().hasNorthExit()
                    || Settings.getCurrentRoom().hasSouthExit()) {
                if (character.getLayoutY() > 198 && character.getLayoutX() - 9 >= 280) {
                    character.setLayoutX(character.getLayoutX() - 9);
                }
                if (character.getLayoutY() < 0 && character.getLayoutX() - 9 >= 150) {
                    character.setLayoutX(character.getLayoutX() - 9);
                }
            }
        }
    }
}
