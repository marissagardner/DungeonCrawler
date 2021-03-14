package sample.Java;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        if(acceptInput && event.getCode().equals((KeyCode.UP))) {
            if(Settings.getCurrentRoom().hasNorthExit()) {
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
        } else if(acceptInput && event.getCode().equals((KeyCode.DOWN))) {
            if(Settings.getCurrentRoom().hasSouthExit()) {
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
        } else if(acceptInput && event.getCode().equals((KeyCode.RIGHT))) {
            if(Settings.getCurrentRoom().hasEastExit()) {
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
        } else if(acceptInput && event.getCode().equals((KeyCode.LEFT))) {
            if(Settings.getCurrentRoom().hasWestExit()) {
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
        if(event.getCode().equals(KeyCode.ENTER)) {
            acceptInput = true;
        }
    }
}
