package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class Controller {
    @FXML
    private ToggleGroup weaponGroup;

    @FXML
    private ToggleGroup difficultyGroup;

    @FXML
    public void handleWeaponToggleClick() {
        weaponGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
    }

    @FXML
    public void handleDiffcultyToggleClick() {
        difficultyGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
    }

}
