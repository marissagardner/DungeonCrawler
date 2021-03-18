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
}

