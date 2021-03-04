package sample.Java;

import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class game_screen_controller {

    @FXML
    private Text money;

    @FXML
    public void initialize() {
        money.setText("Money: " + Settings.money);
    }
}
