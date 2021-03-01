package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

public class menu_screen_controllerTest extends ApplicationTest {
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("menu_screen.fxml"));
        primaryStage.setTitle("Forbidden Forest");
        primaryStage.setScene(new Scene(root, 960, 600));
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(960);
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(960);
        primaryStage.show();
    }

    @Test
    public void testPlay() {
      //  clickOn("#startGame");
//        Node dialogPane = lookup(".dialog-pane").query();
//        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Input a valid name."));
      //  verifyThat("OK", NodeMatchers.isVisible());
    }
}