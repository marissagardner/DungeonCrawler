package sample;

import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main main = new Main();
        main.start(primaryStage);
    }

    @Test
    public void testStart() {
        assertEquals(GameState.START_SCREEN, Settings.gameState);
        clickOn("#startButton");
        sleep(1500);
        assertEquals(GameState.MENU_SCREEN, Settings.gameState);
    }

    @Test
    public void testSwitchToMenuScreen() {
        clickOn("#startButton");
        sleep(1500);
        verifyThat("#gameTitle", NodeMatchers.isVisible());
    }

    @Test
    public void testSwitchToGameScreen() {
        //valid inputs
        clickOn("#startButton");
        clickOn("#nameInput");
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        verifyThat("#money", NodeMatchers.isVisible());
    }

    @Test
    public void testNoName() {
        clickOn("#startButton");
        clickOn("#nameInput");
        write("");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Input a valid name."));
        verifyThat("OK", NodeMatchers.isVisible());
    }

    @Test
    public void testWhitespaceName() {
        clickOn("#startButton");
        clickOn("#nameInput");
        write("    ");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Input a valid name."));
        verifyThat("OK", NodeMatchers.isVisible());
    }

    @Test
    public void testNoWeapon() {
        clickOn("#startButton");
        clickOn("#nameInput");
        write("Avi");
        clickOn("#sword");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Please select a weapon."));
        verifyThat("OK", NodeMatchers.isVisible());
    }

    @Test
    public void testNoDifficulty() {
        clickOn("#startButton");
        clickOn("#nameInput");
        write("Ruhan");
        clickOn("#easy");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Please choose your difficulty."));
        verifyThat("OK", NodeMatchers.isVisible());
    }

    @Test
    public void testAxeMedium() {
        clickOn("#startButton");
        clickOn("#nameInput");
        write("Ruhan");
        clickOn("#axe");
        clickOn("#medium");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
    }

    @Test
    public void testEasyMoney() {
        clickOn("#startButton");
        clickOn("#nameInput");
        write("Avi");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        assertEquals(100, Settings.money);
    }

    @Test
    public void testMediumMoney() {
        clickOn("#startButton");
        clickOn("#nameInput");
        write("Avi");
        clickOn("#medium");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        assertEquals(75, Settings.money);
    }

    @Test
    public void testHardMoney() {
        clickOn("#startButton");
        clickOn("#nameInput");
        write("Avi");
        clickOn("#hard");
        sleep(1500);
        clickOn("#startGame");
        sleep(1500);
        assertEquals(50, Settings.money);
    }

}

