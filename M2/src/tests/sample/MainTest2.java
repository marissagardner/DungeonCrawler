package sample;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import sample.Java.GameState;
import sample.Java.Main;
import sample.Java.Settings;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

//This file contains test cases for M3

public class MainTest2 extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main main = new Main();
        main.start(primaryStage);
    }

    @Test
    public void testGameInitialization() {
        clickOn("#startButton");
        clickOn("#nameInput");
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        assertEquals(100, Settings.getMoney());
    }

    @Test
    public void testRoom0() {
        assertEquals(GameState.START_SCREEN, Settings.getGameState());
        clickOn("#startButton");
        sleep(1500);
        assertEquals(GameState.MENU_SCREEN, Settings.getGameState());
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        verifyThat("#money", NodeMatchers.isVisible());
        for (int i = 0; i < 29; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        sleep(1500);
        assertEquals(0, Settings.getCurrentRoom().getRoomNum());
    }

    @Test
    public void testRoom0NorthExit() {
        assertEquals(GameState.START_SCREEN, Settings.getGameState());
        clickOn("#startButton");
        sleep(1500);
        assertEquals(GameState.MENU_SCREEN, Settings.getGameState());
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        verifyThat("#money", NodeMatchers.isVisible());
        for (int i = 0; i < 29; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        sleep(1500);
        assertEquals(0, Settings.getCurrentRoom().getRoomNum());
        int NorthExitRoomNum = Settings.getCurrentRoom().getNorthRoom().getRoomNum();
        for (int i = 0; i < 22; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        sleep(1500);
        assertEquals(NorthExitRoomNum, Settings.getCurrentRoom().getRoomNum());
    }

    @Test
    public void testRoom0EastExit() {
        assertEquals(GameState.START_SCREEN, Settings.getGameState());
        clickOn("#startButton");
        sleep(1500);
        assertEquals(GameState.MENU_SCREEN, Settings.getGameState());
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        verifyThat("#money", NodeMatchers.isVisible());
        for (int i = 0; i < 29; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        sleep(1500);
        assertEquals(0, Settings.getCurrentRoom().getRoomNum());
        int EastExitRoomNum = Settings.getCurrentRoom().getEastRoom().getRoomNum();
        for (int i = 0; i < 37; i++) {
            press(KeyCode.RIGHT).release(KeyCode.RIGHT);
        }
        for (int i = 0; i < 8; i++) {
            press(KeyCode.DOWN).release(KeyCode.DOWN);
        }
        for (int i = 0; i < 15; i++) {
            press(KeyCode.RIGHT).release(KeyCode.RIGHT);
        }
        sleep(1500);
        assertEquals(EastExitRoomNum, Settings.getCurrentRoom().getRoomNum());
    }
    @Test
    public void testRoom0SouthExit() {
        assertEquals(GameState.START_SCREEN, Settings.getGameState());
        clickOn("#startButton");
        sleep(1500);
        assertEquals(GameState.MENU_SCREEN, Settings.getGameState());
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        verifyThat("#money", NodeMatchers.isVisible());
        for (int i = 0; i < 29; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        sleep(1500);
        assertEquals(0, Settings.getCurrentRoom().getRoomNum());
        int SouthExitRoomNum = Settings.getCurrentRoom().getSouthRoom().getRoomNum();
        for (int i = 0; i < 26; i++) {
            press(KeyCode.DOWN).release(KeyCode.DOWN);
        }
        sleep(1500);
        assertEquals(SouthExitRoomNum, Settings.getCurrentRoom().getRoomNum());
    }
    @Test
    public void testRoom0WestExit() {
        assertEquals(GameState.START_SCREEN, Settings.getGameState());
        clickOn("#startButton");
        sleep(1500);
        assertEquals(GameState.MENU_SCREEN, Settings.getGameState());
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        verifyThat("#money", NodeMatchers.isVisible());
        for (int i = 0; i < 29; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        sleep(1500);
        assertEquals(0, Settings.getCurrentRoom().getRoomNum());
        int WestExitRoomNum = Settings.getCurrentRoom().getWestRoom().getRoomNum();
        for (int i = 0; i < 37; i++) {
            press(KeyCode.LEFT).release(KeyCode.LEFT);
        }
        for (int i = 0; i < 8; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        for (int i = 0; i < 15; i++) {
            press(KeyCode.LEFT).release(KeyCode.LEFT);
        }
        sleep(1500);
        assertEquals(WestExitRoomNum, Settings.getCurrentRoom().getRoomNum());
    }

    @Test
    public void testRoom0NorthAndBackExit() {
        assertEquals(GameState.START_SCREEN, Settings.getGameState());
        clickOn("#startButton");
        sleep(1500);
        assertEquals(GameState.MENU_SCREEN, Settings.getGameState());
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        verifyThat("#money", NodeMatchers.isVisible());
        for (int i = 0; i < 29; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        sleep(1500);
        assertEquals(0, Settings.getCurrentRoom().getRoomNum());
        for (int i = 0; i < 22; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        for (int i = 0; i < 26; i++) {
            press(KeyCode.DOWN).release(KeyCode.DOWN);
        }
        sleep(1500);
        assertEquals(0, Settings.getCurrentRoom().getRoomNum());
    }

    @Test
    public void testRoom0EastAndBackExit() {
        assertEquals(GameState.START_SCREEN, Settings.getGameState());
        clickOn("#startButton");
        sleep(1500);
        assertEquals(GameState.MENU_SCREEN, Settings.getGameState());
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        verifyThat("#money", NodeMatchers.isVisible());
        for (int i = 0; i < 29; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        sleep(1500);
        assertEquals(0, Settings.getCurrentRoom().getRoomNum());
        for (int i = 0; i < 37; i++) {
            press(KeyCode.RIGHT).release(KeyCode.RIGHT);
        }
        for (int i = 0; i < 8; i++) {
            press(KeyCode.DOWN).release(KeyCode.DOWN);
        }
        for (int i = 0; i < 15; i++) {
            press(KeyCode.RIGHT).release(KeyCode.RIGHT);
        }
        for (int i = 0; i < 37; i++) {
            press(KeyCode.LEFT).release(KeyCode.LEFT);
        }
        for (int i = 0; i < 8; i++) {
            press(KeyCode.UP).release(KeyCode.UP);
        }
        for (int i = 0; i < 15; i++) {
            press(KeyCode.LEFT).release(KeyCode.LEFT);
        }
        sleep(1500);
        assertEquals(0, Settings.getCurrentRoom().getRoomNum());
    }
}

