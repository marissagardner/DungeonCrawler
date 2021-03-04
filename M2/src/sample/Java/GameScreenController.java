package sample.Java;

import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class GameScreenController {

    @FXML
    private Text money;

    @FXML
    public void initialize() {
        money.setText("Money: " + Settings.getMoney());
    }
}
