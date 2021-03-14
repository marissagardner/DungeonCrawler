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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameScreenController {

    @FXML
    private Text money;

    @FXML
    private ImageView character;

    @FXML
    public void initialize() {

        money.setText("Money: " + Settings.getMoney());

        //array list of rooms, starts with main room
        ArrayList<DungeonRoom> rooms = new ArrayList<>();
        rooms.add(new DungeonRoom(1,1,1,1, 0));

        //generates 20 more rooms, can change this number
        for(int i = 0; i < 20; i++) {
            int roomType = 1 + (int) (Math.random() * 15);

            //does not allow "dead-end" rooms right now
            while (roomType == 1 || roomType == 2 || roomType == 4 || roomType == 8) {
                roomType = 1 + (int) (Math.random() * 15);
            }

            //converts to binary to create the room
            int[] bin = new int[4];
            int counter = 0;
            while(roomType > 0)
            {
                bin[counter] = roomType % 2;
                roomType = roomType / 2;
                counter++;
            }

            rooms.add(new DungeonRoom(bin[3], bin[2], bin[1], bin[0], i + 1));
        }

        //sorts rooms into lists if they have those exits - room can be part of multiple lists
        ArrayList<DungeonRoom> northRooms = new ArrayList<>();
        ArrayList<DungeonRoom> southRooms = new ArrayList<>();
        ArrayList<DungeonRoom> eastRooms = new ArrayList<>();
        ArrayList<DungeonRoom> westRooms = new ArrayList<>();

        for(DungeonRoom r: rooms) {
            if(r.hasNorthExit()) {
                northRooms.add(r);
            }
            if(r.hasSouthExit()) {
                southRooms.add(r);
            }
            if(r.hasEastExit()) {
                eastRooms.add(r);
            }
            if(r.hasWestExit()) {
                westRooms.add(r);
            }
        }

        //Links rooms to each other
        //Alternates front and back of lists
        //Rooms cannot link to the same room twice
        for(DungeonRoom r: rooms) {
            if(!r.areExitsFilled()) {
                //process for filling north exit with a room
                if(r.hasNorthExit() && !r.isNorthFilled() && southRooms.size() > 0) {
                    for(int i = southRooms.size() - 1; i >= 0; i--) {
                        if(r != southRooms.get(i) && !r.isLinkedTo(southRooms.get(i))) {
                            r.setNorthRoom(southRooms.get(i));
                            southRooms.get(i).setSouthRoom(r);

                            southRooms.remove(i);
                            northRooms.remove(r);

                            i = -1;
                        }
                    }
                }

                if(r.hasSouthExit() && !r.isSouthFilled() && northRooms.size() > 0) {
                    for(int i = 0; i < northRooms.size(); i++) {
                        if(r != northRooms.get(i) && !r.isLinkedTo(northRooms.get(i))) {
                            r.setSouthRoom(northRooms.get(i));
                            northRooms.get(i).setNorthRoom(r);

                            northRooms.remove(i);
                            southRooms.remove(r);

                            i = northRooms.size();
                        }
                    }
                }

                if(r.hasEastExit() && !r.isEastFilled() && westRooms.size() > 0) {
                    for(int i = westRooms.size() - 1; i >= 0; i--) {
                        if(r != westRooms.get(i) && !r.isLinkedTo(westRooms.get(i))) {
                            r.setEastRoom(westRooms.get(i));
                            westRooms.get(i).setWestRoom(r);

                            westRooms.remove(i);
                            eastRooms.remove(r);

                            i = -1;
                        }
                    }
                }

                if(r.hasWestExit() && !r.isWestFilled() && eastRooms.size() > 0) {
                    for(int i = 0; i < eastRooms.size(); i++) {
                        if(r != eastRooms.get(i) && !r.isLinkedTo(eastRooms.get(i))) {
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

        //Fills all remaining exits with dead-end rooms
        for(DungeonRoom r: rooms) {
            if(!r.areExitsFilled()) {
                r.fillRooms();
            }
        }

        //Prints out dungeon map
        //For understanding dungeon layout
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("Room: " + rooms.get(i).getRoomNum());
            System.out.println(rooms.get(i).getRoomPath());
            if (rooms.get(i).hasNorthExit() && rooms.get(i).isNorthFilled()) {
                System.out.println("North room: " + rooms.get(i).getNorthRoom().getRoomPath());
                System.out.println("North room number: " + rooms.get(i).getNorthRoom().getRoomNum());
            }
            if (rooms.get(i).hasSouthExit() && rooms.get(i).isSouthFilled()) {
                System.out.println("South room: " + rooms.get(i).getSouthRoom().getRoomPath());
                System.out.println("South room number: " + rooms.get(i).getSouthRoom().getRoomNum());
            }
            if (rooms.get(i).hasEastExit() && rooms.get(i).isEastFilled()) {
                System.out.println("East room: " + rooms.get(i).getEastRoom().getRoomPath());
                System.out.println("East room number: " + rooms.get(i).getEastRoom().getRoomNum());
            }
            if (rooms.get(i).hasWestExit() && rooms.get(i).isWestFilled()) {
                System.out.println("West room: " + rooms.get(i).getWestRoom().getRoomPath());
                System.out.println("West room number: " + rooms.get(i).getWestRoom().getRoomNum());
            }
            System.out.println("Exits filled: " + rooms.get(i).areExitsFilled());
            System.out.println();
            System.out.println();
        }

        Settings.setCurrentRoom(rooms.get(0));

        //TODO: add end room - need to figure out how to make it 6 rooms away
    }

    @FXML
    public void enterDungeon(KeyEvent event) throws IOException{

        if (character.getLayoutY() <= -150) {
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
        if (event.getCode().equals(KeyCode.UP)) {
            if (character.getLayoutY() - 9 >= 0 || (character.getLayoutX() >= 280 && character.getLayoutX() <= 400)) {
                character.setLayoutY(character.getLayoutY() - 9);
            }
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            if (character.getLayoutY() + 9 <= 198) {
                character.setLayoutY(character.getLayoutY() + 9);
            }
        } else if (event.getCode().equals(KeyCode.LEFT)) {
            if (character.getLayoutX() - 9 >= 0 && character.getLayoutY() >= 0) {
                character.setLayoutX(character.getLayoutX() - 9);
            } else if (character.getLayoutX() - 9 >= 280) {
                character.setLayoutX(character.getLayoutX() - 9);
            }
        } else if (event.getCode().equals(KeyCode.RIGHT)) {
            if (character.getLayoutX() + 9 <= 672 && character.getLayoutY() >= 0) {
                character.setLayoutX(character.getLayoutX() + 9);
            } else if (character.getLayoutX() + 9 <= 400) {
                character.setLayoutX(character.getLayoutX() + 9);
            }
        }
    }
}
