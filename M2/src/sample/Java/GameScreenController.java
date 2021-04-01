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
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.ArrayList;

public class GameScreenController {

    @FXML
    private Text money;

    @FXML
    private Text health;

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

    private ImageView player;
    private ImageView player1;
    private ImageView player2;
    private ImageView player3;
    private ImageView player4;
    private ImageView playerL;
    private ImageView playerL1;
    private ImageView playerL2;
    private ImageView playerL3;
    private ImageView playerL4;

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
        money.setText("Money: " + Settings.getMoney());
        health.setText("Health: " + Settings.getPlayer().getHealth());
        //array list of rooms, starts with main room
        ArrayList<DungeonRoom> rooms = new ArrayList<>();
        rooms.add(new DungeonRoom(1, 1, 1, 1, 0, false, true));
        //generates 20 more rooms, can change this number
        for (int i = 0; i < 20; i++) {
            int roomType = 1 + (int) (Math.random() * 15);
            //does not allow "dead-end" rooms right now
            while (roomType == 1 || roomType == 2 || roomType == 4 || roomType == 8) {
                roomType = 1 + (int) (Math.random() * 15);
            }
            //converts to binary to create the room
            int[] bin = new int[4];
            int counter = 0;
            while (roomType > 0) {
                bin[counter] = roomType % 2;
                roomType = roomType / 2;
                counter++;
            }
            rooms.add(new DungeonRoom(bin[3], bin[2], bin[1], bin[0], i + 1, false, false));
        }
        //sorts rooms into lists if they have those exits - room can be part of multiple lists
        ArrayList<DungeonRoom> northRooms = new ArrayList<>();
        ArrayList<DungeonRoom> southRooms = new ArrayList<>();
        ArrayList<DungeonRoom> eastRooms = new ArrayList<>();
        ArrayList<DungeonRoom> westRooms = new ArrayList<>();
        for (DungeonRoom r : rooms) {
            if (r.hasNorthExit()) {
                northRooms.add(r);
            }
            if (r.hasSouthExit()) {
                southRooms.add(r);
            }
            if (r.hasEastExit()) {
                eastRooms.add(r);
            }
            if (r.hasWestExit()) {
                westRooms.add(r);
            }
        }
        //Links rooms to each other
        for (DungeonRoom r : rooms) {
            if (!r.areExitsFilled()) {
                if (r.hasNorthExit() && !r.isNorthFilled() && southRooms.size() > 0) {
                    for (int i = southRooms.size() - 1; i >= 0; i--) {
                        if (r != southRooms.get(i) && !r.isLinkedTo(southRooms.get(i))) {
                            r.setNorthRoom(southRooms.get(i));
                            southRooms.get(i).setSouthRoom(r);
                            southRooms.remove(i);
                            northRooms.remove(r);
                            i = -1;
                        }
                    }
                }
                if (r.hasSouthExit() && !r.isSouthFilled() && northRooms.size() > 0) {
                    for (int i = 0; i < northRooms.size(); i++) {
                        if (r != northRooms.get(i) && !r.isLinkedTo(northRooms.get(i))) {
                            r.setSouthRoom(northRooms.get(i));
                            northRooms.get(i).setNorthRoom(r);
                            northRooms.remove(i);
                            southRooms.remove(r);
                            i = northRooms.size();
                        }
                    }
                }
                if (r.hasEastExit() && !r.isEastFilled() && westRooms.size() > 0) {
                    for (int i = westRooms.size() - 1; i >= 0; i--) {
                        if (r != westRooms.get(i) && !r.isLinkedTo(westRooms.get(i))) {
                            r.setEastRoom(westRooms.get(i));
                            westRooms.get(i).setWestRoom(r);
                            westRooms.remove(i);
                            eastRooms.remove(r);
                            i = -1;
                        }
                    }
                }
                if (r.hasWestExit() && !r.isWestFilled() && eastRooms.size() > 0) {
                    for (int i = 0; i < eastRooms.size(); i++) {
                        if (r != eastRooms.get(i) && !r.isLinkedTo(eastRooms.get(i))) {
                            r.setWestRoom(eastRooms.get(i));
                            eastRooms.get(i).setEastRoom(r);
                            eastRooms.remove(i);
                            westRooms.remove(r);
                            i = eastRooms.size();
                        }
                    }
                }
            }
        }
        for (DungeonRoom r : rooms) {
            if (!r.areExitsFilled()) {
                r.fillRooms();
            }
        }
        addExit(rooms);
        //Fills all remaining exits with dead-end rooms
        for (DungeonRoom r : rooms) {
            if (!r.areExitsFilled()) {
                r.fillRooms();
            }
        }
        //Prints out dungeon map
        //For understanding dungeon layout
        printMap(rooms);
        Settings.setCurrentRoom(rooms.get(0));
    }

    public void printMap(ArrayList<DungeonRoom> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("Room: " + rooms.get(i).getRoomNum());
            System.out.println(rooms.get(i).getRoomPath());
            if (rooms.get(i).hasNorthExit() && rooms.get(i).isNorthFilled()) {
                System.out.println("North room number: "
                        + rooms.get(i).getNorthRoom().getRoomNum());
            }
            if (rooms.get(i).hasSouthExit() && rooms.get(i).isSouthFilled()) {
                System.out.println("South room number: "
                        + rooms.get(i).getSouthRoom().getRoomNum());
            }
            if (rooms.get(i).hasEastExit() && rooms.get(i).isEastFilled()) {
                System.out.println("East room number: "
                        + rooms.get(i).getEastRoom().getRoomNum());
            }
            if (rooms.get(i).hasWestExit() && rooms.get(i).isWestFilled()) {
                System.out.println("West room number: "
                        + rooms.get(i).getWestRoom().getRoomNum());
            }
            System.out.println("Exits filled: " + rooms.get(i).areExitsFilled());
            System.out.println();
            System.out.println();
        }
    }

    public void addExit(ArrayList<DungeonRoom> rooms) {
        ArrayList<Integer> randoms = new ArrayList<>();
        randoms.add(21);
        randoms.add(22);
        randoms.add(23);
        randoms.add(24);
        randoms.add(25);
        randoms.add(26);
        randoms.add(27);
        randoms.add(28);
        randoms.add(29);
        randoms.add(30);

        int selector = (int) (Math.random() * randoms.size());
        DungeonRoom random1 = new DungeonRoom(
                1, 1, 1, 1, randoms.get(selector), false, false);
        randoms.remove(selector);
        selector = (int) (Math.random() * randoms.size());
        DungeonRoom random2 = new DungeonRoom(
                0, 1, 1, 0, randoms.get(selector), false, false);
        randoms.remove(selector);
        selector = (int) (Math.random() * randoms.size());
        DungeonRoom random3 = new DungeonRoom(
                1, 0, 1, 1, randoms.get(selector), false, false);
        randoms.remove(selector);
        selector = (int) (Math.random() * randoms.size());
        DungeonRoom random4 = new DungeonRoom(
                0, 1, 0, 1, randoms.get(selector), false, false);
        randoms.remove(selector);
        selector = (int) (Math.random() * randoms.size());
        DungeonRoom random5 = new DungeonRoom(
                1, 1, 0, 1, randoms.get(selector), false, false);
        randoms.remove(selector);
        selector = (int) (Math.random() * randoms.size());
        DungeonRoom random6 = new DungeonRoom(
                1, 0, 1, 1, randoms.get(selector), false, false);
        DungeonRoom exit = new DungeonRoom(
                1, 1, 0, 0, 100, true, true);

        rooms.add(random1);
        rooms.add(random2);
        rooms.add(random3);
        rooms.add(random4);
        rooms.add(random5);
        rooms.add(random6);
        rooms.add(exit);

        random1.setNorthRoom(random2);
        random2.setSouthRoom(random1);
        random2.setEastRoom(random3);
        random3.setWestRoom(random2);
        random3.setEastRoom(random4);
        random4.setWestRoom(random3);
        random3.setNorthRoom(random5);
        random5.setSouthRoom(random3);
        random5.setWestRoom(random6);
        random6.setEastRoom(random5);
        random6.setNorthRoom(exit);
        exit.setSouthRoom(random6);

        DungeonRoom leadingRoom;
        boolean isRandom = false;

        while (!isRandom) {
            int lead = 1 + (int) (Math.random() * 4);
            if (lead == 1) {
                leadingRoom = rooms.get(0).getNorthRoom();
                if (leadingRoom.hasWestExit()) {
                    leadingRoom.getWestRoom().setEastRoom(
                            new DungeonRoom(0, 0, 0, 1, 100, false, false));
                    leadingRoom.setWestRoom(random1);
                    random1.setEastRoom(leadingRoom);
                    isRandom = true;
                } else if (leadingRoom.hasNorthExit()) {
                    leadingRoom.getNorthRoom().setSouthRoom(
                            new DungeonRoom(1, 0, 0, 0, 100, false, false));
                    leadingRoom.setNorthRoom(random1);
                    random1.setSouthRoom(leadingRoom);
                    isRandom = true;
                } else if (leadingRoom.hasEastExit()) {
                    leadingRoom.getEastRoom().setWestRoom(
                            new DungeonRoom(0, 0, 1, 0, 100, false, false));
                    leadingRoom.setEastRoom(random1);
                    random1.setWestRoom(leadingRoom);
                    isRandom = true;
                }
            }
            if (lead == 2) {
                leadingRoom = rooms.get(0).getSouthRoom();
                if (leadingRoom.hasWestExit()) {
                    leadingRoom.getWestRoom().setEastRoom(
                            new DungeonRoom(0, 0, 0, 1, 100, false, false));
                    leadingRoom.setWestRoom(random1);
                    random1.setEastRoom(leadingRoom);
                    isRandom = true;
                } else if (leadingRoom.hasEastExit()) {
                    leadingRoom.getEastRoom().setWestRoom(
                            new DungeonRoom(0, 0, 1, 0, 100, false, false));
                    leadingRoom.setEastRoom(random1);
                    random1.setWestRoom(leadingRoom);
                    isRandom = true;
                }
            }
            if (lead == 3) {
                leadingRoom = rooms.get(0).getEastRoom();
                if (leadingRoom.hasNorthExit()) {
                    leadingRoom.getNorthRoom().setSouthRoom(
                            new DungeonRoom(1, 0, 0, 0, 100, false, false));
                    leadingRoom.setNorthRoom(random1);
                    isRandom = true;
                } else if (leadingRoom.hasEastExit()) {
                    leadingRoom.getEastRoom().setWestRoom(
                            new DungeonRoom(0, 0, 1, 0, 100, false, false));
                    leadingRoom.setEastRoom(random1);
                    random1.setWestRoom(leadingRoom);
                    isRandom = true;
                }
            }
            if (lead == 4) {
                leadingRoom = rooms.get(0).getWestRoom();
                if (leadingRoom.hasWestExit()) {
                    leadingRoom.getWestRoom().setEastRoom(
                            new DungeonRoom(0, 0, 0, 1, 100, false, false));
                    leadingRoom.setWestRoom(random1);
                    isRandom = true;
                } else if (leadingRoom.hasNorthExit()) {
                    leadingRoom.getNorthRoom().setSouthRoom(
                            new DungeonRoom(1, 0, 0, 0, 100, false, false));
                    leadingRoom.setNorthRoom(random1);
                    isRandom = true;
                }
            }
        }
    }


    private Timeline t = new Timeline();
    private Timeline tL = new Timeline();
    private boolean right = true;
    private boolean left = false;
    @FXML
    public void enterDungeon(KeyEvent event) throws IOException {
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

        if (man.getLayoutY() <= -150) {
            Settings.setGameState(GameState.DUNGEON);
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
        if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            t.stop();
            tL.stop();
            if (right) {
                man.getChildren().setAll(player);
            } else if (left) {
                man.getChildren().setAll(playerL);
            }
        } else if (event.getCode().equals(KeyCode.UP)) {
            if (right) {
                t.play();
            } else if (left) {
                tL.play();
            }
            if (man.getLayoutY() - 9 >= 0
                    || (man.getLayoutX() >= 280 && man.getLayoutX() <= 400)) {
                man.setLayoutY(man.getLayoutY() - 9);
            }
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            if (right) {
                t.play();
            } else if (left) {
                tL.play();
            }
            if (man.getLayoutY() + 9 <= 198) {
                man.setLayoutY(man.getLayoutY() + 9);
            }
        } else if (event.getCode().equals(KeyCode.LEFT)) {
            tL.play();
            left = true;
            right = false;
            if (man.getLayoutX() - 9 >= 0 && man.getLayoutY() >= 0) {
                man.setLayoutX(man.getLayoutX() - 9);
            } else if (man.getLayoutX() - 9 >= 280) {
                man.setLayoutX(man.getLayoutX() - 9);
            }
        } else if (event.getCode().equals(KeyCode.RIGHT)) {
            t.play();
            right = true;
            left = false;
            if (man.getLayoutX() + 9 <= 672 && man.getLayoutY() >= 0) {
                man.setLayoutX(man.getLayoutX() + 9);
            } else if (man.getLayoutX() + 9 <= 400) {
                man.setLayoutX(man.getLayoutX() + 9);
            }
        }
    }
}
