package sample;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

public class start_screen_controllerTest extends ApplicationTest {

    public void start(Stage primaryStage) throws Exception{
        Main main = new Main();
        main.start(primaryStage);
    }

    @Test
    public void testPlay() {
        clickOn("Start Game!");
        assertEquals("Start", Settings.gameState);
        clickOn("#startButton");
        assertEquals("Menu", Settings.gameState);

    }

}