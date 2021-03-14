package sample.Java;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class DungeonRoomController {

    //Only moves one room each time an arrow is pressed
    private boolean acceptInput = false;

    @FXML
    private Text roomNumber;

    @FXML
    private Text money;

    @FXML
    private ImageView character;

    @FXML
    public void initialize() {
        String playerMoney = "Money: " + Settings.getMoney();
        String roomNum = "Room: " + Settings.getCurrentRoom().getRoomNum();
        money.setText(playerMoney);
        roomNumber.setText(roomNum);
    }

    //Unused right now, onKeyReleased could call this method for walk functionality
    @FXML
    public void reset(KeyEvent event) throws IOException {
    }

    @FXML
    public void move(KeyEvent event) throws IOException {
        if(event.getCode().equals((KeyCode.UP))) {

            if (character.getLayoutY() - 9 >= 0 && character.getLayoutX() >= 0 && character.getLayoutX() <= 672) {
                character.setLayoutY(character.getLayoutY() - 9);
            }

            if (Settings.getCurrentRoom().hasNorthExit()) {
                if (character.getLayoutX() > 280 && character.getLayoutX() < 400) {
                    character.setLayoutY(character.getLayoutY() - 9);
                }
                if (character.getLayoutY() <= -150) {
                    Settings.setCurrentRoom(Settings.getCurrentRoom().getNorthRoom());
                    acceptInput = false;

                    Parent menuParent = FXMLLoader.load(getClass().getResource(Settings.getCurrentRoom().getRoomPath()));
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

            if (Settings.getCurrentRoom().hasEastExit()) {
                if (character.getLayoutX() > 672 && character.getLayoutY() - 9 >= 150) {
                    character.setLayoutY(character.getLayoutY() - 9);
                }
            }

            if (Settings.getCurrentRoom().hasWestExit()) {
                if (character.getLayoutX() < 0 && character.getLayoutY() - 9 >= 0) {
                    character.setLayoutY(character.getLayoutY() - 9);
                }
            }
        } else if (event.getCode().equals((KeyCode.DOWN))) {

            if (character.getLayoutY() + 9 <= 198 && character.getLayoutX() >= 0 && character.getLayoutX() <= 672) {
                character.setLayoutY(character.getLayoutY() + 9);
            }

            if (Settings.getCurrentRoom().hasSouthExit()) {
                if (character.getLayoutX() > 280 && character.getLayoutX() < 400) {
                    character.setLayoutY(character.getLayoutY() + 9);
                }

                if (character.getLayoutY() >= 380) {
                    Settings.setCurrentRoom(Settings.getCurrentRoom().getSouthRoom());
                    acceptInput = false;

                    Parent menuParent = FXMLLoader.load(getClass().getResource(Settings.getCurrentRoom().getRoomPath()));
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

            if (Settings.getCurrentRoom().hasEastExit()) {
                if (character.getLayoutX() > 672 && character.getLayoutY() + 9 <= 198) {
                    character.setLayoutY(character.getLayoutY() + 9);
                }
            }

            if (Settings.getCurrentRoom().hasWestExit()) {
                if (character.getLayoutX() < 0 && character.getLayoutY() + 9 <= 48) {
                    character.setLayoutY(character.getLayoutY() + 9);
                }
            }
        } else if (event.getCode().equals((KeyCode.RIGHT))) {
            if (character.getLayoutX() + 9 <= 672 && character.getLayoutY() >= 0 && character.getLayoutY() <= 198) {
                character.setLayoutX(character.getLayoutX() + 9);
            }

            if (Settings.getCurrentRoom().hasEastExit()) {
                if (character.getLayoutY() > 150 && character.getLayoutY() < 198) {
                    character.setLayoutX(character.getLayoutX() + 9);
                }

                if (character.getLayoutX() >= 800) {
                    Settings.setCurrentRoom(Settings.getCurrentRoom().getEastRoom());
                    acceptInput = false;

                    Parent menuParent = FXMLLoader.load(getClass().getResource(Settings.getCurrentRoom().getRoomPath()));
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

            if (Settings.getCurrentRoom().hasNorthExit() || Settings.getCurrentRoom().hasSouthExit()) {
                if ((character.getLayoutY() < 0 || character.getLayoutY() > 198) && character.getLayoutX() + 9 <= 400) {
                    character.setLayoutX(character.getLayoutX() + 9);
                }
            }

        } else if (event.getCode().equals((KeyCode.LEFT))) {

            if (character.getLayoutX() - 9 >= 0 && character.getLayoutY() >= 0 && character.getLayoutY() <= 198) {
                character.setLayoutX(character.getLayoutX() - 9);
            }

            if(Settings.getCurrentRoom().hasWestExit()) {
                if (character.getLayoutY() > 0 && character.getLayoutY() < 48) {
                    character.setLayoutX(character.getLayoutX() - 9);
                }

                if (character.getLayoutX() <= -128) {
                    Settings.setCurrentRoom(Settings.getCurrentRoom().getWestRoom());
                    acceptInput = false;

                    Parent menuParent = FXMLLoader.load(getClass().getResource(Settings.getCurrentRoom().getRoomPath()));
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

            if (Settings.getCurrentRoom().hasNorthExit() || Settings.getCurrentRoom().hasSouthExit()) {
                if ((character.getLayoutY() < 0 || character.getLayoutY() > 198) && character.getLayoutX() - 9 >= 280) {
                    character.setLayoutX(character.getLayoutX() - 9);
                }
            }
        }
    }
}
