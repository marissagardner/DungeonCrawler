package sample;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class game_screen_controller {

    @FXML
    private Text money;

    @FXML
    public void initialize() {
        money.setText("Money: " + Settings.money);
    }
}
